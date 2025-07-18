package com.example.demo.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VMPerfService {

    @Autowired
    private MeterRegistry meterRegistry;

    // CPU-Intensive: Simple loop (computes fast, minimal memory)
    public long computeCPUIntensiveFactorial(int n) {
        
        meterRegistry.counter("vmperf.cpu.factorial.calls").increment(); // Custom counter

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        meterRegistry.gauge("vmperf.cpu.factorial.lastvalue", result); // Optional gauge
        return result;
    }

    // Memory-Intensive: load n chunks of 10 MB each
    public int loadMemory(int chunks) {
        meterRegistry.counter("vmperf.memory.load.calls").increment(); // Count memory load calls

        List<byte[]> memoryList = new ArrayList<>();
        int totalBytes = 0;

        try {
            for (int i = 0; i < chunks; i++) {
                byte[] block = new byte[10 * 1024 * 1024]; // 10 MB
                memoryList.add(block);
                totalBytes += block.length;

                Thread.sleep(50); // Reduce CPU usage
            }
        } catch (OutOfMemoryError | InterruptedException e) {
            System.out.println("Memory limit reached or interrupted");
        }

        meterRegistry.gauge("vmperf.memory.bytes.allocated", totalBytes); // Report allocated memory
        
        return memoryList.size();
    }
}
