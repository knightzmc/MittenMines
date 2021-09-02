package me.bristermitten.mittenmines.compat

class DelegatingVersionCompat(
    override val blockPlacer: BlockPlacer,
    override val regionSelection: RegionSelection,
    override val blockDataFactory: BlockDataFactory,
) : VersionCompat {

    companion object {
        fun from(other: VersionCompat) = DelegatingVersionCompat(
            other.blockPlacer,
            other.regionSelection,
            other.blockDataFactory
        )
    }

    fun copy(
        blockPlacer: BlockPlacer = this.blockPlacer,
        regionSelection: RegionSelection = this.regionSelection,
        blockDataFactory: BlockDataFactory = this.blockDataFactory,
    ) = DelegatingVersionCompat(blockPlacer, regionSelection, blockDataFactory)
}
