package com.pokemon.master.pokedex.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AddPokemonException extends Exception {
    public AddPokemonException(String message) {
        super(message);
    }
}
