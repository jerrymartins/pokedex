package com.pokemon.master.pokedex.gateway.database.repository;

import com.pokemon.master.pokedex.gateway.database.entity.PokemonMasterEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PokemonMasterRepository extends CrudRepository<PokemonMasterEntity, String> {
    List<PokemonMasterEntity> findByName(String name);

    List<PokemonMasterEntity> findAll();
}
