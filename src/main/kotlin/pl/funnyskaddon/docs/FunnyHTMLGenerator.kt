package pl.funnyskaddon.docs

import ch.njol.skript.Skript
import ch.njol.skript.doc.*
import ch.njol.skript.lang.*
import com.google.common.io.Files
import pl.funnyskaddon.FunnySkAddon
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.function.Function


class FunnyHTMLGenerator(private val plugin: FunnySkAddon, private val template: File, private val output: File) {

    private var skeleton: String

    private val listItem: File?

    private val eventDocItem: File?
    private val conditionDocItem: File?
    private val effectDocItem: File?
    private val expressionDocItem: File?
    private val eventExpressionDocItem: File?

    init {
        this.skeleton = readFile(File("$template/template.html"))

        this.listItem = File("$template/templates/items-list-item.html")

        this.eventDocItem = File("$template/templates/event-doc-item.html")
        this.conditionDocItem = File("$template/templates/condition-doc-item.html")
        this.effectDocItem = File("$template/templates/effect-doc-item.html")
        this.expressionDocItem = File("$template/templates/expression-doc-item.html")
        this.eventExpressionDocItem = File("$template/templates/event-expression-doc-item.html")
    }

    fun generateDocumentation() {
        this.generate("events.html", sortedIterator(Skript.getEvents().iterator(), eventComparator), eventDocItem)
        this.generate(
            "conditions.html",
            sortedIterator(Skript.getConditions().iterator(), syntaxElementComparator),
            conditionDocItem
        )
        this.generate(
            "effects.html",
            sortedIterator(Skript.getEvents().iterator(), syntaxElementComparator),
            effectDocItem
        )
        this.generate(
            "expressions.html",
            sortedIterator(Skript.getExpressions().iterator(), syntaxElementComparator),
            expressionDocItem,
            eventExpressionDocItem,
            useAlternative = Function {
                return@Function it.c.getAnnotation(Events::class.java) != null
            })
    }

    private fun generate(
        fileName: String,
        iterator: Iterator<SyntaxElementInfo<*>>,
        docItemFile: File?,
        alternativeDocItemFile: File? = null,
        useAlternative: Function<SyntaxElementInfo<*>, Boolean> = Function {
            return@Function false
        }
    ) {
        Skript.info("Creating documentation for $fileName")

        var data: String = this.skeleton

        var listOfItems = ""
        if (listItem == null) return
        val listItem: String = readFile(this.listItem)

        var docItems = ""

        if (docItemFile == null || !docItemFile.exists()) return
        val docItem: String = readFile(docItemFile)

        var alternativeDocItem: String? = null
        if (alternativeDocItemFile != null && alternativeDocItemFile.exists()) {
            alternativeDocItem = readFile(alternativeDocItemFile)
        }

        for (item in iterator) {
            if (item.c.getAnnotation(NoDoc::class.java) != null || item.c.getAnnotation(FunnyDoc::class.java) == null) {
                continue
            }

            val info: FunnySyntaxElement = if (item is SkriptEventInfo<*>) {
                FunnySyntaxElement(item)
            } else {
                FunnySyntaxElement(item)
            }

            FunnySyntaxElement(item)
            listOfItems += generateItemsListItem(listItem, info)
            docItems += if (useAlternative.apply(item) && alternativeDocItem != null) {
                generateDocItem(alternativeDocItem, info)
            } else {
                generateDocItem(docItem, info)
            }
        }

        data = data.replace("{doc.items-list}", listOfItems)
        data = data.replace("{doc.items}", docItems)
        data = data.replace("{fsa.version}", plugin.description.version)

        writeFile(File("$output/$fileName"), data)
    }

