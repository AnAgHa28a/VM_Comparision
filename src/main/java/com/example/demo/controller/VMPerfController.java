package com.example.demo.controller;
import io.micrometer.core.instrument.MeterRegistry;
import com.example.demo.service.VMPerfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VMPerfController {

    @Autowired
    private VMPerfService vmperfService;
    private MeterRegistry meterRegistry;

    @GetMapping("/vmperf")
    public int VMPerf(@RequestParam int c, @RequestParam int m) {
        try {
            meterRegistry.gauge("vmperf.api.call.start", 1); // Passing constant value when api call starts
            // CPU-intensive logic
            vmperfService.computeCPUIntensiveFactorial(c);

            // Memory-intensive logic
            vmperfService.loadMemory(m);
            meterRegistry.gauge("vmperf.api.call.end", 1); // Passing constant value when api call ends
            return 1; // success
        } catch (Exception e) {
            return 0; // error occurred
        }
    }
}
