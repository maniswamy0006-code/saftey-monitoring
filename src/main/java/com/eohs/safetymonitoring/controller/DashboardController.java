package com.eohs.safetymonitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @GetMapping("/worker/dashboard")
    public String workerDash() {
        return "worker-dashboard";
    }

    @GetMapping("/engineer/dashboard")
    public String engineerDash() {
        return "engineer-dashboard";
    }

    @GetMapping("/management/dashboard")
    public String managementDash() {
        return "management-dashboard";
    }
}
