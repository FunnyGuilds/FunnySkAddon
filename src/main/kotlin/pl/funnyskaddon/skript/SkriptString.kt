package pl.funnyskaddon.skript

class SkriptString(val string: String) {

    override fun hashCode(): Int {
        return string.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return string == other
    }

    override fun toString(): String {
        return string
    }

}