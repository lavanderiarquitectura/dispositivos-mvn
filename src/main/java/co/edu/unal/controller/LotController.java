package co.edu.unal.controller;

import co.edu.unal.exception.ResourceNotFoundException;
import co.edu.unal.model.Lot;
import co.edu.unal.repository.LotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class LotController {

	@Autowired
	LotRepository lotRepository;

	// Get All Lots
	@GetMapping("/lots")
	public List<Lot> getAllLots() {
	    return lotRepository.findAll();
	}
	
	// Get a Single Lot
	@GetMapping("/lots/{id}")
	public Lot getLotById(@PathVariable(value = "id") Long lotId) {
	    return lotRepository.findById(lotId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", lotId));
	}
	
	// Create a new Lot
	@PostMapping("/lots")
	public Lot createLot(@Valid @RequestBody Lot lot) {
	    return lotRepository.save(lot);
	}

	// Update a Lot
	@PutMapping("/lots/{id}")
	public Lot updateLot(@PathVariable(value = "id") Long lotId,
	                                        @Valid @RequestBody Lot lotDetails) {

	    Lot lot = lotRepository.findById(lotId)
	            .orElseThrow(() -> new ResourceNotFoundException("Lot", "id", lotId));

	   
	    lot.setTypeService(lotDetails.getTypeService());
	    
	    Lot updatedLot = lotRepository.save(lot);
	    return updatedLot;
	}

	// Delete a Lot
	@DeleteMapping("/lots/{id}")
	public ResponseEntity<?> deleteLot(@PathVariable(value = "id") Long lotId) {
	    Lot lot = lotRepository.findById(lotId)
	            .orElseThrow(() -> new ResourceNotFoundException("Lot", "id", lotId));

	    lotRepository.delete(lot);

	    return ResponseEntity.ok().build();
	}


	
}
