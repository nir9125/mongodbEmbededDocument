package com.stackroute.Pharmacy.controller;

import com.stackroute.Pharmacy.exception.MedicalShopIdAlreadyExists;
import com.stackroute.Pharmacy.exception.MedicineNameNotFoundException;
import com.stackroute.Pharmacy.exception.ShopIdDoesNotExistException;
import com.stackroute.Pharmacy.model.MedicalShop;
import com.stackroute.Pharmacy.model.Medicine;
import com.stackroute.Pharmacy.services.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    ShopServiceImpl service ;
    @PostMapping("addshop")
    public ResponseEntity<?> addMedicalShop(@RequestBody MedicalShop shop){

            MedicalShop medicalShop = service.addMedicalShop(shop);
            return new ResponseEntity<MedicalShop>(medicalShop, HttpStatus.CREATED);

    }


    @PostMapping("/addMedicine/{shopid}")
      public ResponseEntity<?> addMedicine(@PathVariable String shopid, @RequestBody Medicine medicine){
        MedicalShop medicalShop = null;
        try {
            medicalShop = service.addMedicine(shopid,medicine);
            return new ResponseEntity<MedicalShop>(medicalShop,HttpStatus.CREATED);
        } catch (ShopIdDoesNotExistException e) {
            return new ResponseEntity<String>("Invalid Shop Id",HttpStatus.CONFLICT);
        }

    }
    @GetMapping("viewallshop")
    public ResponseEntity<?> veiwAllShop(){
        List<MedicalShop> medicalShops = service.getAllMedicalShop();
        return new ResponseEntity<List>(medicalShops,HttpStatus.OK);
    }

    @GetMapping("viewbyaddr/{addr}")
    public ResponseEntity<List> viewByAddress(@PathVariable String addr){
        return  new ResponseEntity<List>(service.getMedicalShopByAddr(addr),HttpStatus.OK);
    }


    @DeleteMapping("delete/medicine/{shopid}/{medid}")
    public ResponseEntity<?> deletemedicine(@PathVariable String shopid, @PathVariable String medid){
        try {
            boolean result = service.deleteMedicine(shopid,medid);
            return  new ResponseEntity<String>("Medicine is Deleted",HttpStatus.OK);
        } catch (ShopIdDoesNotExistException e) {
            return new ResponseEntity<String>("INvalid Shop id",HttpStatus.NOT_FOUND);
        } catch (MedicineNameNotFoundException e) {
            return new ResponseEntity<String>("Medicine id not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/viewbyname/medicine/{shopid}/{medname}")
    public ResponseEntity<?> getMedicineByName(@PathVariable("shopid") String shopid, @PathVariable String medname){
        try {
            Medicine  medicine  = service.getMedicineByName(shopid,medname);
            return new ResponseEntity<Medicine>( medicine,HttpStatus.OK);
        } catch (ShopIdDoesNotExistException e) {
            return new ResponseEntity<String>("INvalid Shop id",HttpStatus.NOT_FOUND);
        } catch (MedicineNameNotFoundException e) {
            return new ResponseEntity<String>("Medicine id not found",HttpStatus.NOT_FOUND);
        }

    }

}
