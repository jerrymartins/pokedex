package com.pokemon.master.pokedex.gateway.database;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.*;
import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import com.pokemon.master.pokedex.gateway.database.repository.PokemonMasterRepository;
import com.pokemon.master.pokedex.gateway.database.translator.PokemonMasterEntityToPokemonMasterDomainTranslator;
import com.pokemon.master.pokedex.gateway.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchMasterGatewayImpl implements SearchMasterGateway {

    private final PokemonMasterRepository pokemonMasterRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchMasterGatewayImpl.class);

    @Autowired
    public SearchMasterGatewayImpl(PokemonMasterRepository pokemonMasterRepository) {
        this.pokemonMasterRepository = pokemonMasterRepository;
    }

    @Override
    public PokemonMasterDomain searchById(String id) throws PokemonMasterNotFoundException {
        try {
            PokemonMasterEntity master = pokemonMasterRepository.findById(id).orElseThrow();
            return PokemonMasterEntityToPokemonMasterDomainTranslator.translate(master);
        } catch (Exception exception) {
            LOGGER.error("Problemas ao buscar mestre por id {}", id);
            throw new PokemonMasterNotFoundException("Mestre não encontrado");
        }
    }

    @Override
    public List<PokemonMasterDomain> all() throws SearchPokemonMasterException {
        try {
            return pokemonMasterRepository.findAll()
                    .stream()
                    .map(PokemonMasterEntityToPokemonMasterDomainTranslator::translate)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new SearchPokemonMasterException("Erro ao buscar mestres");
        }
    }

}
