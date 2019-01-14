package pl.lukasz.edukacja.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.domain.UserRole;

public interface UserService {
	public void addUser(User user);
	
	public User getUser(int id);
	
	@Secured("ROLE_ADMIN")
	public void removeUser(int id);
	
	public List<User> listUser();
	
	@Secured("ROLE_ADMIN")
	public void editUser(User user);
	
	public String hashPassword(String password);
	
	public void addUserRole(UserRole userRole);
	public List<UserRole> listUserRole();
	public void removeUserRole(int id);
	public UserRole getUserRole(int id);
	public User findByLogin(String login);
	
	@Secured("ROLE_ADMIN")
	public void editUserWithoutHashPassword(User user);
}
