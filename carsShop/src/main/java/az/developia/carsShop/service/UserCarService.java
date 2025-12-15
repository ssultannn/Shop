package az.developia.carsShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.carsShop.dto.CarDto;
import az.developia.carsShop.entity.Car;
import az.developia.carsShop.entity.User;
import az.developia.carsShop.repository.CarRepository;
import az.developia.carsShop.repository.UserRepository;

@Service
public class UserCarService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	private User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
	}

	public Car saveCar(CarDto carDto) {
		User user = getCurrentUser();
		Car car = new Car();

		car.setBrand(carDto.getBrand());
		car.setModel(carDto.getModel());
		car.setType(carDto.getType());
		car.setColor(carDto.getColor());
		car.setDescription(carDto.getDescription());
		car.setPrice(carDto.getPrice());
		car.setImgUrl(carDto.getImgUrl());
		car.setRating(carDto.getRating());

		car.setOwnerId(user.getId());
		return carRepository.save(car);
	}

	public List<Car> getMyCar() {
		User user = getCurrentUser();
		return carRepository.findByOwnerId(user.getId());
	}

	public void deleteCar(Long id) {
		User user = getCurrentUser();
		boolean isOwner = carRepository.existsByIdAndOwnerId(id, user.getId());
		if (!isOwner) {
			throw new RuntimeException("вы не можете удалить чужую машину");
		}
		carRepository.deleteById(id);
	}

	public Car updateCar(Long id, Car updatedCar) {
		
		User user = getCurrentUser();
		boolean isOwner = carRepository.existsByIdAndOwnerId(id, user.getId());
		if (!isOwner) {
			throw new RuntimeException("вы не можете обновить чужую машину");
		}
		
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
