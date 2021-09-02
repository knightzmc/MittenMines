package me.bristermitten.mittenmines.mine

import me.bristermitten.mittenmines.block.BlockPattern
import me.bristermitten.mittenmines.compat.BlockPlacer
import me.bristermitten.mittenmines.mine.storage.MineStorage
import me.bristermitten.mittenmines.util.OperationFailedException
import me.bristermitten.mittenmines.util.Result
import me.bristermitten.mittenmines.util.fail
import me.bristermitten.mittenmines.util.ok
import javax.inject.Inject

class MineManager @Inject constructor(private val mineStorage: MineStorage, private val blockPlacer: BlockPlacer) {
    fun rename(mine: Mine, newName: String): Result<Unit> {
        if (mineStorage.getAll().any { it.name == newName }) {
            return fail(OperationFailedException(mapOf("{name}" to newName)) {
                it.errors.mineNameExists
            })
        }
        mine.name = newName
        return ok(Unit)
    }

    fun fill(mine: Mine) {
        blockPlacer.setRegion(mine.pattern, mine.miningRegion)
    }

    fun setPattern(mine: Mine, pattern: BlockPattern) {
        mine.pattern = pattern
        fill(mine)
    }

    suspend fun delete(mine: Mine) {
        mineStorage.delete(mine.id)
    }
}
