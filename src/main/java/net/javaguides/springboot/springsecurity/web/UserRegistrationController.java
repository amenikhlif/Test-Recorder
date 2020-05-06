package net.javaguides.springboot.springsecurity.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.service.UserService;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Controller
@RequestMapping("/registration")
@ComponentScan(basePackages= {"net.javaguides.springboot.springsecurity.web"})
public class UserRegistrationController {

    @Autowired
    private UserService userService;
  
  	 @Autowired
     private BCryptPasswordEncoder passwordEncoder;
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }
  

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }
    
    
    
    
    //////
    
    @RequestMapping("/user_lists")
    public String viewHomePage(Model model,@RequestParam(defaultValue="") String firstName) {
     //  model.addAttribute("user_lists", userService.getUserAllLists());
      model.addAttribute("user_lists", userService.findByfirstName(firstName));
    //	model.addAttribute("user_lists", userService.findByEmail(email));
     
        return "gestionuser";
    }

    @RequestMapping("/insert_user")
    public String insertUser(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    } 
    
    @PostMapping("/user_lists/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UserRegistrationDto user, 
      BindingResult result, Model model) {
    	User u= userService.getUserById(id);
    	System.out.println(user.getEmail());
    	   User userr = new User();
    	   userr.setId(id);
           userr.setFirstName(user.getFirstName());
           userr.setLastName(user.getLastName());
           userr.setEmail(user.getEmail());
          
           
           userr.setPassword(passwordEncoder.encode(user.getPassword()));
         
          // userr.setRoles(user.getRoles());
         
        
        
     
         Role r = new Role(user.getRole());
         List<Role> listRole = new ArrayList<Role>();
         listRole.add(r);
         //On met la liste dans le désordre
         Collection<Role> roles= listRole;
         userr.setRoles(roles);
         System.out.println(roles);
           userService.addUser(userr);
           // userRepository.save(userr);
      //  userService.save(user);
       /* if (result.hasErrors()) {
            user.setId(id);
            return"redirect:/edit_user/" + user;
        }
             
        userService.addUser(user);
        model.addAttribute("users", userRepository.findAll());*/
        return "redirect:/registration/user_lists";
    }	

    @RequestMapping("/edit_user/{id}")
    public ModelAndView editUserForm(@PathVariable(name = "id") Long userId) {
        ModelAndView modelAndView = new ModelAndView("edit_user");
        
       modelAndView.addObject("user", userService.getUserById(userId));
     
        return modelAndView;
    }

    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long userId) {
    	userService.deleteUser(userId);
        return "redirect:/registration/user_lists";
    }
    

}
