package com.seregil13

data class GraphqlVariable internal constructor(
    val name: String,
    val type: GraphqlVariableType,
    val isNullable: Boolean
) {

    fun displayDefinition(): String {
        return "\$$name: $type${if (isNullable) "" else "!"}"
    }

    fun displayArguments(): String {
        return "$name: \$$name"
    }

    @GraphqlDsl
    class Builder {
        lateinit var name: String
        lateinit var type: GraphqlVariableType
        var nullable: Boolean = false

        fun build(): GraphqlVariable = GraphqlVariable(name, type, nullable)
    }
}
