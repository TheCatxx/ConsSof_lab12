package com.tecsup.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.dto.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.services.OwnerService;

@RestController
public class OwnerController {
	
	@Autowired
	private OwnerService service;
	
	@PostMapping("/owners")
	@ResponseStatus(HttpStatus.CREATED)
	Owner create(@RequestBody OwnerDTO newOwner) {
		
		Owner owner = new Owner();
		owner.setFirst_name(newOwner.getFirst_name());
		owner.setLast_name(newOwner.getLast_name());
		owner.setCity(newOwner.getCity());
		return service.create(owner);
	}
	
}