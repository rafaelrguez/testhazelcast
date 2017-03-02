package es.innovationstrategies.pruebatecnica.testhazelcast.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hazelcast.core.MapLoader;

import es.innovationstrategies.pruebatecnica.testhazelcast.dao.IUserDAO;
import es.innovationstrategies.pruebatecnica.testhazelcast.model.User;


public class UserMapLoader implements MapLoader<String, User> {
	
	@Autowired
    private IUserDAO userDAO;
	
	@Override
    public User load(String userEmail) {
		return userDAO.getUser(userEmail);
    }

    @Override
    public Map<String, User> loadAll(Collection<String> idCol) {
        return userDAO.getUserMap(idCol);
    }

    @Override
    public Set<String> loadAllKeys() {
        return null;
    }
}
