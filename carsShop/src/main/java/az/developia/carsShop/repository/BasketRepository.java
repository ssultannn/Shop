package az.developia.carsShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.carsShop.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	List<Basket> findByUserId(Long userId);

	Optional<Basket> findByUserIdAndCarId(Long userId, Long carId);

	void deleteByUserIdAndCarId(Long userId, Long carId);

}
