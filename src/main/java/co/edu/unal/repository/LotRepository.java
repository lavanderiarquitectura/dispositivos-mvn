package co.edu.unal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unal.model.Lot;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    List<Lot> findByTypeOperationAndTypeFabricAndIsFinished(Integer typeOperation, Integer fabricType, Boolean isFinished);
    Lot findTopByOrderByIdDesc();
    List<Lot> findByTypeOperationAndIsFinished(Integer typeOperation, Boolean isFinished);

}
