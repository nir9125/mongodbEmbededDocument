package com.stackroute.Pharmacy.services;

import com.stackroute.Pharmacy.exception.MedicineIdAlreadyExistException;
import com.stackroute.Pharmacy.exception.MedicineIdNotFoundException;
import com.stackroute.Pharmacy.model.Medicine;

import java.util.List;

public interface MedicineDao {
    public Medicine addMedicine(Medicine medicine) throws MedicineIdAlreadyExistException;
    public List<Medicine> getAllMedicine();

    boolean deleteMedicineById(String id) throws MedicineIdNotFoundException;
    List<Medicine> getMedicinceBasedOnCost(int price);
    Medicine getMedicienceByName(String name);
}
