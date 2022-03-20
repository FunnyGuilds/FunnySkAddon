package pl.funnyskaddon.skript.expression.event

import ch.njol.skript.ScriptLoader
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import ch.njol.util.coll.CollectionUtils
import net.dzikoysk.funnyguilds.event.FunnyEvent
import org.bukkit.entity.Player
import org.bukkit.event.Event
import pl.funnyskaddon.extension.getPlayerOption

class EventExpressionUtil {

    companion object {

        init {
            EventValues.registerEventValue(
                FunnyEvent::class.java,
                Player::class.java, object : Getter<Player?, FunnyEvent>() {
                    override fun get(event: FunnyEvent): Player? {
                        if (event.doer == null) {
                            return null
                        }
                        return event.doer.getPlayerOption().orNull()
                    }
                }, 0
            )
        }

        fun isCurrentEvent(vararg events: Class<out Event?>?): Boolean {
            return CollectionUtils.containsAnySuperclass(events, *ScriptLoader.getCurrentEvents())
        }

    }

}