package us.timinc.mc.cobblemon.catchondefeat

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.platform.events.PlatformEvents
import net.minecraft.resources.ResourceLocation
import net.neoforged.fml.common.Mod
import us.timinc.mc.cobblemon.catchondefeat.config.CatchOnDefeatConfig
import us.timinc.mc.cobblemon.catchondefeat.config.ConfigBuilder
import us.timinc.mc.cobblemon.catchondefeat.customproperties.CatchOnDefeatProperties
import us.timinc.mc.cobblemon.catchondefeat.event.handler.AttemptJoinOnDefeatHandler
import us.timinc.mc.cobblemon.catchondefeat.event.handler.CancelPokeballHitWhenOnlyJoinByDefeatHandler
import us.timinc.mc.cobblemon.catchondefeat.registry.CatchOnDefeatNetwork

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

        CobblemonEvents.BATTLE_FAINTED.subscribe(Priority.LOWEST, AttemptJoinOnDefeatHandler::handle)
        CobblemonEvents.THROWN_POKEBALL_HIT.subscribe(
            Priority.LOWEST,
            CancelPokeballHitWhenOnlyJoinByDefeatHandler::handle
        )
        CatchOnDefeatNetwork
    }

    fun modResource(name: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
}