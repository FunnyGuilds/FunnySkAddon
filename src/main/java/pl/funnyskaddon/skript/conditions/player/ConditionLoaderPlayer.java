package pl.funnyskaddon.skript.conditions.player;

import ch.njol.skript.Skript;

public class ConditionLoaderPlayer {

    public ConditionLoaderPlayer() {
        Skript.registerCondition(PlayerHasGuild.class, "(player |)%offlineplayer% has guild",
                "(player |)%offlineplayer% (does not|doesn't) have guild");
        Skript.registerCondition(PlayerIsDeputy.class, "(player |)%offlineplayer% is deputy",
                "(player |)%offlineplayer% is(n't| not) deputy");
        Skript.registerCondition(PlayerIsOwner.class, "(player |)%offlineplayer% is guild owner",
                "(player |)%offlineplayer% is(n't| not) guild owner");
        Skript.registerCondition(PlayerIsInGuildRegion.class, "(player |)%player% is in guild region %object%",
                "(player |)%player% is(n't| not) in guild region %object%");
        Skript.registerCondition(PlayerIsInGuild.class, "(player |)%offlineplayer% is in guild %object%",
                "(player |)%offlineplayer% is(n't| not) in guild %object%");
    }

}
