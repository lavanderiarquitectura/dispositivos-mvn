package co.edu.unal.controller;

import co.edu.unal.exception.ResourceNotFoundException;
import co.edu.unal.model.Device;
import co.edu.unal.model.Lot;
import co.edu.unal.repository.LotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
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

	// Get All Lots for 
	@GetMapping("/lots/byTypeOperation/{type_operation}")
	public List<Lot> getAllLotsByTypeOperation(@PathVariable(value = "type_operation") Integer type) {
		List<Lot> allLots = lotRepository.findAll();
		List<Lot> lotsByType = new ArrayList<>();
		for(Lot device: allLots) {
			if(device.getTypeOperation().equals(type))
				lotsByType.add(device);
		}
	    return lotsByType;
	}
	
	// Get a list of  Lot by operationID, fabriqueID and if lot has finished
	@GetMapping("/lots/{id_op}/{id_fab}/{bool_fin}")
	public List<Lot> getLotByIdOperAndFab(@PathVariable(value = "id_op") Integer opId, @PathVariable(value = "id_fab") Integer fabId,
										  @PathVariable(value = "bool_fin") Boolean boolFin) {
		return lotRepository.findByTypeOperationAndTypeFabricAndIsFinished(opId, fabId, boolFin);
	}

	@GetMapping("/lots/max")
	public Lot getMaxId() {
		return lotRepository.findTopByOrderByIdDesc();
	}

	// Get a list of  Lot by operationID and if lot has finished
	@GetMapping("/lots/{id_op}/{bool_fin}")
	public List<Lot> getLotByIdOper(@PathVariable(value = "id_op") Integer opId, @PathVariable(value = "bool_fin") Boolean boolFin) {
		return lotRepository.findByTypeOperationAndIsFinished(opId, boolFin);
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
	   
	    lot.setTypeOperation(lotDetails.getTypeOperation());
	    lot.setState(lotDetails.getState());
	    
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
