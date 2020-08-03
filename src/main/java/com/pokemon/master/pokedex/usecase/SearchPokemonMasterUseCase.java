package com.pokemon.master.pokedex.usecase;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.domain.PokemonStatus;
import com.pokemon.master.pokedex.gateway.SearchMasterGateway;
import com.pokemon.master.pokedex.gateway.exception.PokemonMasterNotFoundException;
import com.pokemon.master.pokedex.gateway.exception.SearchPokemonMasterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPokemonMasterUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchPokemonMasterUseCase.class);
    private final SearchMasterGateway searchMasterGateway;
    private final AddLinkToPokemonUseCase addLinkToPokemonUseCase;
    private final AddLinkToMasterUseCase addLinkToMasterUseCase;

    public SearchPokemonMasterUseCase(SearchMasterGateway searchMasterGateway, AddLinkToPokemonUseCase addLinkToPokemonUseCase, AddLinkToMasterUseCase addLinkToMasterUseCase) {
        this.searchMasterGateway = searchMasterGateway;
        this.addLinkToPokemonUseCase = addLinkToPokemonUseCase;
        this.addLinkToMasterUseCase = addLinkToMasterUseCase;
    }

    public List<PokemonMasterDomain> all() throws SearchPokemonMasterException {
        LOGGER.info("buscando todos os mestres");
        List<PokemonMasterDomain> masters = searchMasterGateway.all();
        return masters.stream().peek(master -> {
            Link link = new Link("http://localhost:8080/pokemon-master/" + master.getIdMaster());
            master.add(link);
        }).collect(Collectors.toList());
    }

    public PokemonMasterDomain searchById(String id) throws PokemonMasterNotFoundException {
        LOGGER.info("buscando mestre por id {}", id);
        PokemonMasterDomain master = searchMasterGateway.searchById(id);
        master.setPokemons(addLinkToPokemonUseCase.execute(master.getPokemons(), id));
        return addLinkToMasterUseCase.addLink(master);
    }

    public List<PokemonMasterDomain.PokemonDomain> searchPokemonsByMasterId(String masterId, PokemonStatus status) throws PokemonMasterNotFoundException {
        LOGGER.info("buscando pokemons do mestre id {}", masterId);
        List<PokemonMasterDomain.PokemonDomain> pokemons = searchById(masterId).getPokemons();

        switch (status) {
            case active:
                pokemons = pokemons.stream().filter(PokemonMasterDomain.PokemonDomain::getActive).collect(Collectors.toList());
                break;
            case inactive:
                pokemons = pokemons.stream().filter(pokemon -> !pokemon.getActive()).collect(Collectors.toList());
                break;
        }

        return addLinkToPokemonUseCase.execute(pokemons, masterId);

    }

}
