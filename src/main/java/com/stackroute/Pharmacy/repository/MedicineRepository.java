package com.stackroute.Pharmacy.repository;

import com.stackroute.Pharmacy.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine,String> {
    List<Medicine> findByPriceGreaterThan(int price);
    Medicine findByMedName(String med);
}
