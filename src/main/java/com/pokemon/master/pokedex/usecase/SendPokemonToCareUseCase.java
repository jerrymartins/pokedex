package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.AddPokemonToMasterGateway;
import com.pokemon.master.pokedex.gateway.SearchMasterGateway;
import com.pokemon.master.pokedex.gateway.SearchPokemonGateway;
import com.pokemon.master.pokedex.gateway.exception.AddPokemonToMasterGatewayException;
import com.pokemon.master.pokedex.gateway.exception.PokemonMasterNotFoundException;
import com.pokemon.master.pokedex.usecase.exception.SendPokemonToCareException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class SendPokemonToCareUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendPokemonToCareUseCase.class);
    private final SearchMasterGateway searchMasterGateway;
    private final AddPokemonToMasterGateway addPokemonToMasterGateway;

    public SendPokemonToCareUseCase(SearchMasterGateway searchMasterGateway, AddPokemonToMasterGateway addPokemonToMasterGateway, SearchPokemonGateway searchPokemonGateway) {
        this.searchMasterGateway = searchMasterGateway;
        this.addPokemonToMasterGateway = addPokemonToMasterGateway;
    }

    public List<PokemonMasterDomain.PokemonDomain> execute(String idMaster, String pokemon) throws PokemonMasterNotFoundException, AddPokemonToMasterGatewayException, SendPokemonToCareException {
        PokemonMasterDomain pokemonMasterDomain = searchMasterGateway.searchById(idMaster);

        LOGGER.info("desabilitando pokemon");
        disablePokemon(pokemon, pokemonMasterDomain.getPokemons());

        return activePokemons(addPokemonToMasterGateway.addPokemon(pokemonMasterDomain));

    }

    private List<PokemonMasterDomain.PokemonDomain> disablePokemon(String pokemonName, List<PokemonMasterDomain.PokemonDomain> pokemonsDomain) throws SendPokemonToCareException {
        if (pokemonName.isBlank() || pokemonName == null) {
            throw new SendPokemonToCareException("informe o pokemon");
        }

        Predicate<PokemonMasterDomain.PokemonDomain> pokemonToDisable = pokemonDomain -> pokemonDomain.getName().equalsIgnoreCase(pokemonName) && pokemonDomain.getActive();
        return pokemonsDomain.stream().filter(pokemonToDisable).map(pokemonDomain -> {
            pokemonDomain.setActive(false);
            return pokemonDomain;
        }).collect(Collectors.toList());
    }

    private List<PokemonMasterDomain.PokemonDomain> activePokemons(List<PokemonMasterDomain.PokemonDomain> pokemonsDomain) {
        return pokemonsDomain.stream().filter(pokemonDomain -> pokemonDomain.getActive()).collect(Collectors.toList());
    }
}
