package az.developia.carsShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.carsShop.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
