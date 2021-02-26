package com.seregil13

data class GraphqlQuery internal constructor(
    val queryName: String,
    val fields: List<GraphqlField>,
    val variables: List<GraphqlVariable>
) {
    override fun toString(): String {
        return display()
    }

    fun display(separator: String = " "): String = buildString {
        append("query $queryName")

        val prefix = if (variables.isNotEmpty()) "(" else ""
        val postfix = if (variables.isNotEmpty()) ")" else ""

        append(variables.joinToString(prefix = prefix, postfix = postfix) { it.displayDefinition() })
        append("{$separator$queryName")
        append(variables.joinToString(prefix = prefix, postfix = postfix) { it.displayArguments() })
        append("{$separator")
        fields.forEach {
            append(it.display(separator))
        }

        append("}}")
    }

    private fun variableDefinition(): String = when {
        variables.isNotEmpty() -> variables.joinToString(prefix = "(", postfix = ")") { it.displayDefinition() }
        else -> ""
    }

    private fun variableArguments(): String = when {
        variables.isNotEmpty() -> variables.joinToString(prefix = "(", postfix = ")") { it.displayArguments() }
        else -> ""
    }

    fun prettyPrint(): String = buildString {
        append("query $queryName${variableDefinition()} {\n")
        append("\t$queryName${variableArguments()} {\n")
        fields.forEach {
            append(it.prettyPrint(2))
        }
        append("\t}\n}")
    }
//        """

//    $queryName${variableArguments()} {
//        ${fields.joinToString(separator = "") { it.display("\n") }}
//    }
// }
//        """.trimIndent()

    @GraphqlDsl
    class Builder(private val queryName: String) {
        private val fields: MutableList<GraphqlField> = mutableListOf()
        private val variables: MutableList<GraphqlVariable> = mutableListOf()

        fun fragment(fragment: GraphqlFragment) {
            fields.addAll(fragment.fields)
        }

        fun field(name: String, block: GraphqlField.Builder.() -> Unit) {
            fields.add(GraphqlField.Builder(name).apply(block).build())
        }

        fun variable(block: GraphqlVariable.Builder.() -> Unit) {
            variables.add(GraphqlVariable.Builder().apply(block).build())
        }

        fun build() = GraphqlQuery(queryName, fields, variables)
    }
}
