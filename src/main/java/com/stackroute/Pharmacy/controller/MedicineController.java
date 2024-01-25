package com.stackroute.Pharmacy.controller;

import com.stackroute.Pharmacy.exception.MedicineIdAlreadyExistException;
import com.stackroute.Pharmacy.exception.MedicineIdNotFoundException;
import com.stackroute.Pharmacy.model.Medicine;
import com.stackroute.Pharmacy.services.MedicineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharma")
public class MedicineController {
    @Autowired
    MedicineServiceImpl service;

    @PostMapping("/addmedicine")
    public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine){
        try {
            Medicine medicine1 = service.addMedicine(medicine);
            return new ResponseEntity<Medicine>(medicine1, HttpStatus.CREATED);
        } catch (MedicineIdAlreadyExistException e) {
            return new ResponseEntity<String>("Id Already Exist in DB", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/viewall")
    public ResponseEntity<?> getAllMedicince(){
        List<Medicine> medicineList = service.getAllMedicine();
        return new ResponseEntity<List>(medicineList,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteMedicinceByID(@PathVariable String id){
        try {
            boolean result = service.deleteMedicineById(id);
            return new ResponseEntity<String>("Medicince Deleted",HttpStatus.OK);
        } catch (MedicineIdNotFoundException e) {
          return new ResponseEntity<String>("Id not Found in DB",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("viewbyprice/{maxprice}")
    public ResponseEntity<?> viewByPrice(@PathVariable int maxprice){
        List<Medicine> medicineList = service.getMedicinceBasedOnCost(maxprice);
        return new ResponseEntity<List>(medicineList,HttpStatus.OK);
    }

    @GetMapping("viewbyname/{name}")
    public ResponseEntity<Medicine> viewByName(@PathVariable String name){
        return  new ResponseEntity<>(service.getMedicienceByName(name),HttpStatus.OK);
    }
}
