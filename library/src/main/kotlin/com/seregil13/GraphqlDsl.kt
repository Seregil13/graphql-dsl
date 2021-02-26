package com.seregil13

fun graphqlQuery(queryName: String, block: GraphqlQuery.Builder.() -> Unit) = GraphqlQuery.Builder(queryName).apply(block).build()

@DslMarker
annotation class GraphqlDsl
