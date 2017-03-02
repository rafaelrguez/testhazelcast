package es.innovationstrategies.pruebatecnica.testhazelcast.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.innovationstrategies.pruebatecnica.testhazelcast.model.User;

@Repository
public class UserDAO implements IUserDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);	
	}

	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		
		return user;
	}

	public void deleteUser(User user) {
		User gotUser = (User) sessionFactory.getCurrentSession().load(User.class, user.getEmail());
        if (gotUser != null) {
            this.sessionFactory.getCurrentSession().delete(gotUser);
        }
	}
	
	public void deleteUser(String userEmail) {
		User gotUser = this.getUser(userEmail);
        if (gotUser != null) {
            this.sessionFactory.getCurrentSession().delete(gotUser);
        }
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public User getUser(String userEmail) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userEmail);
	}
	
	public List<User> searchUsers(String filtroEmail) {
        List<User> userList = null;
        
        userList = sessionFactory
                .getCurrentSession()
                .createQuery("from User where email like :email")
                .setParameter("email", "%" + filtroEmail + "%")
                .list();
        
        return userList;
    }
	
	public Map<String, User> getUserMap(Collection<String> idCol) {
        Map<String, User> userMap = new HashMap<String, User>();
        for (String id : idCol) {
            User user = (User) sessionFactory
                    .getCurrentSession()
                    .createQuery("from User where email=?")
                    .setParameter(0, id)
                    .uniqueResult();
            userMap.put(user.getEmail(), user);
        }
        
        return userMap;
    }


}
