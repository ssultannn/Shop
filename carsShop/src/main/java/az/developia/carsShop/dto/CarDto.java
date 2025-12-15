package az.developia.carsShop.dto;

import lombok.Data;

@Data
public class CarDto {
	private Long id;
	private String brand;
	private String model;
	private String type;
	private String color;
	private String description;
	private Double price;
	private String imgUrl;
	private Integer rating;
}
