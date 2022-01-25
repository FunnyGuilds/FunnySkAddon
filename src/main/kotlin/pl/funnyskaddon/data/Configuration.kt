package pl.funnyskaddon.data

import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.*

@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class Configuration : OkaeriConfig() {

    @CustomKey("update")
    var update = Update()

    @CustomKey("messages")
    var messages = Messages()

    @Comment("Tryb developerski, służy do generowania dokumentacji, nie ma raczej sensu ustawiać na true")
    @CustomKey("dev-mode")
    var devMode = false

    class Update : OkaeriConfig() {
        @Comment(
            "Update'y wychodzą rzadko ale zawierają dużo, radzimy więc zostawić to włączone",
            "Jesli mimo najnowszej wersji wiadomość jest dalej wysyłana mozna wyłączyc"
        )
        @CustomKey("check")
        var updateCheck = true

        @Comment("Ustawione na false zwraca również \"niepełne\" wydania (Pre-Release)")
        @CustomKey("only-full-releases")
        var onlyFullReleases = true

        @Comment("Czas co jaki ma być sprawdzana dostępność aktualizacji w minutach")
        @CustomKey("time")
        var checkTime: Int = 180
    }

    class Messages : OkaeriConfig() {
        @Comment("Wiadomość wsyłana gdy gracz nie ma uprawnień do komendy /funnyskaddon")
        @CustomKey("no-perm")
        var noPerm: String? = "&bFunnySkAddon &8> &7Niestety nie posiadasz uprawnien."
    }

}