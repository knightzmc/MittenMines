package me.bristermitten.mittenmines.block

@JvmInline
value class ConstantBlockPattern(val data: BlockData) : BlockPattern {
    override fun createData() = data
}
