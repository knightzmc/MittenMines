package me.bristermitten.mittenmines.block

import kotlinx.serialization.Serializable

@Serializable
data class ConstantBlockPattern(val data: BlockData) : BlockPattern {
    override fun createData() = data
    override fun getItems() = listOf(data)
}
