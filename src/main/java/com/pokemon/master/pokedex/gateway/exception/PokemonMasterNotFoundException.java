package com.pokemon.master.pokedex.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PokemonMasterNotFoundException extends Exception {
    public PokemonMasterNotFoundException(String message) {
        super(message);
    }
}
