package me.bristermitten.mittenmines.block

import me.bristermitten.mittenmines.util.minMax

class RandomBlockPattern(weights: Map<BlockData, Int>) : BlockPattern {

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

    val all = generateAll(weights)

    override fun createData(): BlockData {
        return all.random()
    }
}
