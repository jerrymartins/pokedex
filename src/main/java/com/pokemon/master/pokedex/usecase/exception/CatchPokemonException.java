package com.pokemon.master.pokedex.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CatchPokemonException extends Exception {
    public CatchPokemonException(String message) {
        super(message);
    }
}
