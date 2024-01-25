package com.stackroute.Pharmacy.repository;

import com.stackroute.Pharmacy.model.MedicalShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineShopRepo extends MongoRepository<MedicalShop, String> {
    List<MedicalShop> findByAddr(String addr);
}
