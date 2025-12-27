package az.developia.carsShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.carsShop.entity.Basket;
import az.developia.carsShop.entity.Car;
import az.developia.carsShop.service.BasketService;

@RestController
@RequestMapping("/basket")
public class BasketController {
	@Autowired
	private BasketService basketService;
	@PostMapping("/{carId}")
	public void addToBasket(@PathVariable Long carId) {
		basketService.addToBasket(carId);
	}
	@GetMapping
	public List<Basket> myBaskets(){
		return basketService.myBasket();
	}
	@DeleteMapping("{carId}")
	public void removeFromBasket(@PathVariable Long carId ) {
		basketService.removeFromBasket(carId);
	}
}
