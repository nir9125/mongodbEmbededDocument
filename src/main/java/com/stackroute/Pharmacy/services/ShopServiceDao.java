package com.stackroute.Pharmacy.services;

import com.stackroute.Pharmacy.exception.MedicalShopIdAlreadyExists;
import com.stackroute.Pharmacy.exception.MedicineNameNotFoundException;
import com.stackroute.Pharmacy.exception.ShopIdDoesNotExistException;
import com.stackroute.Pharmacy.model.MedicalShop;
import com.stackroute.Pharmacy.model.Medicine;

import java.util.List;

public interface ShopServiceDao {
    MedicalShop addMedicalShop(MedicalShop shop);
    List<MedicalShop> getAllMedicalShop();

    MedicalShop addMedicine(String shopId, Medicine medicine) throws ShopIdDoesNotExistException;

    List<MedicalShop> getMedicalShopByAddr(String addr);

    boolean deleteMedicine(String shopid, String medid) throws ShopIdDoesNotExistException, MedicineNameNotFoundException;
    public Medicine getMedicineByName(String shopid, String medname) throws ShopIdDoesNotExistException, MedicineNameNotFoundException ;

    }
