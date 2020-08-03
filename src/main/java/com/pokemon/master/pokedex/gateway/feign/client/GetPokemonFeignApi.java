package com.pokemon.master.pokedex.gateway.feign.client;

import com.pokemon.master.pokedex.gateway.feign.model.PokemonResponseFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "getPokemonFeignApi", url = "${feign.pokemon.api}")
public interface GetPokemonFeignApi {
    @GetMapping(value = "/pokemon/{pokemon}", produces = APPLICATION_JSON_VALUE)
    PokemonResponseFeign get(@RequestParam("pokemon") String pokemon);
}
