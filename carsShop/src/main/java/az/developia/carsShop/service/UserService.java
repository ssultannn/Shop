package az.developia.carsShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.developia.carsShop.dto.LoginRequestDto;
import az.developia.carsShop.entity.Authority;
import az.developia.carsShop.entity.User;
import az.developia.carsShop.jwt.JwtUtil;
import az.developia.carsShop.repository.AuthorityRepository;
import az.developia.carsShop.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	JwtUtil jwtUtil;
	
	public User  register(User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("такой username существует");
		}
		User userEntity=new User();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setEnabled(true);
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		userEntity.setSurname(user.getSurname());
		userEntity.setPhoneNumber(user.getPhoneNumber());
		userRepository.save(userEntity);

		Authority authority = new Authority();
		authority.setUsername(user.getUsername());
		authority.setAuthority("ROLE_USER");

		authorityRepository.save(authority);
		return user;
	}
	
	public String login(LoginRequestDto loginRequest) {
		User user=userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()->new RuntimeException("неверный username"));
		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new RuntimeException("неверный пароль");
		}
		return jwtUtil.generateToken(loginRequest.getUsername());
	}
}
