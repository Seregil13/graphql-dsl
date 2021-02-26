package com.seregil13

enum class GraphqlVariableType(private val displayName: String) {
    GRAPHQL_INT("Int"),
    GRAPHQL_STRING("String"),
    GRAPHQL_FLOAT("Float"),
    GRAPHQL_BOOLEAN("Boolean");

    override fun toString(): String = this.displayName
}
