package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.AddMasterGateway;
import com.pokemon.master.pokedex.gateway.exception.AddMasterPokemonGatewayException;
import com.pokemon.master.pokedex.gateway.exception.SearchPokemonException;
import com.pokemon.master.pokedex.gateway.feign.PokemonFeignGateway;
import com.pokemon.master.pokedex.usecase.exception.AddMasterPokemonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AddMasterUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddMasterUseCase.class);
    private final AddMasterGateway addMasterGateway;
    private final PokemonFeignGateway pokemonFeignGateway;

    public AddMasterUseCase(AddMasterGateway addMasterGateway, PokemonFeignGateway pokemonFeignGateway) {
        this.addMasterGateway = addMasterGateway;
        this.pokemonFeignGateway = pokemonFeignGateway;
    }

    public PokemonMasterDomain execute(PokemonMasterDomain pokemonMasterDomain) throws AddMasterPokemonException, SearchPokemonException {
        pokemonMasterDomain.setPokemons(new ArrayList<>());
        PokemonMasterDomain.PokemonDomain pokemonSearched = pokemonFeignGateway.searchByName(pokemonMasterDomain.getStarterPokemon().getName());
        pokemonSearched.setActive(true);
        pokemonMasterDomain.getPokemons().add(pokemonSearched);

        try {
            LOGGER.info("adicionando pokemon mestre {}", pokemonMasterDomain);
            return addMasterGateway.add(pokemonMasterDomain);
        } catch (AddMasterPokemonGatewayException e) {
            throw new AddMasterPokemonException("Problemas ao adicionar mestre pokemon");
        }

    }
}
