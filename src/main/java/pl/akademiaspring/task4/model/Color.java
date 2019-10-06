package pl.akademiaspring.task4.model;

public enum Color {
    WHITE("White"),
    RED("Red"),
    BLACK("Black"),
    BLUE("Blue");

    private final String displayValue;

    private Color(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
