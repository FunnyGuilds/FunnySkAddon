package pl.funnyskaddon.skript.expression.config

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.ExpressionType
import net.dzikoysk.funnyguilds.FunnyGuilds
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.expression.FunnyExpression

@FunnyDoc
@Name("Guild Item Sets")
@Description(
    "Zwraca listę nazw wszystkich zdefiniowanych zestawów wymagań do założenia " +
        "gildii (sekcja items.guildItemSets), posortowaną po priorytecie."
)
@Examples(
    "loop guild creation sets:",
    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%loop-value%\""
)
class ConfigGuildItemSetsExpression : FunnyExpression<String>() {

    companion object {
        init {
            Skript.registerExpression(
                ConfigGuildItemSetsExpression::class.java,
                String::class.java,
                ExpressionType.SIMPLE,
                "[all] guild [creation] [item] sets",
                "[all] [names of] guild [creation] [item] set names"
            )
        }
    }

    override fun get(event: Event): Array<String> {
        val itemsConfig = FunnyGuilds.getInstance().itemsConfiguration
        return itemsConfig.getSetsSortedByPriority()
            .mapNotNull { set -> itemsConfig.guildItemSets.entries.firstOrNull { it.value === set }?.key }
            .toTypedArray()
    }

    override fun isSingle(): Boolean = false

    override fun getReturnType(): Class<String> = String::class.java

    override fun toString(e: Event?, debug: Boolean): String = "guild creation sets"

}
