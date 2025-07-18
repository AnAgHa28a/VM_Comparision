package com.example.demo.controller;

import com.example.demo.service.VMPerfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VMPerfController {

    @Autowired
    private VMPerfService vmperfService;

    @GetMapping("/vmperf")
    public int VMPerf(@RequestParam int c, @RequestParam int m) {
        try {
            // CPU-intensive logic
            vmperfService.computeCPUIntensiveFactorial(c);

            // Memory-intensive logic
            vmperfService.loadMemory(m);

            return 1; // success
        } catch (Exception e) {
            return 0; // error occurred
        }
    }
}
