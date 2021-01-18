# FunnySkAddon

#### O Pluginie: Dodatek do pluginu Skript dodający wsparcie pluginu gildyjnego FunnyGuilds
#### Twórcy: Peridot, LloydPL
#### Dokumentacja: https://funnyskaddon.pl
#### Dokumentacja (old): https://mlgroupmc.github.io/
#### bStats: https://bstats.org/plugin/bukkit/FunnySkAddon-Extended/6363

### Changelog:
  * 1.0:
    * Pierwsze wydanie dodatku
  * 1.1:
    * Naprawiono błąd w 1 klasie
    * Usunięto klasy PlayerGuild... i wdrożono funkcje gildii po graczu do klas Guild...
    * Dodanie metody która umożliwia zwrócenie objektu guild po podanym graczu
    * Dodanie wsparcia bstats
  * 1.2:
    * Nowe Effect'y
    * Nowy Condition
    * Nowe Expression'y
  * 1.5:
    * Dodanie informacji o nowej wersji (można wyłączyć w config.yml)
    * Zmiana nazw niektórych klas
    * Naprawienie kilku błędów
    * Pomniejsze zmiany
  * 1.6:
    * Poprawiono składnie (zalecamy zapoznać się z dokumentacją !)
    * Poprawiono pobieranie graczy offline
  * 1.7:
    * Zrobiono lepszą dokumentacje https://mlgroup.pl/funnyskaddon/doc/home
    * Zmieniono troche składnie
    * Dodano autoaktualizacje topki (do zmianny w configu)
    * Zmiana wiadomości przy ładowaniu
  * 1.7.1:
    * Usunięto niepotrzebne wiadomości przy włączaniu pluginu
    * Dodano nowe expression'y do pobierania itemów/expa/pieniędzy potrzebnych do założenia gildii z wariantami dla vipów
      * required items to create guild
      * required exp to create guild
      * required money to create guild
      * required items to create guild for vip
      * required exp to create guild for vip
      * required money to create guild for vip
  * 1.8:
    * Zmieniono sposób sortowania gildii i graczy w rankingu
    * Od teraz plugin działa na wersji FunnyGuilds 4.4.0 Tribute
    * Od teraz zmienne które zwracały w jakiej pozycji w rankingu jest gracz/gildia zwracają liczbę od 1 a nie od 0, 0 będzie jeżeli gracza/gildii nie ma w topce
    * Naprawiono błąd który po włączeniu pluginu pierwszy raz bez wygenerowanych configów funnyguilds wywalał długi błąd w konosli
  * 1.8.1:
    * Usunięto niepotrzebną klasę
    * Zmieniono strukturę pluginu (mało ważne dla normalnego użytkownika)
    * Dodano event kill points change (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Events.html#on-kill-points-change)
    * Dodano automatyczne powiadomienie o tym że wyszła nowa wersja pluginu (do wyłączenia w configu pod update.check) co 3 godziny nie tylko przy włączeniu serwera
  * 1.8.2:
    * Dodano zmienną last top update (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#last-top-update)
  * 1.8.3:
    * Dodano zmienną guild remaining date (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#guild-remaining-date)
    * Dodano zmienną guild area (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#guild-area)
    * Dodano zmienną guild enlarge level (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#guild-enlarge-level)
    * Dodano zmienną guild born (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#guild-born)
    * Dodano zmienną required to enlarge guild (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#required-items-to-enlarge-guild)
    * Dodano zmienną required items to extension validity(więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#required-items-to-extension-validity)
    * Dodano zmienną required items to teleport home (więcej informacji https://mlgroup.pl/funnyskaddon/doc/Expressions.html#required-items-to-teleport-home)
    * Naprawiono błąd który powodował że effecty add/remove które dodawały lub odejmowały punkty/smierci/zabojstwa nie dzialaly
    * Guild remaining time zwraca już poprawny czas
    * Guild members nie zwraca już 2x tego samego gracza
  * 1.8.4:
    * Dodano eventy:
      * [On] enter guild region https://mlgroup.pl/funnyskaddon/doc/Events.html#on-enter-guild-region
      * [On] leave guild region https://mlgroup.pl/funnyskaddon/doc/Events.html#on-leave-guild-region
    * Od teraz do topki są zaliczane gildie z minimalną liczbą członków ustawioną w konfiguracji funnyguilds jako guild-min-members
    * Dodano nowe błędy do naprawienia później kappa
  * 1.8.8
    * Plugin oficjalnie wspiera funnyguilds 4.4.2
    * Plugin będzie teraz budowany w gradle co minimalnie ułatwi pracę (mało ważne dla normalnego użytkownika)
  * 1.8.9
    * Inne pluginy od teraz wykrywają FunnySkAddon jako faktyczny dodatek do pluginu skript
    * Dodano komendę diagnostyczną /funnyskaddon (alias /fsa) z uprawnieniem funnyskaddon.cmd
  * 1.9
    * Aktualizacja bStats ze względu na zmiany w klasie Metrics i rozpoczęcie prowadzenie przez bStats systemu id pluginów
  * 1.9.3
    * Posprzątanie odrobinę kodu pluginu
    * Użycie systemu obliczania pozycji w rankingu bezpośrednio z pluginu FunnyGuilds zamiast własnego systemu
      * Usunięcie wyrażenia *%last top update%*
      * Usunięcie efektu *update guild tops*
    * Dodanie do konfiguracji możliwości ustawienia własnej wiadomości o braku uprawnień
  * 1.9.3.1
    * Przeniesienie klasy Metrics.java (bStats) do innego sub-package'a w celu zapobiegnięcie nadpisywaniu jej przez inne pluginy
  * 2.0-Beta
    * Przepisanie całego dodatku na kotlina
    * Niektóre wydarzenia, warunki, efekty i wyrażenia zostały zmienione, jeśli coś przestało działać można znaleźć odpowiednik w nowej dokumentacji (https://funnyskaddon.pl/) odpowiednik
    * Dużo nowych wydarzeń, efektów i wyrażeń
    * Niektóre wydarzenia, warunki, efekty i wyrażenia zostały zmienione, jeśli coś przestało działać można znaleźć odpowiednik w nowej dokumentacji (https://funnyskaddon.pl/) odpowiednik
    * Od teraz w miejscach gdzie podaje się gildie można podać jej tag lub lokalizacje
    * Naprawienie wielu błędów
    * Od teraz można znaleźć dokumentacje dodatku pod: https://funnyskaddon.pl/
    * Dodatek zmienił licencje na *GNU General Public License v3.0*
### Wymagania:
  * 1.0 - 1.7.1:
    * Java 8
    * Spigot 1.8 lub nowszy
    * Skript
    * FunnyGuilds: https://ci.kacperduras.pl/job/FunnyGuilds/100/ lub nowszy
  * 1.8-1.8.3:
    * Java 8
    * Spigot 1.8 lub nowszy
    * Skript
    * FunnyGuilds 4.4.0 Tirbute lub nowszy
  * 1.8.4:
    * Java 8
    * Spigot 1.8 lub nowszy
    * Skript
    * FunnyGuilds https://ci.insertt.pl/job/FunnyGuilds
  * 1.8.8
    * Java 8
    * Spigot 1.8 lub nowszy
    * Skript
    * Funnyguilds 4.4.2 lub nowszy
  * 1.8.9-1.9
    * Java 8
    * Spigot 1.8.8 - 1.14.4
    * Skript
    * FunnyGuilds 4.4.2 lub nowszy
  * 1.9.3-1.9.3.1
    * Java 8
    * Spigot 1.8.8 - 1.15.2
    * Skript
    * FunnyGuilds 4.5.1 lub nowszy
  * 2.0-Beta
    * Java 8
    * Spigot 1.8.8 lub nowszy
    * Skript
    * FunnyGuilds 4.8.1 lub nowszy
