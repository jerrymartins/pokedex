package com.pokemon.master.pokedex.gateway.feign;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.gateway.SearchPokemonGateway;
import com.pokemon.master.pokedex.gateway.exception.SearchPokemonException;
import com.pokemon.master.pokedex.gateway.feign.client.GetPokemonFeignApi;
import com.pokemon.master.pokedex.gateway.feign.model.PokemonResponseFeign;
import com.pokemon.master.pokedex.gateway.feign.translator.PokemonResponseFeignToPokemonDomainTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PokemonFeignGateway implements SearchPokemonGateway {

    private final GetPokemonFeignApi getPokemonFeignApi;

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonFeignGateway.class);

    public PokemonFeignGateway(GetPokemonFeignApi getPokemonFeignApi) {
        this.getPokemonFeignApi = getPokemonFeignApi;
    }

    @Override
    public PokemonMasterDomain.PokemonDomain searchByName(String name) throws SearchPokemonException {
        try {
            PokemonResponseFeign pokemon  = getPokemonFeignApi.get(name);
            return PokemonResponseFeignToPokemonDomainTranslator.translate(pokemon);
        } catch (Exception exception) {
            throw new SearchPokemonException("Pokemon não encontrado");
        }
    }
}
