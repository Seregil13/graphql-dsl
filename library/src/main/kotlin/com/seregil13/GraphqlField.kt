package com.seregil13

data class GraphqlField(
    private val name: String,
    private val subfields: List<GraphqlField>,
    private val interfaces: List<GraphqlInterface>
) {
    override fun toString(): String {
        return display("")
    }

    fun display(separator: String): String = buildString {
        append("$name$separator")
        if (subfields.isNotEmpty()) append(
            subfields.joinToString(
                prefix = "{$separator",
                postfix = "$separator}",
                separator = separator
            ) { it.display(separator) }
        )
        if (interfaces.isNotEmpty()) append(interfaces.joinToString { it.display(separator) })
    }

    fun prettyPrint(indentLevel: Int): String = buildString {
        append(name.indent(indentLevel))
        if (subfields.isNotEmpty()) {
            append(" {\n")
            append(subfields.joinToString(separator = "") { it.prettyPrint(indentLevel + 1) })
            append("}\n".indent(indentLevel))
        }
        if (interfaces.isNotEmpty()) {
            append(" {\n")
            append(interfaces.joinToString { it.prettyPrint(indentLevel + 1) })
            append("\n".indent(indentLevel))
            append("}\n".indent(indentLevel))
        }

        if (subfields.isEmpty() && interfaces.isEmpty()) append("\n".indent(indentLevel))
    }

    @GraphqlDsl
    class Builder(private val name: String) {
        private val fields: MutableList<GraphqlField> = mutableListOf()
        private val interfaces: MutableList<GraphqlInterface> = mutableListOf()

        fun fragment(fragment: GraphqlFragment) {
            fields.addAll(fragment.fields)
        }

        fun field(name: String, block: Builder.() -> Unit = {}) {
            fields.add(Builder(name).apply(block).build())
        }

        fun on(name: String, block: GraphqlInterface.Builder.() -> Unit) {
            interfaces.add(GraphqlInterface.Builder(name).apply(block).build())
        }

        fun build() = GraphqlField(name, fields, interfaces)
    }
}
