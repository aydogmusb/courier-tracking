package com.example.couriertracking.controller;

import com.example.couriertracking.form.InfoAddForm;
import com.example.couriertracking.service.CourierInfoService;
import com.example.couriertracking.service.CourierLocationCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class CourierTrackingController {

    private final CourierInfoService courierInfoService;
    private final CourierLocationCalculatorService courierLocationCalculatorService;

    public CourierTrackingController(CourierInfoService courierInfoService,
                                     CourierLocationCalculatorService courierLocationCalculatorService) {
        this.courierInfoService = courierInfoService;
        this.courierLocationCalculatorService = courierLocationCalculatorService;
    }

    @GetMapping("/info/add")
    public ModelAndView infoAddPage() {
        return new ModelAndView("addInfo", "infoAddForm", new InfoAddForm());
    }

    @PostMapping(value = "/info")
    public String checkCourierInfo(@ModelAttribute("infoAddForm") InfoAddForm infoAddForm) {
        courierInfoService.addCourierInfo(infoAddForm);
        try {
            courierLocationCalculatorService.checkCoordinates(infoAddForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/info/add";
    }
}
