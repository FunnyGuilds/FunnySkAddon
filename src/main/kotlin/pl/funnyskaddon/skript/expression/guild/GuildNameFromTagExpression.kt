package pl.funnyskaddon.skript.expression.guild

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import net.dzikoysk.funnyguilds.FunnyGuilds
import net.dzikoysk.funnyguilds.guild.Guild
import org.bukkit.event.Event
import pl.funnyskaddon.docs.FunnyDoc
import pl.funnyskaddon.skript.getValueOption

@FunnyDoc
@Name("Guild Name From Tag")
@Description("Zwraca nazwę gildii po jej tagu<br>", "<s>Nie wiadomo po co</s>")
@Examples(
    "send \"%guild name from tag \"AD4U\"%\"",
    "set {_name} to guild name with tag \"AD4U\""
)
class GuildNameFromTagExpression : SimpleExpression<String>() {

    private lateinit var tagExpression: Expression<String>

    companion object {
        init {
            Skript.registerExpression(
                GuildNameFromTagExpression::class.java,
                String::class.javaObjectType,
                ExpressionType.PROPERTY,
                "guild name from tag %string%",
                "guild name with tag %string%"
            )
        }
    }

    override fun init(
        expression: Array<Expression<*>>,
        matchedPattern: Int,
        isDelayed: Kleenean,
        parseResult: SkriptParser.ParseResult
    ): Boolean {
        tagExpression = expression[0] as Expression<String>
        return true
    }

    override fun get(event: Event): Array<String>? {
        return event.getValueOption(tagExpression)
            .flatMap(FunnyGuilds.getInstance().guildManager::findByTag)
            .map(Guild::getName)
            .map { value -> arrayOf(value) }
            .orNull
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String? {
        return null
    }

    override fun getReturnType(): Class<out String> {
        return String::class.javaObjectType
    }

}