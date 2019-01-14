package pl.lukasz.edukacja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.lukasz.edukacja.dao.UserDAO;
import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.domain.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Transactional
	public void addUser(User user) {
		user.getUserRole().add(userDAO.findRoleByName("ROLE_USER"));
		user.setPassword(hashPassword(user.getPassword()));
		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		userDAO.removeUser(id);
	}

	@Override
	@Transactional
	public List<User> listUser() {
		return userDAO.listUser();
	}

	@Override
	@Transactional //po co to?
	public void editUser(User user) {
		user.setPassword(hashPassword(user.getPassword()));
		userDAO.editUser(user);
	}
	
	@Override
	@Transactional
	public void editUserWithoutHashPassword(User user) {
		userDAO.editUser(user);
	}

	@Transactional 
	public String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Transactional 
	public void addUserRole(UserRole userRole) {
		userDAO.addRole(userRole);
	}

	@Transactional 
	public List<UserRole> listUserRole() {
		return userDAO.listUserRole();
	}

	@Transactional 
	public void removeUserRole(int id) {
		userDAO.removeUserRole(id);
		
	}

	@Transactional 
	public UserRole getUserRole(int id) {
		return userDAO.getUserRole(id);
	}

	@Override
	public User findByLogin(String login) {
		return userDAO.findByLogin(login);
	}

}
