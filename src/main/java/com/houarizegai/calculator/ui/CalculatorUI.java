package com.houarizegai.calculator.ui;

import com.houarizegai.calculator.theme.properties.Theme;
import com.houarizegai.calculator.theme.ThemeLoader;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.Color;
import javax.swing.*;

import static com.houarizegai.calculator.util.ColorUtil.hex2Color;

public class CalculatorUI {

    // Constants for font name, regex, application title, window dimensions, button dimensions, and margins
    private static final String FONT_NAME = "Comic Sans MS";
    private static final String DOUBLE_OR_NUMBER_REGEX = "([-]?\\d+[.]\\d*)|(\\d+)|(-\\d+)";
    private static final String APPLICATION_TITLE = "Calculator";
    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    // Main window frame
    private final JFrame window;
    // Combo boxes for calculator type and theme
    private JComboBox<String> comboCalculatorType;
    private JComboBox<String> comboTheme;
    // Input screen text field
    private JTextField inputScreen;
    // Calculator buttons
    private JButton btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnEqual, btnRoot, btnPower, btnLog;
    // Variables to manage the state of the calculator
    private char selectedOperator = ' ';
    private boolean go = true; // For calculating with operator other than '='
    private boolean addToDisplay = true; // To concatenate numbers in display
    private double typedValue = 0;
    // Map to store themes
    private final Map<String, Theme> themesMap;

    // Constructor
    public CalculatorUI() {
        // Load themes
        themesMap = ThemeLoader.loadThemes();

        // Initialize the main window
        window = new JFrame(APPLICATION_TITLE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);

        // Define columns and rows for button positions
        int[] columns = {MARGIN_X, MARGIN_X + 90, MARGIN_X + 90 * 2, MARGIN_X + 90 * 3, MARGIN_X + 90 * 4};
        int[] rows = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 100 + 80, MARGIN_Y + 100 + 80 * 2, MARGIN_Y + 100 + 80 * 3, MARGIN_Y + 100 + 80 * 4};

        // Initialize components
        initInputScreen(columns, rows);
        initButtons(columns, rows);
        initCalculatorTypeSelector();
        initThemeSelector();

        // Configure window settings
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    // Method to calculate the result based on the operator
    public double calculate(double firstNumber, double secondNumber, char operator) {
        switch (operator) {
            case '+':
                return firstNumber + secondNumber;
            case '-':
                return firstNumber - secondNumber;
            case '*':
                return firstNumber * secondNumber;
            case '/':
                return firstNumber / secondNumber;
            case '%':
                return firstNumber % secondNumber;
            case '^':
                return Math.pow(firstNumber, secondNumber);
            default:
                return secondNumber;
        }
    }

    // Initialize the theme selector combo box
    private void initThemeSelector() {
        comboTheme = createComboBox(themesMap.keySet().toArray(new String[0]), 230, 30, "Theme");
        comboTheme.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED)
                return;

            String selectedTheme = (String) event.getItem();
            applyTheme(themesMap.get(selectedTheme));
        });

        // Apply the first theme by default if available
        if (themesMap.entrySet().iterator().hasNext()) {
            applyTheme(themesMap.entrySet().iterator().next().getValue());
        }
    }

    // Initialize the input screen text field
    private void initInputScreen(int[] columns, int[] rows) {
        inputScreen = new JTextField("0");
        inputScreen.setBounds(columns[0], rows[0], 350, 70);
        inputScreen.setEditable(false);
        inputScreen.setBackground(Color.WHITE);
        inputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        window.add(inputScreen);
    }

    // Initialize the calculator type selector combo box
    private void initCalculatorTypeSelector() {
        comboCalculatorType = createComboBox(new String[]{"Standard", "Scientific"}, 20, 30, "Calculator type");
        comboCalculatorType.addItemListener(event -> {
            if (event.getStateChange() != ItemEvent.SELECTED)
                return;

            String selectedItem = (String) event.getItem();
            switch (selectedItem) {
                case "Standard":
                    window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                    btnRoot.setVisible(false);
                    btnPower.setVisible(false);
                    btnLog.setVisible(false);
                    break;
                case "Scientific":
                    window.setSize(WINDOW_WIDTH + 80, WINDOW_HEIGHT);
                    btnRoot.setVisible(true);
                    btnPower.setVisible(true);
                    btnLog.setVisible(true);
                    break;
            }
        });
    }

    // Initialize all calculator buttons and their action listeners
    private void initButtons(int[] columns, int[] rows) {
        // 'C' button to clear the input
        btnC = createButton("C", columns[0], rows[1]);
        btnC.addActionListener(event -> {
            inputScreen.setText("0");
            selectedOperator = ' ';
            typedValue = 0;
        });

        // 'Back' button to delete the last character
        btnBack = createButton("<-", columns[1], rows[1]);
        btnBack.addActionListener(event -> {
            String str = inputScreen.getText();
            StringBuilder str2 = new StringBuilder();
            for (int i = 0; i < (str.length() - 1); i++) {
                str2.append(str.charAt(i));
            }
            if (str2.toString().equals("")) {
                inputScreen.setText("0");
            } else {
                inputScreen.setText(str2.toString());
            }
        });

        // '%' button for modulus operation
        btnMod = createButton("%", columns[2], rows[1]);
        btnMod.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()) || !go)
                return;

            typedValue = calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
            if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                inputScreen.setText(String.valueOf((int) typedValue));
            } else {
                inputScreen.setText(String.valueOf(typedValue));
            }
            selectedOperator = '%';
            go = false;
            addToDisplay = false;
        });

        // '/' button for division operation
        btnDiv = createButton("/", columns[3], rows[1]);
        btnDiv.addActionListener(event -> {
            if (!Pattern.matches(DOUBLE_OR_NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = calculate(typedValue, Double.parseDouble(inputScreen.getText()), selectedOperator);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(typedValue))) {
                    inputScreen.setText(String.valueOf((int) typedValue));
                } else {
                    inputScreen.setText(String.valueOf(typedValue));
                }
                selectedOperator = '/';
                go = false;
                addToDisplay = false;
            } else {
                selectedOperator = '/';
            }
        });

        // Number buttons and their action listeners
        btn7 = createButton("7", columns[0], rows[2]);
        btn7.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("7");
                } else {
                    inputScreen.setText(inputScreen.getText() + "7");
                }
            } else {
                inputScreen.setText("7");
                addToDisplay = true;
            }
            go = true;
        });

        btn8 = createButton("8", columns[1], rows[2]);
        btn8.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("8");
                } else {
                    inputScreen.setText(inputScreen.getText() + "8");
                }
            } else {
                inputScreen.setText("8");
                addToDisplay = true;
            }
            go = true;
        });

        btn9 = createButton("9", columns[2], rows[2]);
        btn9.addActionListener(event -> {
            if (addToDisplay) {
                if (Pattern.matches("[0]*", inputScreen.getText())) {
                    inputScreen.setText("9");
