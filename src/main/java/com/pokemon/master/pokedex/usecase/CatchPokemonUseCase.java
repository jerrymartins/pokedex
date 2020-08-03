package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.AddPokemonToMasterGateway;
import com.pokemon.master.pokedex.gateway.SearchMasterGateway;
import com.pokemon.master.pokedex.gateway.exception.AddPokemonToMasterGatewayException;
import com.pokemon.master.pokedex.gateway.exception.PokemonMasterNotFoundException;
import com.pokemon.master.pokedex.usecase.exception.CatchPokemonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class CatchPokemonUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatchPokemonUseCase.class);
    private final SearchMasterGateway searchMasterGateway;
    private final AddPokemonToMasterGateway addPokemonToMasterGateway;

    public CatchPokemonUseCase(SearchMasterGateway searchMasterGateway, AddPokemonToMasterGateway addPokemonToMasterGateway) {
        this.searchMasterGateway = searchMasterGateway;
        this.addPokemonToMasterGateway = addPokemonToMasterGateway;
    }

    public List<PokemonMasterDomain.PokemonDomain> execute(String idMaster, String pokemon) throws PokemonMasterNotFoundException, AddPokemonToMasterGatewayException, CatchPokemonException {
        PokemonMasterDomain pokemonMasterDomain = searchMasterGateway.searchById(idMaster);

        enablePokemon(pokemon, pokemonMasterDomain.getPokemons());

        return activePokemons(addPokemonToMasterGateway.addPokemon(pokemonMasterDomain));

    }

    private List<PokemonMasterDomain.PokemonDomain> enablePokemon(String pokemonName, List<PokemonMasterDomain.PokemonDomain> pokemonsDomain) throws CatchPokemonException {
        if (pokemonName.isBlank()) {
            throw new CatchPokemonException("informe o pokemon");
        }
        LOGGER.info("recuperando pokemon");
        Predicate<PokemonMasterDomain.PokemonDomain> pokemonToDisable = pokemonDomain -> pokemonDomain.getName().equalsIgnoreCase(pokemonName) && !pokemonDomain.getActive();
        return pokemonsDomain.stream().filter(pokemonToDisable).map(pokemonDomain -> {
            pokemonDomain.setActive(true);
            return pokemonDomain;
        }).collect(Collectors.toList());
    }

    private List<PokemonMasterDomain.PokemonDomain> activePokemons(List<PokemonMasterDomain.PokemonDomain> pokemonsDomain) {
        return pokemonsDomain.stream().filter(PokemonMasterDomain.PokemonDomain::getActive).collect(Collectors.toList());
    }
}
