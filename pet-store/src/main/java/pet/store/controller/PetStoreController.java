package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

//this tells Spring that this class is a REST controller and to map HTTP requests to class methods
@RestController
//tells Spring that the URI for every HTTP request that is mapped to am method must start with /pet_store
@RequestMapping("/pet_store")
// Lombok annotation that creates an SLF4J logger
@Slf4j
public class PetStoreController {
	@Autowired 
	private PetStoreService petStoreService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store");
		return petStoreService.savePetStore(petStoreData);	
	}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		log.info("Updating store with ID={}", petStoreId);
		
		petStoreData.setPetStoreId(petStoreId);
		
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee insertEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Adding employee to store with ID={}", petStoreId);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);	
	}
	
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer insertCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Adding customer to store with ID={}", petStoreId);
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);	
	}
	
	@PutMapping("/{petStoreId}/customer/{customerId}")
	public PetStoreCustomer updateCustomer(@PathVariable Long petStoreId,@PathVariable Long customerId, @RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Updating customer to store with ID={}", petStoreId);
		petStoreCustomer.setCustomerId(customerId);
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);	
	}
	
	@GetMapping
	private List<PetStoreData> listAllPetStores() {
		log.info("Getting all pet stores.");
		return petStoreService.retrieveAllPetStores();
	}
	
	@GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreDataById(@PathVariable Long petStoreId) {
		log.info("Getting data of pet store with ID={}", petStoreId);
		
		return petStoreService.retrievePetStoreById(petStoreId);
	}

	@DeleteMapping("/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID={}", petStoreId);
		
		petStoreService.deletePetStoreById(petStoreId);
		
		return Map.of("message:", "Deletion of pet store with ID=" + petStoreId + " was successful.");
	}
	
	
	
}
