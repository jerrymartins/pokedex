package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class AddLinkToMasterUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddLinkToMasterUseCase.class);

    public AddLinkToMasterUseCase() {}

    public PokemonMasterDomain addLink(PokemonMasterDomain master) {
        LOGGER.info("criando link para mestre");
        long totalActive = master.getPokemons().stream().filter(PokemonMasterDomain.PokemonDomain::getActive).count();

        if (totalActive <= 6) {
            Link linkAddPokemon = new Link(String.format("http://localhost:8080/pokemon-master/add-pokemon/%s", master.getIdMaster()))
                    .withRel("AddPokemonToMaster");
            master.add(linkAddPokemon);
        }

        return master;
    }

}
