package com.houarizegai.calculator.theme.properties;

public class Theme {

    // Variables to store theme properties
    private String name;                       // Name of the theme
    private String applicationBackground;      // Background color of the application
    private String textColor;                  // Color of the text
    private String btnEqualTextColor;          // Text color of the equal button
    private String operatorBackground;         // Background color of the operator buttons
    private String numbersBackground;          // Background color of the number buttons
    private String btnEqualBackground;         // Background color of the equal button

    // Getter for the theme name
    public String getName() {
        return name;
    }

    // Setter for the theme name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the application background color
    public String getApplicationBackground() {
        return applicationBackground;
    }

    // Setter for the application background color
    public void setApplicationBackground(String applicationBackground) {
        this.applicationBackground = applicationBackground;
    }

    // Getter for the text color
    public String getTextColor() {
        return textColor;
    }

    // Setter for the text color
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    // Getter for the equal button text color
    public String getBtnEqualTextColor() {
        return btnEqualTextColor;
    }

    // Setter for the equal button text color
    public void setBtnEqualTextColor(String btnEqualTextColor) {
        this.btnEqualTextColor = btnEqualTextColor;
    }

    // Getter for the operator buttons background color
    public String getOperatorBackground() {
        return operatorBackground;
    }

    // Setter for the operator buttons background color
    public void setOperatorBackground(String operatorBackground) {
        this.operatorBackground = operatorBackground;
    }

    // Getter for the number buttons background color
    public String getNumbersBackground() {
        return numbersBackground;
    }

    // Setter for the number buttons background color
    public void setNumbersBackground(String numbersBackground) {
        this.numbersBackground = numbersBackground;
    }

    // Getter for the equal button background color
    public String getBtnEqualBackground() {
        return btnEqualBackground;
    }

    // Setter for the equal button background color
    public void setBtnEqualBackground(String btnEqualBackground) {
        this.btnEqualBackground = btnEqualBackground;
    }
}
