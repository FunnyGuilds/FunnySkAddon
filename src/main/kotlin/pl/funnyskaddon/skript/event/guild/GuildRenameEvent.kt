package pl.funnyskaddon.skript.event.guild

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import pl.funnyskaddon.docs.FunnyDoc

@FunnyDoc
class GuildRenameEvent : SimpleEvent() {

    companion object {
        init {
            Skript.registerEvent(
                "Guild Rename",
                GuildRenameEvent::class.java,
                net.dzikoysk.funnyguilds.event.guild.GuildPreRenameEvent::class.java,
                "guild rename"
            ).description("Wywoływany, kiedy zmieni się nazwa gildii")
                .examples(
                    "on guild rename:",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%cause%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%player%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%guild%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%old name%\"",
                    "&nbsp;&nbsp;&nbsp;&nbsp;send \"%new name%\""
                )
        }
    }

}