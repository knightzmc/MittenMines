package me.bristermitten.mittenmines.player

import com.google.inject.Singleton
import org.bukkit.entity.Player
import java.util.*

@Singleton
class MinesPlayerStorage {
    private val players = mutableMapOf<UUID, MinesPlayer>()


    operator fun get(uuid: UUID) = getPlayer(uuid)
    operator fun get(player: Player) = getPlayer(player.uniqueId)
    fun getPlayer(uuid: UUID) = players.getOrPut(uuid) {
        MinesPlayer(uuid)
    }
}
