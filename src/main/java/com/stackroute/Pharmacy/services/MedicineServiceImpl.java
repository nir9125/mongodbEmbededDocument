package com.stackroute.Pharmacy.services;

import com.stackroute.Pharmacy.exception.MedicineIdAlreadyExistException;
import com.stackroute.Pharmacy.exception.MedicineIdNotFoundException;
import com.stackroute.Pharmacy.model.Medicine;
import com.stackroute.Pharmacy.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineDao{

    @Autowired
    MedicineRepository repository;


    @Override
    public Medicine addMedicine(Medicine medicine) throws MedicineIdAlreadyExistException {
        Optional<Medicine> optionalMedicine = repository.findById(medicine.getMedId());
        if(optionalMedicine.isEmpty()){
            Medicine medicine1 = repository.save(medicine);
            return medicine1;
        }
        else
            throw new MedicineIdAlreadyExistException();
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return repository.findAll();
    }

    @Override
    public boolean deleteMedicineById(String id) throws MedicineIdNotFoundException {
        Optional<Medicine> optionalMedicine = repository.findById(id);
        if(optionalMedicine.isPresent()){
            repository.deleteById(id);
        }
        else
            throw new MedicineIdNotFoundException();
            return true;

    }

    @Override
    public List<Medicine> getMedicinceBasedOnCost(int price) {
        return repository.findByPriceGreaterThan(price);
    }

    @Override
    public Medicine getMedicienceByName(String name) {
        return repository.findByMedName(name);
    }
}
