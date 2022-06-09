package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

public interface OwnerService {
	
	Owner create(Owner owner);
	
	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	
	void delete(Long id) throws OwnerNotFoundException;
	/**
	 * 
	 * @param Owner
	 * @return
	 */
	
	Owner findById(long id) throws OwnerNotFoundException;

	Owner update(Owner owner);
	
	Iterable<Owner> findAll();
}
