import com.pokemon.master.pokedex.domain.PokemonMasterDomain
import com.pokemon.master.pokedex.gateway.AddPokemonToMasterGateway
import com.pokemon.master.pokedex.gateway.SearchMasterGateway
import com.pokemon.master.pokedex.gateway.SearchPokemonGateway
import com.pokemon.master.pokedex.usecase.AddLinkToPokemonUseCase
import com.pokemon.master.pokedex.usecase.AddPokemonToMasterUseCase
import com.pokemon.master.pokedex.usecase.exception.AddPokemonException
import org.springframework.hateoas.Link
import spock.lang.Ignore
import spock.lang.Specification

class AddPokemonToMasterSpec extends Specification {

    SearchMasterGateway searchMasterGateway = Mock(SearchMasterGateway)
    SearchPokemonGateway searchPokemonGateway = Mock(SearchPokemonGateway)
    AddPokemonToMasterGateway addPokemonToMasterGateway = Mock(AddPokemonToMasterGateway)
    AddLinkToPokemonUseCase addLinkToPokemonUseCase = Mock(AddLinkToPokemonUseCase)

    AddPokemonToMasterUseCase addPokemonToMasterUseCase = new AddPokemonToMasterUseCase(searchMasterGateway, searchPokemonGateway, addPokemonToMasterGateway, addLinkToPokemonUseCase)

    def "should not add if there are six active pokemons"() {
        given: "a master with six pokemons"
        PokemonMasterDomain masterWithSixActivePokemons = oneMaster(sixActivePokemons());

        when: "usecase is called"
        addPokemonToMasterUseCase.execute(_ as String, "rattata")

        then: "a pokemon is found"
        searchPokemonGateway.searchByName(_ as String) >> onePokemon("rattata")

        and: "a master with six pokemons is found"
        searchMasterGateway.searchById(_ as String) >> masterWithSixActivePokemons

        and:
        thrown(AddPokemonException)
    }

    @Ignore
    def "should be able to add pokemon when master has less than 6 active pokemons"() {
        given: "a master with one pokemon"
        List<PokemonMasterDomain.PokemonDomain> pokemonsList = new ArrayList<>()
        pokemonsList.add(onePokemon())
        PokemonMasterDomain masterWithOneActivePokemons = oneMaster(pokemonsList);

        when: "usecase is called"
        addPokemonToMasterUseCase.execute(_ as String, "rattata")

        then: "a pokemon is found"
        searchPokemonGateway.searchByName(_ as String) >> onePokemon("rattata")

        and: "a master with one pokemons is found"
        searchMasterGateway.searchById(_ as String) >> masterWithOneActivePokemons

        and: "a pokemon is added"
        addPokemonToMasterGateway.addPokemon(_ as PokemonMasterDomain) >> masterWithOneActivePokemons

        and:
        notThrown(AddPokemonException)
        pokemonsList.size() == 2
    }

    PokemonMasterDomain oneMaster(List<PokemonMasterDomain.PokemonDomain> pokemonList) {
        return PokemonMasterDomain.builder()
                .pokemons(pokemonList)
                .build();
    }

    List<PokemonMasterDomain.PokemonDomain> sixActivePokemons() {
        List<PokemonMasterDomain.PokemonDomain> pokemonList = new ArrayList<>()

        ["pikachu", "charmander", "venusaur", "charmeleon", "squirtle", "blastoise"].forEach({ pokemon ->
            PokemonMasterDomain.PokemonDomain pokemonDomain = PokemonMasterDomain.PokemonDomain.builder()
                    .name(pokemon)
                    .active(true)
                    .build()
            pokemonList.add(pokemonDomain)
        })

        return pokemonList
    }

    PokemonMasterDomain.PokemonDomain onePokemon(String pokemonName) {
        return PokemonMasterDomain.PokemonDomain.builder()
                .name(pokemonName)
                .active(true)
                .build()
    }
}