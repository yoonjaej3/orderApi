package com.jyj.orderapi.common.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Slf4j
@Component
@AllArgsConstructor
public class EnvironmentComponent {

    private static final String PROJECT_NAME = "orderApi";
    private static final String VERSION = "0.0.0";
    private final Environment environment;

    public void showProject() {
        log.info("");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("  PROJECT NAME: {}", PROJECT_NAME);
        log.info("  VERSION: {}", VERSION);
        log.info("  PROFILE: {}", getProperties());
        log.info("  TIME ZONE: {}", TimeZone.getDefault().toZoneId());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("");
    }

    private String getProperties() {
        return String.join(",", environment.getActiveProfiles());
    }
}
