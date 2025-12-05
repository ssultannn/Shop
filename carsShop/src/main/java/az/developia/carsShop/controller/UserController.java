package az.developia.carsShop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.carsShop.dto.LoginRequestDto;
import az.developia.carsShop.entity.User;
import az.developia.carsShop.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
UserService userService;

@PostMapping("/register")
public User register(@RequestBody User user) {
	return userService.register(user);
}

@PostMapping("/login")
public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDto loginRequestDto){
	String token=userService.login(loginRequestDto);
	return ResponseEntity.ok(Map.of("token",token));
}
}
