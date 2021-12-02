package com.eleks.academy.pharmagator.controllers.ui;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.services.ExportService;
import com.eleks.academy.pharmagator.services.ImportService;
import com.eleks.academy.pharmagator.services.MedicineService;
import com.eleks.academy.pharmagator.services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui")
public class NavigationController {

    private final MedicineService medicineService;
    private final PharmacyService pharmacyService;
    private final ExportService exportService;
    private final ImportService importService;

    @GetMapping({"", "/", "/index", "/index.html", "/home", "/homepage"})
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/medicine")
    public String getMedicines(Model model) {
        model.addAttribute("meds", medicineService.findAll());
        return "medicine";
    }

    @GetMapping("/price")
    public String getPrices(Model model) {
        List<Pharmacy> pharmacies = pharmacyService.findAll();

        model.addAttribute("pricesMap", exportService.getMapPricesFromDatabase());
        model.addAttribute("pharmacies", pharmacies);
        return "price";
    }

    @GetMapping("/addmedicine")
    public String addMedicine(Model model) {
       model.addAttribute("med",new MedicineDto());
        return "add-medicine";
    }

    @PostMapping()
    public String create(@ModelAttribute("med") MedicineDto medicineDto){
        importService.storeToDatabase(medicineDto);
        return "redirect:/ui";
    }

}
