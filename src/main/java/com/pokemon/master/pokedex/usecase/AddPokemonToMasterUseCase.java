package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.AddPokemonToMasterGateway;
import com.pokemon.master.pokedex.gateway.SearchMasterGateway;
import com.pokemon.master.pokedex.gateway.SearchPokemonGateway;
import com.pokemon.master.pokedex.gateway.exception.AddPokemonToMasterGatewayException;
import com.pokemon.master.pokedex.gateway.exception.PokemonMasterNotFoundException;
import com.pokemon.master.pokedex.gateway.exception.SearchPokemonException;
import com.pokemon.master.pokedex.usecase.exception.AddPokemonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddPokemonToMasterUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddPokemonToMasterUseCase.class);
    private final SearchMasterGateway searchMasterGateway;
    private final SearchPokemonGateway searchPokemonGateway;
    private final AddPokemonToMasterGateway addPokemonToMasterGateway;
    private final AddLinkToPokemonUseCase addLinkToPokemonUseCase;

    public AddPokemonToMasterUseCase(SearchMasterGateway searchMasterGateway, SearchPokemonGateway searchPokemonGateway, AddPokemonToMasterGateway addPokemonToMasterGateway, AddLinkToPokemonUseCase addLinkToPokemonUseCase) {
        this.searchMasterGateway = searchMasterGateway;
        this.searchPokemonGateway = searchPokemonGateway;
        this.addPokemonToMasterGateway = addPokemonToMasterGateway;
        this.addLinkToPokemonUseCase = addLinkToPokemonUseCase;
    }

    public List<PokemonMasterDomain.PokemonDomain> execute(String idMaster, String pokemon) throws SearchPokemonException, PokemonMasterNotFoundException, AddPokemonException, AddPokemonToMasterGatewayException {
        LOGGER.info("procurando pokemon");
        PokemonMasterDomain.PokemonDomain pokemonDomain = searchPokemonGateway.searchByName(pokemon);
        PokemonMasterDomain pokemonMasterDomain = searchMasterGateway.searchById(idMaster);

        pokemonMasterDomain.setPokemons(addPokemon(pokemonDomain, pokemonMasterDomain.getPokemons()));

        List<PokemonMasterDomain.PokemonDomain> activePokemons = activePokemons(addPokemonToMasterGateway.addPokemon(pokemonMasterDomain));

        return addLinkToPokemonUseCase.execute(activePokemons, idMaster);

    }

    private List<PokemonMasterDomain.PokemonDomain> addPokemon(PokemonMasterDomain.PokemonDomain pokemonDomain, List<PokemonMasterDomain.PokemonDomain> pokemonsDomain) throws AddPokemonException {
        Assert.notNull(pokemonDomain, "pokemon não pode ser nulo");

        long activePokemons = pokemonsDomain.stream().filter(PokemonMasterDomain.PokemonDomain::getActive).count();

        if (activePokemons == 6) {
            throw new AddPokemonException("Sem espaço para mais pokemons");
        }

        pokemonDomain.setActive(true);
        pokemonsDomain.add(pokemonDomain);

        return pokemonsDomain;
    }

    private List<PokemonMasterDomain.PokemonDomain> activePokemons(List<PokemonMasterDomain.PokemonDomain> pokemonsDomain) {
        return pokemonsDomain.stream().filter(PokemonMasterDomain.PokemonDomain::getActive).collect(Collectors.toList());
    }
}
