package com.houarizegai.calculator.theme;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.houarizegai.calculator.theme.properties.Theme;
import com.houarizegai.calculator.theme.properties.ThemeList;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class ThemeLoader {

    // Private constructor to prevent instantiation of the class
    private ThemeLoader() {
        throw new AssertionError("Constructor is not allowed");
    }

    // Method to load themes from a YAML file and return them as a map
    public static Map<String, Theme> loadThemes() {
        // Create an ObjectMapper for YAML processing
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        
        // Register any modules (such as those for Java 8 date/time types)
        mapper.findAndRegisterModules();
        
        try {
            // Read and parse the YAML file into a ThemeList object
            ThemeList themeList = mapper.readValue(new File("src/main/resources/application.yaml"), ThemeList.class);
            
            // Return the themes as a map
            return themeList.getThemesAsMap();
        } catch (IOException e) {
            // If an error occurs (e.g., file not found), return an empty map
            return Collections.emptyMap();
        }
    }
}
