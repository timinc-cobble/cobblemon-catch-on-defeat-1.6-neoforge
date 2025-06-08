package us.timinc.mc.cobblemon.catchondefeat

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.platform.events.PlatformEvents
import net.neoforged.fml.common.Mod
import us.timinc.mc.cobblemon.catchondefeat.config.CatchOnDefeatConfig
import us.timinc.mc.cobblemon.catchondefeat.config.ConfigBuilder
import us.timinc.mc.cobblemon.catchondefeat.customproperties.CatchOnDefeatProperties
import us.timinc.mc.cobblemon.catchondefeat.eventhandlers.BattleFaintedHandler
import us.timinc.mc.cobblemon.catchondefeat.eventhandlers.ThrownPokeballHitHandler

@Mod(CatchOnDefeatMod.MOD_ID)
object CatchOnDefeatMod {
    const val MOD_ID = "catchondefeat"

    @Suppress("MemberVisibilityCanBePrivate", "JoinDeclarationAndAssignment")
    var config: CatchOnDefeatConfig

    init {
        config = ConfigBuilder.load(CatchOnDefeatConfig::class.java, MOD_ID)

        var initialized = false
        PlatformEvents.SERVER_STARTING.subscribe { evt ->
            if (initialized) return@subscribe
            initialized = true
            CatchOnDefeatProperties.register()
        }

        CobblemonEvents.BATTLE_FAINTED.subscribe(Priority.LOWEST, BattleFaintedHandler::handle)
        CobblemonEvents.THROWN_POKEBALL_HIT.subscribe(Priority.LOWEST, ThrownPokeballHitHandler::handle)
    }
}