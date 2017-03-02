package es.innovationstrategies.pruebatecnica.testhazelcast.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import es.innovationstrategies.pruebatecnica.testhazelcast.model.User;

public interface IUserDAO {
	
	public void addUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
	public void deleteUser(String userEmail);
	
    public List<User> getAllUsers();
    public User getUser(String userEmail);
    public List<User> searchUsers(String filtroEmail);
    
    public Map<String, User> getUserMap(Collection<String> idCol);

}
