package az.developia.carsShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.carsShop.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	List<Car> findByBrandContainingIgnoreCase(String brand);
	
	List<Car> findByRatingGreaterThanEqual(Integer rating);
	
	List<Car> findByType(String Type);
	
	List<Car> findAllByOrderByPriceAsc();
	
	List<Car> findAllByOrderByPriceDesc();
}
