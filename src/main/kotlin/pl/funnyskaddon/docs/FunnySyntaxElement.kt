package pl.funnyskaddon.docs

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Events
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.SkriptEventInfo
import ch.njol.skript.lang.SyntaxElementInfo
import com.google.common.base.Joiner

class FunnySyntaxElement {
    val name: String?
    val description: String?
    val patterns: String?
    val examples: String?
    val events: Set<String>?

    constructor(
        name: String?,
        description: List<String>?,
        patterns: Set<String>?,
        examples: List<String>?,
        events: Set<String>
    ) {
        this.name = name
        if (description == null) {
            this.description = null
        } else {
            this.description = Joiner.on("\n").join(description.iterator())
        }
        this.patterns = Joiner.on("<br>").join(patterns)
        this.examples = Joiner.on("<br>").join(examples)
        this.events = events
    }

    constructor(info: SyntaxElementInfo<*>) {
        val c: Class<*> = info.c

        val name: Name? = c.getAnnotation(Name::class.java)
        if (name == null) {
            this.name = null
        } else {
            this.name = name.value
        }

        val description: Description? = c.getAnnotation(Description::class.java)
        if (description == null) {
            this.description = "Brak opisu"
        } else {
            this.description = Joiner.on("\n").join(description.value)
        }

        if (info.patterns == null || info.patterns.isEmpty()) {
            this.patterns = "Brak wzorów"
        } else {
            val patterns = mutableSetOf<String>()

            for (pattern in info.patterns) {
                patterns.add(pattern)
            }

            this.patterns = Joiner.on("<br>").join(patterns.sorted())
        }

        val examples: Examples? = c.getAnnotation(Examples::class.java)
        if (examples == null) {
            this.examples = "Brak przykładów"
        } else {
            this.examples = Joiner.on("<br>").join(examples.value)
        }

        val events: Events? = c.getAnnotation(Events::class.java)
        if (events == null) {
            this.events = null
        } else {
            this.events = events.value.toSet()
        }
    }

    constructor(info: SkriptEventInfo<*>) {
        if (info.name == null) {
            this.name = null
        } else {
            this.name = info.name
        }

        if (info.description.isNullOrEmpty()) {
            this.description = "Brak opisu"
        } else {
            this.description = Joiner.on("\n").join(info.description)
        }

        if (info.patterns.isNullOrEmpty()) {
            this.patterns = "Brak wzorów"
        } else {
            val patterns = mutableSetOf<String>()

            for (pattern in info.patterns) {
                patterns.add(pattern)
            }

            this.patterns = Joiner.on("<br>").join(patterns.sorted())
        }

        if (info.examples.isNullOrEmpty()) {
            this.examples = "Brak przykładów"
        } else {
            this.examples = Joiner.on("<br>").join(info.examples)
        }

        this.events = null
    }

}