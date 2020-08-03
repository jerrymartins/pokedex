package com.pokemon.master.pokedex.domain;

import lombok.Getter;

import java.io.Serializable;

public enum StarterPokemonEnum implements Serializable {
    PIKACHU("pikachu"),
    BULBASAUR("bulbasaur"),
    CHARMANDER("charmander"),
    SQUIRTLE("squirtle");

    @Getter
    private final String name;

    StarterPokemonEnum(String name) {
        this.name = name;
    }

}
