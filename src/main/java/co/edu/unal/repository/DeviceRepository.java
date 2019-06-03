package co.edu.unal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unal.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
