package com.tecsup.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.dto.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;

@RestController
public class OwnerController {
	
	@Autowired
	private OwnerService service;
	
	@GetMapping("/owner")
	public Iterable<Owner> getOwners(){
		return service.findAll();		
	}
	
	@PostMapping("/owners/{id}")
	ResponseEntity<String> delete(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity<>("" + id, HttpStatus.OK);
		}catch (OwnerNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}
