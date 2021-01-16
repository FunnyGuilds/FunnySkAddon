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
import ch.njol.util.NonNullPair
import java.lang.StringBuilder


class FunnyHTMLGenerator(private val template: File, val output: File) {

    var skeleton: String

    init {
        this.skeleton = readFile(File("$template/template.html"))
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

    fun generate() {
        var expressionsData: String = this.skeleton

        var expressionsItems: String = ""
        val expressionItem: String = readFile(File("$template/templates/items-list-item.html"))

        var docItems: String = ""
        val docItem: String = readFile(File("$template/templates/doc-item.html"))

        for (expression in Skript.getExpressions()) {
            if (expression.c.getAnnotation(NoDoc::class.java) != null || expression.c.getAnnotation(FunnyDoc::class.java) == null) {
                continue
            }
            expressionsItems += generateItemsListItem(expressionItem, expression)
            docItems += generateDocItem(docItem, expression)
        }
        expressionsData = StringUtils.replace(expressionsData, "{doc.items-list}", expressionsItems)
        expressionsData = StringUtils.replace(expressionsData, "{doc.items}", docItems)
        writeFile(File("$output/expressions.html"), expressionsData);
    }

    private fun generateItemsListItem(itemsListItemSkeleton: String, info: SyntaxElementInfo<*>): String {
        var generatedItem: String = itemsListItemSkeleton
        val c: Class<*> = info.c

        val name: Name = c.getAnnotation(Name::class.java)
        generatedItem = generatedItem.replace("{element.name}", name.value)
            .replace("{element.id}", name.value.toLowerCase().replace(" ", "-"))

        return generatedItem
    }

    fun generateDocItem(docItemSkeleton: String, info: SyntaxElementInfo<*>): String {
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


        return generatedItem
    }

}