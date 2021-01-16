package pl.funnyskaddon.docs

import ch.njol.skript.Skript
import ch.njol.skript.doc.*
import ch.njol.skript.lang.SyntaxElementInfo
import com.google.common.base.Joiner
import com.google.common.io.Files
import org.apache.commons.lang.StringUtils
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*


class FunnyHTMLGenerator(private val template: File, val output: File) {

    var skeleton: String

    init {
        this.skeleton = readFile(File("$template/template.html"))
    }

    fun generate() {
        generateEvents()
        generateConditions()
        generateEffects()
        generateExpressions()
    }

    private fun generateEvents() {
        var data: String = this.skeleton

        var listOfItems = ""
        val listItem: String = readFile(File("$template/templates/items-list-item.html"))

        var docItems = ""
        val eventItem: String? = if (File("$template/templates/event-doc-item.html").exists()) {
            readFile(File("$template/templates/event-doc-item.html"))
        } else {
            null
        }

        for (event in sortedIterator(Skript.getEvents().iterator(), syntaxElementComparator)) {
            if (event.c.getAnnotation(NoDoc::class.java) != null || event.c.getAnnotation(FunnyDoc::class.java) == null) {
                continue
            }

            if (eventItem != null) {
                listOfItems += generateItemsListItem(listItem, event)
                docItems += generateDocItem(eventItem, event, replaceExamples = true)
            }
        }

        data = StringUtils.replace(data, "{doc.items-list}", listOfItems)
        data = StringUtils.replace(data, "{doc.items}", docItems)
        writeFile(File("$output/events.html"), data)
    }

    private fun generateConditions() {
        var data: String = this.skeleton

        var listOfItems = ""
        val listItem: String = readFile(File("$template/templates/items-list-item.html"))

        var docItems = ""
        val conditionItem: String? = if (File("$template/templates/condition-doc-item.html").exists()) {
            readFile(File("$template/templates/condition-doc-item.html"))
        } else {
            null
        }

        for (condition in sortedIterator(Skript.getConditions().iterator(), syntaxElementComparator)) {
            if (condition.c.getAnnotation(NoDoc::class.java) != null || condition.c.getAnnotation(FunnyDoc::class.java) == null) {
                continue
            }

            if (conditionItem != null) {
                listOfItems += generateItemsListItem(listItem, condition)
                docItems += generateDocItem(conditionItem, condition, replaceExamples = true)
            }
        }

        data = StringUtils.replace(data, "{doc.items-list}", listOfItems)
        data = StringUtils.replace(data, "{doc.items}", docItems)
        writeFile(File("$output/conditions.html"), data)
    }

    private fun generateEffects() {
        var data: String = this.skeleton

        var listOfItems = ""
        val listItem: String = readFile(File("$template/templates/items-list-item.html"))

        var docItems = ""
        val effectItem: String? = if (File("$template/templates/effect-doc-item.html").exists()) {
            readFile(File("$template/templates/effect-doc-item.html"))
        } else {
            null
        }

        for (effect in sortedIterator(Skript.getEffects().iterator(), syntaxElementComparator)) {
            if (effect.c.getAnnotation(NoDoc::class.java) != null || effect.c.getAnnotation(FunnyDoc::class.java) == null) {
                continue
            }

            if (effectItem != null) {
                listOfItems += generateItemsListItem(listItem, effect)
                docItems += generateDocItem(effectItem, effect, replaceExamples = true)
            }
        }

        data = StringUtils.replace(data, "{doc.items-list}", listOfItems)
        data = StringUtils.replace(data, "{doc.items}", docItems)
        writeFile(File("$output/effects.html"), data)
    }

