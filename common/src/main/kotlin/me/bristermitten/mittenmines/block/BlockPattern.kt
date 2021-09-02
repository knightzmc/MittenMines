package me.bristermitten.mittenmines.block

interface BlockPattern {
    fun createData(): BlockData
    fun getItems() : List<BlockData>
}
