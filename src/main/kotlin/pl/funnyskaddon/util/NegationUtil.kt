package pl.funnyskaddon.util

class NegationUtil {

    companion object {

        fun negation(value: Boolean?, negate: Boolean?): Boolean {
            return negate != value
        }

    }

}