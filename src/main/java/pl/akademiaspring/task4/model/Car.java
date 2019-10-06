package pl.akademiaspring.task4.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Car {

    @NotNull(message = "Please enter id")
    private Long carId;
    private String mark;
    private String model;
    private Color color;

    public Car() {

    }

    public Car(Long id, String mark, String model, Color color) {
        this.carId = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
