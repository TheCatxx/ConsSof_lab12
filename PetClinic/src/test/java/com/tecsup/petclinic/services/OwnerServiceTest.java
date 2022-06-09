package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;


@SpringBootTest
public class OwnerServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;
	
	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "Carlos";
		String LAST_NAME = "Mendoza";
		String CITY  = "Trujillo";
		
		Owner owner =new Owner("Carlos","Mendoza","Trujillo");
		Owner ownerCreated = ownerService.create(owner);
		logger.info("Owner creado : "+ownerCreated);
		assertThat(ownerCreated.getId(), notNullValue());
		assertThat(ownerCreated.getFirst_name(), is(FIRST_NAME));
		assertThat(ownerCreated.getLast_name(), is(LAST_NAME));
		assertThat(ownerCreated.getCity(), is(CITY));
	}
	
	@Test 
	public void testFindOwnerById() {
		long ID = 1;
		String FIRST_NAME = "George";
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
			
		}catch(OwnerNotFoundException e ){
			assertThat(e.getMessage(), false);
		}
		logger.info(""+owner);
		assertThat(owner.getFirst_name(), is(FIRST_NAME));
	}
	
	
	@Test
	public void testUpdateOwner() {
		String FIRST_NAME = "George";
		String LAST_NAME = "Franklin";
		String CITY  = "Madison";
		long create_id = -1;
		
		
		String UP_FIRST_NAME = "George2";
		String UP_LAST_NAME = "Franklin2";
		String UP_CITY  = "Madison2";
		
		Owner owner = new Owner(FIRST_NAME,LAST_NAME,CITY);
		
		
		logger.info(">" + owner);
		Owner ownerCreated = ownerService.create(owner);
		logger.info(">>"+ownerCreated);
		
		create_id = ownerCreated.getId();
		
		
		ownerCreated.setFirst_name(UP_FIRST_NAME);
		ownerCreated.setLast_name(UP_LAST_NAME);
		ownerCreated.setCity(UP_CITY);
		
		
		Owner upgradeOwner = ownerService.update(ownerCreated);
		logger.info(">>>>"+ upgradeOwner);
		
		//      ACTUAL         EXPECTED
		assertThat(create_id ,notNullValue());
		assertThat(upgradeOwner.getId(), is(create_id));
		assertThat(upgradeOwner.getFirst_name(), is(UP_FIRST_NAME));
		assertThat(upgradeOwner.getLast_name(), is(UP_LAST_NAME));
		assertThat(upgradeOwner.getCity(), is(UP_CITY));
	
	}
	
	@Test
	public void testDeleteOwner() {
		String FIRST_NAME = "George";
		String LAST_NAME = "Franklin";
		String CITY  = "Madison";
		
		Owner owner = new Owner(FIRST_NAME,LAST_NAME,CITY);
		owner = ownerService.create(owner);
		logger.info(""+owner);
		
		try {
			ownerService.delete(owner.getId());
		}catch(OwnerNotFoundException e) {
			assertThat(e.getMessage(), false);
		}
		
		try {
			ownerService.findById(owner.getId());
			assertThat(true, is(false));
		}catch(OwnerNotFoundException e) {
			assertThat(true, is(true));
		}
	}
	
}
