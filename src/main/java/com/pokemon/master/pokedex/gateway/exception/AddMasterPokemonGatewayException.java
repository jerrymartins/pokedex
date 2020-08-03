package com.pokemon.master.pokedex.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AddMasterPokemonGatewayException extends Exception {
    public AddMasterPokemonGatewayException(String message) {
        super(message);
    }
}
