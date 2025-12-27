package az.developia.carsShop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.carsShop.dto.CarDto;
import az.developia.carsShop.entity.Car;
import az.developia.carsShop.service.CarService;
import az.developia.carsShop.service.UserCarService;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
	@Autowired
	private CarService carService;
	@Autowired
	private UserCarService userCarService;

	@GetMapping
	public List<Car> getAll() {

		return carService.getAll();

	}

	@GetMapping("/{id}")
	public Optional<Car> getById(@PathVariable Long id) {
		return carService.getById(id);
	}

	@PostMapping
	public Car сreateCar(@RequestBody CarDto carDto) {
		return userCarService.saveCar(carDto);
	}

	@DeleteMapping("/{id}")
	public void deleteCar(@PathVariable Long id) {
		userCarService.deleteCar(id);
	}

	@GetMapping("/search/{q}")
	public List<Car> search(@PathVariable String q) {
		return carService.search(q);
	}

	@GetMapping("/rating/{rating}")
	public List<Car> getByRating(@PathVariable Integer rating) {
		return carService.getByRating(rating);
	}

	@GetMapping("/type/{type}")
	public List<Car> getByType(@PathVariable String type) {
		return carService.getByType(type);
	}

	@GetMapping("/price/asc")
	public List<Car> priceAsc() {
		return carService.getPriceAsc();

	}

	@GetMapping("/price/desc")
	public List<Car> priceDesc() {
		return carService.getPriceDesc();

	}

	@PutMapping("/{id}")
	public Car updatedCar(@PathVariable Long id, @RequestBody Car car) {
		return userCarService.updateCar(id, car);
	}

	@GetMapping("/my-cars")
	public List<Car> myCars() {
		return userCarService.getMyCar();
	}

}
