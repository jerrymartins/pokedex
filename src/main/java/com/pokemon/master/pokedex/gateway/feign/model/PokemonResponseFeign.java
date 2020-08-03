package com.pokemon.master.pokedex.gateway.feign.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PokemonResponseFeign {
    public Integer id;
    public String name;
    public Integer height;
    public Integer weight;
    public Boolean active;
}
