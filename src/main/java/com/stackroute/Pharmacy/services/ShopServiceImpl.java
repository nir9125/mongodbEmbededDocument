package com.stackroute.Pharmacy.services;

import com.stackroute.Pharmacy.exception.MedicalShopIdAlreadyExists;
import com.stackroute.Pharmacy.exception.MedicineNameNotFoundException;
import com.stackroute.Pharmacy.exception.ShopIdDoesNotExistException;
import com.stackroute.Pharmacy.model.MedicalShop;
import com.stackroute.Pharmacy.model.Medicine;
import com.stackroute.Pharmacy.repository.MedicineShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopServiceDao {
    @Autowired
    MedicineShopRepo repo;


    @Override
    public MedicalShop addMedicalShop(MedicalShop shop) {
        Optional<MedicalShop> medicalShop = repo.findById(shop.getShopId());
        if(medicalShop.isEmpty()){
        return repo.save(shop);
        }
        else
        {
            MedicalShop shop1 = medicalShop.get();
            List<Medicine> existMedicine = shop1.getMedicineList();
            List<Medicine> newMedicine = shop.getMedicineList();
            existMedicine.addAll(newMedicine);
            shop1.setMedicineList(existMedicine);
         return    repo.save(shop1);
        }
    }

    @Override
    public List<MedicalShop> getAllMedicalShop() {
        return repo.findAll();
    }

    @Override
    public MedicalShop addMedicine(String shopId, Medicine medicine) throws ShopIdDoesNotExistException {
      Optional<MedicalShop>medicalShop = repo.findById(shopId);
      if(medicalShop.isEmpty()){
          throw new ShopIdDoesNotExistException();
      }
      else {
          MedicalShop shop1 = medicalShop.get();
          List<Medicine> existMedicine = shop1.getMedicineList();
          existMedicine.add(medicine);
          shop1.setMedicineList(existMedicine);
          return    repo.save(shop1);
      }

    }

    @Override
    public List<MedicalShop> getMedicalShopByAddr(String addr) {
        return repo.findByAddr(addr);
    }

    @Override
    public boolean deleteMedicine(String shopid, String medid) throws ShopIdDoesNotExistException, MedicineNameNotFoundException {
        Optional<MedicalShop> medicalShop = repo.findById(shopid);
        if(medicalShop.isEmpty()){
            throw new ShopIdDoesNotExistException();
        }
        else {
            MedicalShop medicalShop1 = medicalShop.get();
            List<Medicine> existMedicine = medicalShop1.getMedicineList();
            existMedicine.removeIf((med) ->med.getMedId().equals(medid));
            medicalShop1.setMedicineList(existMedicine);
            repo.save(medicalShop1);
            return true;
        }
    }

    @Override
    public Medicine getMedicineByName(String shopid, String medname) throws ShopIdDoesNotExistException, MedicineNameNotFoundException {

        Optional<MedicalShop> medicalShop = repo.findById(shopid);
        if(shopid.isEmpty()){
            throw new ShopIdDoesNotExistException();
        }
        else {
            MedicalShop existShop = medicalShop.get();
            List<Medicine> existMedicineList = existShop.getMedicineList();
            Optional<Medicine> medresult = existMedicineList.stream().
                    filter(med -> med.getMedName().equals(medname)).findFirst();

            if(medresult.isEmpty()){
                throw new MedicineNameNotFoundException();
            }
            else {
                return medresult.get();
            }
        }


    }
}
