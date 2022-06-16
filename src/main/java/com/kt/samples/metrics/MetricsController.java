package com.kt.samples.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {
    //counter_total
    Counter counter;

    public MetricsController(MeterRegistry registry) {
        counter = Counter.builder("___counter___")
                .description("Number of visits to the site")
                .register(registry);
    }

    @GetMapping("/")
    public String visitsCount() {
        counter.increment();
        return String.format("Already %s visit(s) !", Double.valueOf(counter.count()).longValue());
    }
}