package me.bristermitten.mittenmines.block

import kotlinx.serialization.Serializable
import me.bristermitten.mittenmines.util.minMax

@Serializable
class RandomBlockPattern : BlockPattern {

    val all: List<BlockData>

    constructor(items: List<BlockData>) {
        require(items.isNotEmpty())
        this.all = items
    }

    constructor(weights: Map<BlockData, Int>) {
        this.all = generateAll(weights)
    }

    private fun generateAll(weights: Map<BlockData, Int>): List<BlockData> {
        require(weights.isNotEmpty())
        if (weights.size == 1) {
            return listOf(weights.keys.first())
        }


        // We try and simplify the ratios here to make the list a bit smaller
        val (min, max) = minMax(weights.entries) { it.value }
        val gcd = min.value.toBigInteger().gcd(max.value.toBigInteger()).toInt()
        val reducedWeights = weights.map {
            it.key to it.value / gcd
        }

        return reducedWeights.flatMap { (key, value) -> List(value) { key } }
    }

    override fun createData(): BlockData {
        return all.random()
    }

    override fun getItems(): List<BlockData> = all

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RandomBlockPattern) return false

        if (all != other.all) return false

        return true
    }

    override fun hashCode(): Int {
        return all.hashCode()
    }


}
