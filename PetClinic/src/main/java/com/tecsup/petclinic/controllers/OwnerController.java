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
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;

@RestController
public class OwnerController {
	

	@Autowired
	private OwnerService service;
	
	
	
	@PutMapping("/owners/{id}")
	Owner saveOrUpdate(@RequestBody OwnerDTO newOwner, @PathVariable Long id) {
		Owner owner = null;
		try {
			owner = service.findById(id);
			owner.setFirst_name(newOwner.getFirst_name());
			owner.setLast_name(newOwner.getLast_name());
			owner.setCity(newOwner.getCity());
			service.update(owner);
		}catch (OwnerNotFoundException e) {
			owner = service.create(owner);
		}
		return owner;
	}
	
	

	
	@PostMapping("/owners")
	@ResponseStatus(HttpStatus.CREATED)
	Owner create(@RequestBody OwnerDTO newOwner) {
		
		Owner owner = new Owner();
		owner.setFirst_name(newOwner.getFirst_name());
		owner.setLast_name(newOwner.getLast_name());
		owner.setCity(newOwner.getCity());
		return service.create(owner);
	}
	
	@GetMapping("/owners/{id}")
	ResponseEntity<Owner> findOne(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
			
		}catch (OwnerNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
	}
	
	
	@DeleteMapping("/owners/{id}")
	ResponseEntity<String> delete(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity<>(""+id,HttpStatus.OK);
			
		}catch (OwnerNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			// TODO: handle exception
		}
	}
	
}

