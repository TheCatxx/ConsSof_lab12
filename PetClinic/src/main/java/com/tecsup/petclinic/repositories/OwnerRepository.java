package com.tecsup.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tecsup.petclinic.entities.Owner;

public interface OwnerRepository 
			extends CrudRepository<Owner,Long>{

}
