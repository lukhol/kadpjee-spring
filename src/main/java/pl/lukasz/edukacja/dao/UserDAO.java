package pl.lukasz.edukacja.dao;

import java.util.List;

import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.domain.UserRole;

public interface UserDAO {
	public void addUser(User user);
	public User getUser(int id);
	public void removeUser(int id);
	public List<User> listUser();
	public void editUser(User user);
	public User findByLogin(String login);
	
	public void addRole(UserRole userRole);
	public List<UserRole> listUserRole();
	public void removeUserRole (int id);
	public UserRole getUserRole(int id);
	public UserRole findRoleByName(String role);
	
}
