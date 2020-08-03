package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AddLinkToPokemonUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddLinkToPokemonUseCase.class);

    public AddLinkToPokemonUseCase() {}

    public List<PokemonMasterDomain.PokemonDomain> execute(List<PokemonMasterDomain.PokemonDomain> pokemons, String masterId) {
        LOGGER.info("criando links para pokemons");
        long totalActive = countActives(pokemons);
        return pokemons.stream().peek(pokemon -> {
            Link link = handleLink(pokemon, masterId, totalActive);
            if (link != null) pokemon.add(link);
        }).collect(Collectors.toList());
    }

    public Link handleLink(PokemonMasterDomain.PokemonDomain pokemon, String idMaster, long totalActive) {
        if (pokemon.getActive()) {
            return new Link(String.format("http://localhost:8080/pokemon-master/send-pokemon/%s/%s", idMaster, pokemon.getName()))
                    .withRel("SendPokemon");

        } else {
            if (totalActive < 6) {
                return new Link(String.format("http://localhost:8080/pokemon-master/retrieve-pokemon/%s/%s", idMaster, pokemon.getName()))
                        .withRel("CatchPokemon");
            }
            return null;
        }
    }

    private long countActives(List<PokemonMasterDomain.PokemonDomain> pokemons) {
        return pokemons.stream().filter(PokemonMasterDomain.PokemonDomain::getActive).count();
    }

}
