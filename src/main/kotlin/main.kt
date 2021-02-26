import com.seregil13.fragment
import com.seregil13.graphqlQuery

fun main() {
    val typeSimpleFragment = fragment {
        field("id")
        field("name")
        field("color")
        field("icon")
    }

    val pokemonSimpleFragment = fragment {
        field("id")
        field("number")
        field("name")
        field("sprite")
        field("primaryType") {
            fragment(typeSimpleFragment)
        }
        field("secondaryType") {
            fragment(typeSimpleFragment)
        }
        field("evolvesTo") {
            field("details") {
                on("SpecificItem") {
                    field("item") {
                        field("name")
                    }
                }
            }
        }
    }

    val pokemonList = graphqlQuery(queryName = "pokemons") {
        fragment(pokemonSimpleFragment)
    }

    println(pokemonList.prettyPrint())
}
