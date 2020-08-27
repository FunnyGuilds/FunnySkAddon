package pl.funnyskaddon.schedulers

import pl.funnyskaddon.FunnySkAddon
import pl.funnyskaddon.util.VersionUtil

class UpdateCheckScheduler(val plugin: FunnySkAddon) {

    fun start() {
        val description = plugin.description
        plugin.server.scheduler.runTaskTimer(plugin, {
            if (plugin.config.getBoolean("update.check")) {
                val latestVersion: String? =
                    VersionUtil.getLatestVersion("https://raw.githubusercontent.com/MLGroupMC/FunnySkAddon/master/VERSION");
                if (description.version.equals(latestVersion, true).not()) {
                    plugin.logger.warning("[FSA] Wersja pluginu: " + description.version);
                    plugin.logger.warning("[FSA] Najnowsza wersja pluginu: " + latestVersion.toString());
                    plugin.logger.warning("[FSA] Wszystkie wersje: https://github.com/MLGroupMC/FunnySkAddon/releases/");
                }
            }
        }, 0, 216000)
    }

}