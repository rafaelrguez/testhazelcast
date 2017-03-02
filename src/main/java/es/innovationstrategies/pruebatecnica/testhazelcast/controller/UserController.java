package es.innovationstrategies.pruebatecnica.testhazelcast.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.innovationstrategies.pruebatecnica.testhazelcast.model.User;
import es.innovationstrategies.pruebatecnica.testhazelcast.service.IUserService;
import es.innovationstrategies.pruebatecnica.testhazelcast.viewbean.UserBean;

@Controller
public class UserController {
	final static Logger logger = Logger.getLogger(UserController.class);
 
    public UserController() {
        System.out.println("UserController()");
    }
 
    @Autowired
    private IUserService userService;
  
    @RequestMapping(value = "/")
    public ModelAndView listUsers(ModelAndView model) throws IOException {
        //List<User> usersList = userService.getAllUsers();
        //model.addObject("userList", usersList);
        model.addObject("userBean", new UserBean());
        model.setViewName("registration");
        
        return model;
    }
   
    @RequestMapping(value = "/queryUser")
    public ModelAndView queryUsers(@ModelAttribute UserBean userBean) {
    	List<User> usersList = this.userService.searchUsers(userBean.getEmail());
    	
    	if(usersList != null && !usersList.isEmpty()) {
    		ModelAndView model = new ModelAndView(); 
    		model.addObject("userList", usersList);    		 
            model.setViewName("userList");
            
            return model;
    	}
    	else {
    		return new ModelAndView("redirect:/");
    	}
    }
 
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute UserBean userBean) {
    	User userAux = this.userService.getUser(userBean.getEmail());
    	
    	User user = new User();
        user.setEmail(userBean.getEmail());
        user.setName(userBean.getName());
        user.setPhone(userBean.getPhone());
        user.setSurname(userBean.getSurname());
        
        if (userAux == null) {             
            userService.addUser(user);
            
            return new ModelAndView("redirect:/congratulations");
        } else {
            userService.updateUser(user);
            
            return new ModelAndView("redirect:/");
        }
        
        
    }
    
    @RequestMapping(value = "/congratulations")
    public ModelAndView congratulations(ModelAndView model) {
        model.setViewName("congratulations");
        
        return model;
    }
    
    /*@RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model) {
        User user = new User();
        model.addObject("user", user);
        model.setViewName("UserForm");
        
        return model;
    }
 
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(HttpServletRequest request) {
        String userEmail = request.getParameter("userEmail");
        userService.deleteUser(userEmail);
        
        return new ModelAndView("redirect:/");
    }
 
    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        String userEmail = request.getParameter("userEmail");
        User user = userService.getUser(userEmail);
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", user);
 
        return model;
    }*/
}
