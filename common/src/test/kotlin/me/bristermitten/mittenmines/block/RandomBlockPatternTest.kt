package me.bristermitten.mittenmines.block

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.bukkit.Material
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

internal class RandomBlockPatternTest {

    @Test
    fun `test empty pattern throws an exception`() {
        assertThrows<IllegalArgumentException> {
            RandomBlockPattern(mapOf())
        }
    }

    @Test
    fun `test singleton pattern always returns the same result`() {
        val pattern = RandomBlockPattern(mapOf(
            BlockData(Material.STONE) to 1
        ))
        assertEquals(BlockData(Material.STONE), pattern.createData())
    }

    @Test
    fun `test singleton pattern always returns the same result with a different number`() {
        val pattern = RandomBlockPattern(mapOf(
            BlockData(Material.STONE) to Random.nextInt(0, Int.MAX_VALUE)
        ))
        assertEquals(BlockData(Material.STONE), pattern.createData())
    }

    @Test
    fun `test random pattern returns items with equal distribution`() {
        val pattern = RandomBlockPattern(mapOf(
            BlockData(Material.STONE) to 1,
            BlockData(Material.GRASS) to 1
        ))

        val data = List(100000) { pattern.createData() }
        assertTrue(data.any { it.type == Material.STONE })
        assertTrue(data.any { it.type == Material.GRASS })
    }

    @Test
    fun `test random pattern returns items with equal distribution with a very large size`() {
        val pattern = RandomBlockPattern(mapOf(
            BlockData(Material.STONE) to Int.MAX_VALUE,
            BlockData(Material.GRASS) to Int.MAX_VALUE
        ))

        val data = List(100000) { pattern.createData() }
        assertTrue(data.any { it.type == Material.STONE })
        assertTrue(data.any { it.type == Material.GRASS })
    }
}
