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
}
