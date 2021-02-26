package com.seregil13

data class GraphqlInterface internal constructor(
    private val name: String,
    private val fields: List<GraphqlField>
) {

    fun display(separator: String): String =
        "... on $name {$separator ${fields.joinToString { it.display(separator) }} $separator}"

    fun prettyPrint(indentLevel: Int): String = buildString {
        append("... on $name { \n".indent(indentLevel))
        append(fields.joinToString { it.prettyPrint(indentLevel + 1) })
        append("}".indent(indentLevel))
    }

    @GraphqlDsl
    class Builder(private val name: String) {
        private val fields: MutableList<GraphqlField> = mutableListOf()

        fun field(name: String, block: GraphqlField.Builder.() -> Unit) {
            fields.add(GraphqlField.Builder(name).apply(block).build())
        }

        fun build() = GraphqlInterface(name, fields)
    }
}
