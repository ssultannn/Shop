package az.developia.carsShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.carsShop.entity.Basket;
import az.developia.carsShop.entity.User;
import az.developia.carsShop.repository.BasketRepository;
import az.developia.carsShop.repository.CarRepository;
import az.developia.carsShop.repository.UserRepository;

@Service
public class BasketService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private BasketRepository basketRepository;

	private User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
	}

	public void addToBasket(Long carId) {
		User user = getCurrentUser();
		carRepository.findById(carId).orElseThrow(() -> new RuntimeException("car not found"));
		Basket basket=basketRepository.findByUserIdAndCarId(user.getId(), carId).orElse(null);
		
		if (basket==null) {
			Basket newbasket = new Basket();
			newbasket.setUserId(user.getId());
			newbasket.setCarId(carId);
			newbasket.setQuantity(1);
			basketRepository.save(newbasket);
		}else {
			basket.setQuantity(basket.getQuantity()+1);
			basketRepository.save(basket);

		}

		
	}

	public List<Basket> myBasket() {
		User user = getCurrentUser();
		return basketRepository.findByUserId(user.getId());
	}

	public void removeFromBasket(Long carId) {
		User user = getCurrentUser();
		Basket basket = basketRepository.findByUserIdAndCarId(user.getId(), carId)
				.orElseThrow(() -> new RuntimeException("car not found"));
		basketRepository.delete(basket);
	}

}
