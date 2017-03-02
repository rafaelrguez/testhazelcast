package es.innovationstrategies.pruebatecnica.testhazelcast.service;

import java.util.List;

import es.innovationstrategies.pruebatecnica.testhazelcast.model.User;

public interface IUserService {
	public void addUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
	public void deleteUser(String userEmail);
	
    public List<User> getAllUsers();
    public User getUser(String userEmail);
    public List<User> searchUsers(String filtroEmail);

}
