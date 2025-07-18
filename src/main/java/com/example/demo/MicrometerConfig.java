package com.example.demo;

import io.micrometer.azuremonitor.AzureMonitorConfig;
import io.micrometer.azuremonitor.AzureMonitorMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfig {

	private static final String INSTRUMENTATION_KEY = "4f741a53-9d02-4f93-84d2-a7ac938e348f";

	@Bean
	public MeterRegistry azureMeterRegistry() {
		AzureMonitorConfig azureMonitorConfig = new AzureMonitorConfig() {
			@Override
			public String instrumentationKey() {
				return INSTRUMENTATION_KEY;
			}

			@Override
			public String get(String key) {
				return null;
			}
		};
		return new AzureMonitorMeterRegistry(azureMonitorConfig, Clock.SYSTEM);
	}
}

