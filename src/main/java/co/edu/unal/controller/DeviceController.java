package co.edu.unal.controller;

import co.edu.unal.exception.ResourceNotFoundException;
import co.edu.unal.model.Device;
import co.edu.unal.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class DeviceController {

	@Autowired
	DeviceRepository deviceRepository;

	// Get All Devices
	@GetMapping("/devices")
	public List<Device> getAllDevices() {
	    return deviceRepository.findAll();
	}
	
	// Get All Devices
	@GetMapping("/devices/byType/{type}")
	public List<Device> getAllDevicesByType(@PathVariable(value = "type") String type) {
		List<Device> allDevices = deviceRepository.findAll();
		List<Device> devicesByType = new ArrayList<Device>();
		for(Device device: allDevices) {
			if(device.getType().equals(type))
				devicesByType.add(device);
		}
	    return devicesByType;
	}
		
	
	// Get a Single Device
	@GetMapping("/devices/{id}")
	public Device getDeviceById(@PathVariable(value = "id") Long deviceId) {
	    return deviceRepository.findById(deviceId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", deviceId));
	}
	
	// Create a new Device
	@PostMapping("/devices")
	public Device createDevice(@Valid @RequestBody Device device) {
	    return deviceRepository.save(device);
	}

	// Update a Device
	@PutMapping("/devices/{id}")
	public Device updateDevice(@PathVariable(value = "id") Long deviceId,
	                                        @Valid @RequestBody Device deviceDetails) {

	    Device device = deviceRepository.findById(deviceId)
	            .orElseThrow(() -> new ResourceNotFoundException("Device", "id", deviceId));

	    device.setLotId( deviceDetails.getLotId());
	    device.setState(deviceDetails.getState());
	    device.setType( deviceDetails.getType());
	    
	    Device updatedDevice = deviceRepository.save(device);
	    return updatedDevice;
	}

	// Delete a Device
	@DeleteMapping("/devices/{id}")
	public ResponseEntity<?> deleteDevice(@PathVariable(value = "id") Long deviceId) {
	    Device device = deviceRepository.findById(deviceId)
	            .orElseThrow(() -> new ResourceNotFoundException("Device", "id", deviceId));

	    deviceRepository.delete(device);

	    return ResponseEntity.ok().build();
	}


	
}
