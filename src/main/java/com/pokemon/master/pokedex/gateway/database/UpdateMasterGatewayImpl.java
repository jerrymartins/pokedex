package com.pokemon.master.pokedex.gateway.database;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.*;
import com.pokemon.master.pokedex.gateway.database.repository.PokemonMasterRepository;
import com.pokemon.master.pokedex.gateway.database.translator.PokemonMasterDomainToPokemonMasterEntityTranslator;
import com.pokemon.master.pokedex.gateway.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateMasterGatewayImpl implements UpdateMasterGateway {

    private final PokemonMasterRepository pokemonMasterRepository;
    private final SearchMasterGateway searchMasterGateway;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateMasterGatewayImpl.class);

    @Autowired
    public UpdateMasterGatewayImpl(PokemonMasterRepository pokemonMasterRepository, SearchMasterGateway searchMasterGateway) {
        this.pokemonMasterRepository = pokemonMasterRepository;
        this.searchMasterGateway = searchMasterGateway;
    }
    @Override
    public void update(String id, PokemonMasterDomain pokemonMasterDomain) throws UpdateMasterPokemonException {
        try {
            PokemonMasterDomain masterFound = searchMasterGateway.searchById(id);
            pokemonMasterDomain.setPokemons(masterFound.getPokemons());
            pokemonMasterRepository.save(PokemonMasterDomainToPokemonMasterEntityTranslator.translate(pokemonMasterDomain.withIdMaster(id)));
        } catch (Exception exception) {
            LOGGER.error("Problemas ao atualizar mestre pokemon {}", exception);
            throw new UpdateMasterPokemonException("Problemas ao atualizar mestre pokemon");
        }
    }
}
