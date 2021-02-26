package com.seregil13

data class GraphqlFragment internal constructor(
    internal val fields: List<GraphqlField>
) {
    class Builder {
        private val fields: MutableList<GraphqlField> = mutableListOf()

        fun field(name: String, block: GraphqlField.Builder.() -> Unit = {}) {
            fields.add(GraphqlField.Builder(name).apply(block).build())
        }

        fun build() = GraphqlFragment(fields)
    }
}

fun fragment(block: GraphqlFragment.Builder.() -> Unit): GraphqlFragment =
    GraphqlFragment.Builder().apply(block).build()
