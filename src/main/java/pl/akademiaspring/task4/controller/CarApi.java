package pl.akademiaspring.task4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspring.task4.model.Car;
import pl.akademiaspring.task4.service.CarService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.Optional;


@Controller
public class CarApi {

    private CarService carService;

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/list")
    public String getCars(Model model) {

        model.addAttribute("cars", carService.getCarList());

        return "listCars";
    }

    @GetMapping("/searchCar")
    public String searchCar(Model model) {

        model.addAttribute("car", new Car());

        return "searchCar";
    }

    @GetMapping("/getCarById")
    public String getCar(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("car", car);

            return "searchCar";
        }

        Optional<Car> first = carService.getCarList()
                .stream().filter(c -> c.getCarId() == car.getCarId()).findFirst();

        if (first.isPresent()) {
            model.addAttribute("car", first.get());
            return "showCar";
        }

        return "redirect:/home";
    }

    @GetMapping("/add-car")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());

        return "addCar";
    }

    @PostMapping("/save-new-car")
    public String saveNewCar(@ModelAttribute Car car) {

        Optional<Long> maxId = carService.getCarList()
                .stream()
                .map(c -> c.getCarId())
                .max(Comparator.naturalOrder());

        if (maxId.isPresent()) {
            car.setCarId(maxId.get() + 1);
        } else {
            car.setCarId(1L);
        }

        carService.getCarList().add(car);

        return "redirect:/list";
    }

    @GetMapping("/modifyCarColor/{id}")
    public String modifyColorCarById(@PathVariable long id, Model model) {
        Optional<Car> first = carService.getCarList()
                .stream().filter(c -> c.getCarId() == id).findFirst();

        if(first.isPresent()) {
            model.addAttribute("car", first.get());
            return "modifyCarColor";
        }

        return "redirect:/list";
    }

    @PostMapping("/updateCar")
    public String updateCar(@ModelAttribute Car car) {
        Optional<Car> first = carService.getCarList()
                .stream().filter(c -> c.getCarId() == car.getCarId()).findFirst();

        if(first.isPresent()) {
            first.get().setColor(car.getColor());
        }

        return "redirect:/list";
    }

    @GetMapping(path = "/delete/{id}")
    public String removeCar(@PathVariable long id) {
        Optional<Car> first = carService.getCarList()
                .stream().filter(c -> c.getCarId() == id).findFirst();

        if (first.isPresent()) {
            carService.getCarList().remove(first.get());
        }

        return "redirect:/list";
    }


}