    private fun generateExpressions() {
        var data: String = this.skeleton

        var listOfItems = ""
        val listItem: String = readFile(File("$template/templates/items-list-item.html"))

        var docItems = ""
        val expressionItem: String? = if (File("$template/templates/expression-doc-item.html").exists()) {
            readFile(File("$template/templates/expression-doc-item.html"))
        } else {
            null
        }

        val eventExpressionItem: String? = if (File("$template/templates/event-expression-doc-item.html").exists()) {
            readFile(File("$template/templates/event-expression-doc-item.html"))
        } else {
            null
        }

        for (expression in Skript.getExpressions()) {
            if (expression.c.getAnnotation(NoDoc::class.java) != null || expression.c.getAnnotation(FunnyDoc::class.java) == null) {
                continue
            }

            if (expression.c.getAnnotation(Events::class.java) == null) {
                if (expressionItem != null) {
                    listOfItems += generateItemsListItem(listItem, expression)
                    docItems += generateDocItem(
                        expressionItem,
                        expression,
                        replaceExamples = true,
                        replaceEvents = false
                    )
                }
            } else {
                if (eventExpressionItem != null) {
                    listOfItems += generateItemsListItem(listItem, expression)
                    docItems += generateDocItem(
                        eventExpressionItem,
                        expression,
                        replaceExamples = false,
                        replaceEvents = true
                    )
                }
            }
        }

        data = StringUtils.replace(data, "{doc.items-list}", listOfItems)
        data = StringUtils.replace(data, "{doc.items}", docItems)
        writeFile(File("$output/expressions.html"), data);
    }

    private fun generateItemsListItem(itemsListItemSkeleton: String, info: SyntaxElementInfo<*>): String {
        var generatedItem: String = itemsListItemSkeleton
        val c: Class<*> = info.c

        if (c.getAnnotation(Name::class.java) != null) {
            val name: Name = c.getAnnotation(Name::class.java)
            generatedItem = generatedItem.replace("{element.name}", name.value)
                .replace("{element.id}", name.value.toLowerCase().replace(" ", "-"))
        }

        return generatedItem
    }

    private fun generateDocItem(
        docItemSkeleton: String,
        info: SyntaxElementInfo<*>,
        replaceExamples: Boolean = true,
        replaceEvents: Boolean = false
    ): String {
        var generatedItem: String = docItemSkeleton
        val c: Class<*> = info.c

        val name: Name? = c.getAnnotation(Name::class.java)
        if (name == null) {
            return ""
        } else {
            generatedItem = generatedItem.replace("{element.name}", name.value)
                .replace("{element.id}", name.value.toLowerCase().replace(" ", "-"))
        }

        val description: Description? = c.getAnnotation(Description::class.java)
        generatedItem = if (description == null) {
            generatedItem.replace(
                "{element.description}",
                "Brak opisu"
            )
        } else {
            generatedItem.replace(
                "{element.description}",
                Joiner.on("\n").join(description.value)
            ).replace("\n\n", "<p>")
        }

        generatedItem = if (info.patterns == null || info.patterns.isEmpty()) {
            generatedItem.replace(
                "{element.patterns}",
                "Brak wzorów"
            )
        } else {
            val patterns = mutableSetOf<String>()

            for (pattern in info.patterns.sorted()) {
                patterns.add(pattern)
            }

            generatedItem.replace(
                "{element.patterns}",
                Joiner.on("<br>").join(patterns)
            )
        }

        if (replaceExamples) {
            val examples: Examples? = c.getAnnotation(Examples::class.java)
            generatedItem = if (examples == null) {
                generatedItem.replace(
                    "{element.examples}",
                    "Brak przykładów"
                )
            } else {
                generatedItem.replace(
                    "{element.examples}",
                    Joiner.on("<br>").join(examples.value)
                )
            }
        }
        if (replaceEvents) {
            val events: Events? = c.getAnnotation(Events::class.java)
            generatedItem = if (events == null) {
                generatedItem.replace(
                    "{element.events}",
                    "Brak wydarzeń"
                )
            } else {
                var eventLinks = ""

                for (eventName in events.value) {
                    eventLinks += ", <a href=\"events.html#" + eventName.toLowerCase()
                        .replace(" ", "-") + "\">" + eventName + "</a>"
                }

                generatedItem.replace(
                    "{element.events}",
                    eventLinks.replaceFirst(", ", "")
                )
            }
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
            val name1 = o1.c.getAnnotation(Name::class.java)
            val name2 = o2.c.getAnnotation(Name::class.java)
            if (name1 == null) return 0
            if (name2 == null) return 0
            return name1.value.compareTo(name2.value)
        }
    }

    private val syntaxElementComparator = SyntaxElementComparator()

    private fun <T> sortedIterator(it: Iterator<T>, comparator: Comparator<in T>): Iterator<T> {
        val list: MutableList<T> = ArrayList()
        while (it.hasNext()) {
            list.add(it.next())
        }
        Collections.sort(list, comparator)
        return list.iterator()
    }

}