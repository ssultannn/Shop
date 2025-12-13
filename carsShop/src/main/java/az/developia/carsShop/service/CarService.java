package az.developia.carsShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.carsShop.entity.Car;
import az.developia.carsShop.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;

	public List<Car> getAll() {
		return carRepository.findAll();
	}

	public Optional<Car> getById(Long id) {
		return carRepository.findById(id);
	}

	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

	public List<Car> getByRating(Integer rating) {
		return carRepository.findByRatingGreaterThanEqual(rating);

	}

	public List<Car> search(String keyword) {
		return carRepository.findByBrandContainingIgnoreCase(keyword);
	}

	public List<Car> getByType(String type) {
		return carRepository.findByType(type);

	}

	public List<Car> getPriceAsc() {
		return carRepository.findAllByOrderByPriceAsc();
	}

	public List<Car> getPriceDesc() {
		return carRepository.findAllByOrderByPriceDesc();

	}

	public Car updateCar(Long id, Car updatedCar) {
		Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("car not found"));
		car.setBrand(updatedCar.getBrand());
		car.setModel(updatedCar.getModel());
		car.setType(updatedCar.getType());
		car.setColor(updatedCar.getColor());
		car.setDescription(updatedCar.getDescription());
		car.setPrice(updatedCar.getPrice());
		car.setImgUrl(updatedCar.getImgUrl());
		car.setRating(updatedCar.getRating());
		car.setOwnerId(updatedCar.getOwnerId());
		return carRepository.save(car);
	}

}
