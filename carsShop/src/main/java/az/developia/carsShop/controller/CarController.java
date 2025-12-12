package az.developia.carsShop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.carsShop.entity.Car;
import az.developia.carsShop.service.CarService;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
	@Autowired
	private CarService carService;

	@GetMapping
	public List<Car> getAll() {
		return carService.getAll();

	}
	@GetMapping("/{id}")
	public Optional<Car> getById(@PathVariable Long id) {
		return carService.getById(id);
	}

	@PostMapping
	public Car сreateCar(@RequestBody Car car) {
		return carService.saveCar(car);
	}
	@DeleteMapping("/{id}")
	public void deleteCar(@PathVariable Long id) {
		carService.deleteCar(id);
	}
}
