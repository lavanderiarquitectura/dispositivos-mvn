package co.edu.unal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unal.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

}
