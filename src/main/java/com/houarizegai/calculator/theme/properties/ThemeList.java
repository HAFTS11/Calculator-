package com.houarizegai.calculator.theme.properties;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThemeList {
    
    // List to store multiple themes
    private List<Theme> themes;

    // Getter for the list of themes
    public List<Theme> getThemes() {
        return themes;
    }

    // Setter for the list of themes
    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    // Method to convert the list of themes to a map, with theme name as key
    public Map<String, Theme> getThemesAsMap() {
        // Stream through the list of themes and collect them into a map
        // with the theme name as the key and the Theme object as the value
        return themes.stream().collect(Collectors.toMap(Theme::getName, Function.identity()));
    }
}
