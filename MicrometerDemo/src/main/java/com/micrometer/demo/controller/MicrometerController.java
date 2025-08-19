package com.micrometer.demo.controller;

import io.micrometer.core.instrument.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class MicrometerController {
    private final MeterRegistry meterRegistry;
    private final Counter requestCounter;

    public MicrometerController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.requestCounter = Counter.builder("api.requests")
                .description("Total number of API requests")
                .register(meterRegistry);
    }

    @GetMapping("/hello")
    public String sayHello() {
        // Increment the request counter
        requestCounter.increment();
        return "Hello, World!";
    }

    @GetMapping("/metric/scrape")
    public String metricScrape() {
        List<Meter> meters = meterRegistry.getMeters();

        StringBuilder sb = new StringBuilder();
        for (Meter meter : meters) {
            List<Tag> tags = meter.getId().getTags();
//            sb.append(meter.getId()).append(" ");
            for (Tag tag :
                    tags) {
                sb.append(tag.getKey()).append(":").append(tag.getValue()).append(" ");
            }
            sb.append(meter.getId().getName()).append("=").append(getMeterValue(meter));
            sb.append("\n \n \n --------");
        }
        return sb.toString();
    }

    private double getMeterValue(Meter meter) {
        if (meter instanceof Counter) {
            return ((Counter) meter).count();
        } else if (meter instanceof Timer) {
            return ((Timer) meter).totalTime(TimeUnit.MILLISECONDS);
        } else if (meter instanceof Gauge) {
            return ((Gauge) meter).value();
        }
        return 0;
    }
}
