package com.orangehrmlive.automation.utils;

import java.util.UUID;

public class TestDataGenerator {

    public static String generateUniqueLastName() {
        return "Test" + UUID.randomUUID().toString().substring(0, 8);
    }
}