    private fun generateDocItem(
        docItemSkeleton: String,
        info: FunnySyntaxElement
    ): String {
        var generatedItem: String = docItemSkeleton

        if (info.name == null) {
            return ""
        } else {
            generatedItem = generatedItem.replace("{element.name}", info.name)
                .replace("{element.id}", info.name.toLowerCase().replace(" ", "-"))
        }

        generatedItem = if (info.description == null) {
            generatedItem.replace(
                "{element.description}",
                "Brak opisu"
            )
        } else {
            generatedItem.replace(
                "{element.description}",
                info.description
            )
        }

        generatedItem = if (info.patterns.isNullOrEmpty()) {
            generatedItem.replace(
                "{element.patterns}",
                "Brak wzorów"
            )
        } else {
            generatedItem.replace(
                "{element.patterns}",
                info.patterns
            )
        }

        generatedItem = if (info.examples.isNullOrEmpty()) {
            generatedItem.replace(
                "{element.examples}",
                "Brak przykładów"
            )
        } else {
            generatedItem.replace(
                "{element.examples}",
                info.examples
            )
        }

        generatedItem = if (info.events.isNullOrEmpty()) {
            generatedItem.replace(
                "{element.events}",
                "Brak wydarzeń"
            )
        } else {
            var eventLinks = ""

            for (eventName in info.events.sorted()) {
                eventLinks += ", <a href=\"events.html#on-" + eventName.toLowerCase()
                    .replace(" ", "-") + "\">" + eventName + "</a>"
            }

            generatedItem.replace(
                "{element.events}",
                eventLinks.replaceFirst(", ", "")
            )
        }

        return generatedItem
    }

    private fun generateItemsListItem(itemsListItemSkeleton: String, info: FunnySyntaxElement): String {
        var generatedItem: String = itemsListItemSkeleton

        if (info.name != null) {
            generatedItem = generatedItem.replace("{element.name}", info.name)
                .replace("{element.id}", info.name.toLowerCase().replace(" ", "-"))
        }

        return generatedItem
    }

    private fun readFile(file: File): String {
        return try {
            Files.toString(file, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            ""
        }
    }

    private fun writeFile(file: File, data: String) {
        try {
            Files.write(data, file, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private class SyntaxElementComparator : Comparator<SyntaxElementInfo<*>?> {
        override fun compare(o1: SyntaxElementInfo<*>?, o2: SyntaxElementInfo<*>?): Int {
            // Nullness check
            if (o1 == null || o2 == null) {
                assert(false)
                throw NullPointerException()
            }

            if (o1.c.getAnnotation(NoDoc::class.java) != null) {
                return if (o2.c.getAnnotation(NoDoc::class.java) != null) 0 else 1
            } else if (o2.c.getAnnotation(NoDoc::class.java) != null) return -1

            if (o1.c.getAnnotation(FunnyDoc::class.java) == null) {
                return if (o2.c.getAnnotation(FunnyDoc::class.java) == null) 0 else 1
            } else if (o2.c.getAnnotation(FunnyDoc::class.java) == null) return -1

            val name1 = o1.c.getAnnotation(Name::class.java)
            val name2 = o2.c.getAnnotation(Name::class.java)
            if (name1 == null) return 0
            if (name2 == null) return 0
            return name1.value.compareTo(name2.value)
        }
    }

    private val syntaxElementComparator = SyntaxElementComparator()

    private class EventComparator : Comparator<SkriptEventInfo<*>?> {
        override fun compare(o1: SkriptEventInfo<*>?, o2: SkriptEventInfo<*>?): Int {
            // Nullness check
            if (o1 == null || o2 == null) {
                assert(false)
                throw NullPointerException()
            }

            if (o1.c.getAnnotation(NoDoc::class.java) != null) {
                return if (o2.c.getAnnotation(NoDoc::class.java) != null) 0 else 1
            } else if (o2.c.getAnnotation(NoDoc::class.java) != null) return -1

            if (o1.c.getAnnotation(FunnyDoc::class.java) == null) {
                return if (o2.c.getAnnotation(FunnyDoc::class.java) == null) 0 else 1
            } else if (o2.c.getAnnotation(FunnyDoc::class.java) == null) return -1

            return o1.name.compareTo(o2.name)
        }
    }

    private val eventComparator = EventComparator()

    private fun <T> sortedIterator(iterator: Iterator<T>, comparator: Comparator<in T>): Iterator<T> {
        val list: MutableList<T> = ArrayList()
        while (iterator.hasNext()) {
            list.add(iterator.next())
        }
        Collections.sort(list, comparator)
        return list.iterator()
    }

}