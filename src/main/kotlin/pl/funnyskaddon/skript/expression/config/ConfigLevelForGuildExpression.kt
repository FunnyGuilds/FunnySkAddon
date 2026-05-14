package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
@Name("Level To Create Guild")
@Description(
    "Zwraca wymagany poziom gracza do założenia gildii dla wskazanego " +
        "zestawu (domyślnie \"default\")."
)
@Examples(
    "send \"%required level to create guild%\"",
    "send \"%required level for guild in set \"vip\"%\""
)
class ConfigLevelForGuildExpression : GuildItemSetExpression<Int>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigLevelForGuildExpression::class.java,
                Int::class.javaObjectType,
                ExpressionType.PROPERTY,
                "[required] level (to create|for) guild [[(with|in|of) set] %-string%]"
            )
        }
    }

    override fun get(event: Event): Array<Int> {
        val set = resolveSet(event) ?: return arrayOf(0)
        return arrayOf(set.requiredLevel)
    }

    override fun getReturnType(): Class<Int> = Int::class.javaObjectType

    override fun toString(e: Event?, debug: Boolean): String =
        "level required to create a guild in set ${setNameOrDefault(e, debug)}"

}
