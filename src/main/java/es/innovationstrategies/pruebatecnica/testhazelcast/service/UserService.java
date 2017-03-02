package es.innovationstrategies.pruebatecnica.testhazelcast.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import es.innovationstrategies.pruebatecnica.testhazelcast.dao.IUserDAO;
import es.innovationstrategies.pruebatecnica.testhazelcast.model.User;

@Service
@Transactional
@Configuration
@EnableCaching
public class UserService implements IUserService {
	
	@Bean
    public CacheManager cacheManager() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager = cachingProvider.getCacheManager();
        return new JCacheCacheManager(cacheManager);
    }
	
    @Bean
    public KeyGenerator keyGenerator() {
        return null;
    }
    
    @Bean
    public HazelcastInstance instance() {
        Map<String, CacheSimpleConfig> cacheConfigs = new HashMap<String, CacheSimpleConfig>();
        cacheConfigs.put("users", new CacheSimpleConfig().setName("users"));
        Config config = new Config().setCacheConfigs(cacheConfigs);
        return Hazelcast.newHazelcastInstance(config);
    }

	@Autowired
    private IUserDAO userDAO;
	
	@CacheEvict(value="users")
	public void addUser(User user) {
		this.userDAO.addUser(user);
	}

	@CacheEvict(value="users")
	public User updateUser(User user) {
		return this.userDAO.updateUser(user);
	}
	
	@CacheEvict(value="users")
	public void deleteUser(String userEmail) {
		this.userDAO.deleteUser(userEmail);
	}

	@CacheEvict(value="users")
	public void deleteUser(User user) {
		this.userDAO.deleteUser(user);
	}

	@Cacheable(value="users", key="#email")
	public List<User> getAllUsers() {
		return this.userDAO.getAllUsers();
	}

	@Cacheable(value="user", key="#email")
	public User getUser(String userEmail) {
		return this.userDAO.getUser(userEmail);
	}
	
	@Cacheable(value="users", key="#email")
	public List<User> searchUsers(String filtroEmail) {
		return this.userDAO.searchUsers(filtroEmail);
	}

}
