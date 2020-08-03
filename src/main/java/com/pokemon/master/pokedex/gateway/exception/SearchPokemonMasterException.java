package com.pokemon.master.pokedex.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SearchPokemonMasterException extends Exception {
    public SearchPokemonMasterException(String message) {
        super(message);
    }
}
