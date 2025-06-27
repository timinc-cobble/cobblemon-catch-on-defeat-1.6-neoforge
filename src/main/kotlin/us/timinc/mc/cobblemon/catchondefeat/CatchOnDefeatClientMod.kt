package us.timinc.mc.cobblemon.catchondefeat

import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.common.Mod
import us.timinc.mc.cobblemon.catchondefeat.CatchOnDefeatMod.MOD_ID
import us.timinc.mc.cobblemon.catchondefeat.config.CatchOnDefeatClientConfig
import us.timinc.mc.cobblemon.catchondefeat.config.ConfigBuilder

@Mod(MOD_ID, dist = [Dist.CLIENT])
object CatchOnDefeatClientMod {
    var config: CatchOnDefeatClientConfig = ConfigBuilder.load(CatchOnDefeatClientConfig::class.java, "${MOD_ID}_client")
}