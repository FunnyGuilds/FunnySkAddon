package pl.funnyskaddon.skript.events.guild;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Timespan;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.event.guild.*;
import net.dzikoysk.funnyguilds.event.guild.ally.GuildAcceptAllyInvitationEvent;
import net.dzikoysk.funnyguilds.event.guild.ally.GuildBreakAllyEvent;
import net.dzikoysk.funnyguilds.event.guild.ally.GuildRevokeAllyInvitationEvent;
import net.dzikoysk.funnyguilds.event.guild.ally.GuildSendAllyInvitationEvent;
import net.dzikoysk.funnyguilds.event.guild.member.*;
import org.bukkit.entity.Player;
import pl.funnyskaddon.core.fix.GuildCreateEventFix;

public class EventLoaderGuild {
	
	public EventLoaderGuild() {
		Skript.registerEvent("guild revoke ally invitation", SimpleEvent.class, GuildRevokeAllyInvitationEvent.class, "guild revoke ally invitation"); 
		Skript.registerExpression(GuildRevokeAllyInvitationDoer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildRevokeAllyInvitationGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildRevokeAllyInvitationPossibleAlly.class, Guild.class, ExpressionType.SIMPLE,
				"possible ally");
		
		Skript.registerEvent("guild member revoke invitation", SimpleEvent.class, GuildMemberRevokeInviteEvent.class, "guild member revoke invite");
		Skript.registerExpression(GuildMemberRevokeInviteMember.class, Player.class, ExpressionType.SIMPLE,
				"possible member");
		Skript.registerExpression(GuildMemberRevokeInvitePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberRevokeInviteGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild lives change", SimpleEvent.class, GuildLivesChangeEvent.class, "guild lives change");
		Skript.registerExpression(GuildLivesChangePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildLivesChangeGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildLivesChangeCurrentLives.class, Integer.class, ExpressionType.SIMPLE,
				"lives");
		Skript.registerExpression(GuildLivesChangeAttackerGuild.class, Guild.class, ExpressionType.SIMPLE,
				"attacking guild");
		
		Skript.registerEvent("guild ally invitation accept", SimpleEvent.class, GuildAcceptAllyInvitationEvent.class, "guild ally invitation accept"); 
		Skript.registerExpression(GuildAcceptAllyInvitationDoer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildAcceptAllyInvitationGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildAcceptAllyInvitationNewAlly.class, Guild.class, ExpressionType.SIMPLE,
				"allied guild");
		
		Skript.registerEvent("guild ally breakup", SimpleEvent.class, GuildBreakAllyEvent.class, "guild ally breakup"); 
		Skript.registerExpression(GuildAcceptAllyInvitationDoer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildBreakAllianceGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildBreakAllianceFormerAlly.class, Guild.class, ExpressionType.SIMPLE,
				"former ally");
		
		Skript.registerEvent("guild send ally invitation", SimpleEvent.class, GuildSendAllyInvitationEvent.class, "guild send ally invitation"); 
		Skript.registerExpression(GuildSendAllyInvitationDoer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildSendAllyInvitationGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildSendAllyInvitationPossibleAlly.class, Guild.class, ExpressionType.SIMPLE,
				"possible ally");
		
		Skript.registerEvent("guild ban", SimpleEvent.class, GuildBanEvent.class, "guild ban"); 
		Skript.registerExpression(GuildBanEventPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildBanEventGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildBanEventReason.class, String.class, ExpressionType.SIMPLE,
				"reason");
		Skript.registerExpression(GuildBanEventTime.class, Timespan.class, ExpressionType.SIMPLE,
				"time");
		
		Skript.registerEvent("guild unban", SimpleEvent.class, GuildUnbanEvent.class, "guild unban"); 
		Skript.registerExpression(GuildUnbanEventPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildUnbanEventGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild create", SimpleEvent.class, GuildCreateEventFix.class, "guild create"); 
		Skript.registerExpression(GuildCreatePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildCreateGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild delete", SimpleEvent.class, GuildDeleteEvent.class, "guild delete"); 
		Skript.registerExpression(GuildDeletePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildDeleteGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild rename", SimpleEvent.class, GuildRenameEvent.class, "guild rename"); 
		Skript.registerExpression(GuildRenamePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildRenameGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildRenameNewGuild.class, String.class, ExpressionType.SIMPLE,
				"new name");
		
		Skript.registerEvent("guild move", SimpleEvent.class, GuildMoveEvent.class, "guild move"); 
		Skript.registerExpression(GuildMovePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMoveGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild member join", SimpleEvent.class, GuildMemberJoinEvent.class, "guild member join"); 
		Skript.registerExpression(GuildMemberJoinMember.class, Player.class, ExpressionType.SIMPLE,
				"member");
		Skript.registerExpression(GuildMemberJoinPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberJoinGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
				
		Skript.registerEvent("guild member kick", SimpleEvent.class, GuildMemberKickEvent.class, "guild member kick");
		Skript.registerExpression(GuildMemberKickMember.class, Player.class, ExpressionType.SIMPLE,
				"kicked");
		Skript.registerExpression(GuildMemberKickPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberKickGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild member leave", SimpleEvent.class, GuildMemberLeaveEvent.class, "guild member leave");
		Skript.registerExpression(GuildMemberLeavePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberLeaveGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		
		Skript.registerEvent("guild member deputy", SimpleEvent.class, GuildMemberDeputyEvent.class, "guild member deputy");
		Skript.registerExpression(GuildMemberDeputyMember.class, Player.class, ExpressionType.SIMPLE,
				"new deputy");
		Skript.registerExpression(GuildMemberDeputyPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberDeputyGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
				
		Skript.registerEvent("guild member invite", SimpleEvent.class, GuildMemberInviteEvent.class, "guild member invite");
		Skript.registerExpression(GuildMemberInviteMember.class, Player.class, ExpressionType.SIMPLE,
				"possible member");
		Skript.registerExpression(GuildMemberInvitePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberInviteGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild member leader", SimpleEvent.class, GuildMemberLeaderEvent.class, "guild member leader change");
		Skript.registerExpression(GuildMemberLeaderMember.class, Player.class, ExpressionType.SIMPLE,
				"new leader");
		Skript.registerExpression(GuildMemberLeaderPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildMemberLeaderGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild extend validity", SimpleEvent.class, GuildExtendValidityEvent.class, "guild extend validity");
		Skript.registerExpression(GuildExtendValidityPlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildExtendValidityGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		Skript.registerExpression(GuildExtendValidityTime.class, Timespan.class, ExpressionType.SIMPLE,
				"extend time");
		
		Skript.registerEvent("guild enlarge", SimpleEvent.class, GuildEnlargeEvent.class, "guild enlarge");
		Skript.registerExpression(GuildEnlargePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildEnlargeGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
		
		Skript.registerEvent("guild base change", SimpleEvent.class, GuildBaseChangeEvent.class, "guild base change");
		Skript.registerExpression(GuildBaseChangePlayer.class, Player.class, ExpressionType.SIMPLE,
				"player");
		Skript.registerExpression(GuildBaseChangeGuild.class, Guild.class, ExpressionType.SIMPLE,
				"guild");
	}
	
}
