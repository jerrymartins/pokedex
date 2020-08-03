package com.pokemon.master.pokedex.controller;

import com.pokemon.master.pokedex.domain.PokemonMasterDomain;
import com.pokemon.master.pokedex.domain.PokemonStatus;
import com.pokemon.master.pokedex.gateway.exception.*;
import com.pokemon.master.pokedex.usecase.*;
import com.pokemon.master.pokedex.usecase.exception.AddMasterPokemonException;
import com.pokemon.master.pokedex.usecase.exception.AddPokemonException;
import com.pokemon.master.pokedex.usecase.exception.CatchPokemonException;
import com.pokemon.master.pokedex.usecase.exception.SendPokemonToCareException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pokemon-master")
public class PokemonMasterController {

    private final AddMasterUseCase addMasterUseCase;
    private final SearchPokemonMasterUseCase searchPokemonMasterUseCase;
    private final UpdatePokemonMasterUseCase updatePokemonMasterUseCase;
    private final DeletePokemonMasterUseCase deletePokemonMasterUseCase;

    private final AddPokemonToMasterUseCase addPokemonToMasterUseCase;
    private final SendPokemonToCareUseCase sendPokemonToCareUseCase;
    private final CatchPokemonUseCase catchPokemonUseCase;

    public PokemonMasterController(AddMasterUseCase addMasterUseCase, SearchPokemonMasterUseCase searchPokemonMasterUseCase, UpdatePokemonMasterUseCase updatePokemonMasterUseCase, DeletePokemonMasterUseCase deletePokemonMasterUseCase, AddPokemonToMasterUseCase addPokemonToMasterUseCase, SendPokemonToCareUseCase sendPokemonToCareUseCase, CatchPokemonUseCase catchPokemonUseCase) {
        this.addMasterUseCase = addMasterUseCase;
        this.searchPokemonMasterUseCase = searchPokemonMasterUseCase;
        this.updatePokemonMasterUseCase = updatePokemonMasterUseCase;
        this.deletePokemonMasterUseCase = deletePokemonMasterUseCase;
        this.addPokemonToMasterUseCase = addPokemonToMasterUseCase;
        this.sendPokemonToCareUseCase = sendPokemonToCareUseCase;
        this.catchPokemonUseCase = catchPokemonUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonMasterDomain create(@RequestBody @Valid PokemonMasterDomain pokemonMasterDomain) throws AddMasterPokemonException, SearchPokemonException {
        return addMasterUseCase.execute(pokemonMasterDomain);
    }

    @GetMapping
    public List<PokemonMasterDomain> findByAll() throws SearchPokemonMasterException {
        return searchPokemonMasterUseCase.all();
    }

    @GetMapping(path = "{id}")
    public PokemonMasterDomain findById(@PathVariable String id) throws PokemonMasterNotFoundException {
        return searchPokemonMasterUseCase.searchById(id);
    }

    @GetMapping(path = "pokemons/{masterId}/{status}")
    public List<PokemonMasterDomain.PokemonDomain> findPokemonsByMasterid(@PathVariable String masterId, @PathVariable(required = false) PokemonStatus status ) throws PokemonMasterNotFoundException {
        return searchPokemonMasterUseCase.searchPokemonsByMasterId(masterId, status);
    }


    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @Valid @RequestBody PokemonMasterDomain pokemonMasterDomain) throws UpdateMasterPokemonException {
        updatePokemonMasterUseCase.execute(id, pokemonMasterDomain);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) throws DeletePokemonMasterException {
        deletePokemonMasterUseCase.execute(id);
    }

    @PatchMapping(path = "add-pokemon/{idMaster}/{pokemon}")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonMasterDomain.PokemonDomain> addPokemon(@PathVariable String idMaster, @PathVariable String pokemon) throws SearchPokemonException, AddPokemonException, PokemonMasterNotFoundException, AddPokemonToMasterGatewayException {
        return addPokemonToMasterUseCase.execute(idMaster, pokemon);
    }

    @PatchMapping(path = "send-pokemon/{idMaster}/{pokemon}")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonMasterDomain.PokemonDomain> sendPokemonToCare(@PathVariable String idMaster, @PathVariable String pokemon) throws PokemonMasterNotFoundException, AddPokemonToMasterGatewayException, SendPokemonToCareException {
        return sendPokemonToCareUseCase.execute(idMaster, pokemon);
    }

    @PatchMapping(path = "catch-pokemon/{idMaster}/{pokemon}")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonMasterDomain.PokemonDomain> catchPokemon(@PathVariable String idMaster, @PathVariable String pokemon) throws PokemonMasterNotFoundException, AddPokemonToMasterGatewayException, CatchPokemonException {
        return catchPokemonUseCase.execute(idMaster, pokemon);
    }

}
