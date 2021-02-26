package com.seregil13

internal fun String.indent(numIndents: Int): String {
    val temp = this
    return buildString {
        repeat(numIndents) {
            append("\t")
        }
        append(temp)
    }
}
