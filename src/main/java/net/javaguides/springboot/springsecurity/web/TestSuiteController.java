package net.javaguides.springboot.springsecurity.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import net.javaguides.springboot.springsecurity.model.TestSuite;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.service.TestSuiteService;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;



@Controller
public class TestSuiteController {

	 @Autowired  
	private final TestSuiteService testSuiteService;
	 
	  
	  public TestSuiteController(TestSuiteService testSuiteService,UserRepository userRepository ) {
	        this.testSuiteService = testSuiteService;
	        
	    }

	  
	  @RequestMapping("/testSuite_lists")
	    public String viewHomePage(Model model,@RequestParam(defaultValue="") String TestSuiteName) {
	       model.addAttribute("testSuite_lists", testSuiteService.getTestSuiteAllLists());
	   
		  return "testSuite";
	    }
	  
	  


	    @RequestMapping("/insert_testsuite")
	    public String insertTestSuite(Model model) {
	        model.addAttribute("testsuite", new TestSuite());
	        return "add_testSuite";
	    }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveTestsuite(@ModelAttribute("testSuite") TestSuite testSuite) {
		  System.out.println(testSuite.toString());
	        if (!testSuite.getTestSuiteName().trim().isEmpty() && !testSuite.getTestSuiteDescription().trim().isEmpty() ) {
	        	
	        	testSuiteService.save(testSuite);
	            return "redirect:/testSuite_lists";
	        } else {
	            return "redirect:/insert_testsuite";
	        }
		  
	    } 
	    

	    
	    

	    @RequestMapping(value = "/testSuite_lists/update", method = RequestMethod.POST)
	    public String updateTest(@ModelAttribute("testSuite") TestSuite testSuite) {
	    	if (!testSuite.getTestSuiteName().trim().isEmpty() && !testSuite.getTestSuiteDescription().trim().isEmpty() ) {
		        	//testSuiteService.addTestSuite(testSuite);
	    		TestSuite t= testSuiteService.getTestSuiteById(testSuite.getIdTestSuite());
	    		User u = t.getUser();
	    		t.setUser(u);
	    		
	    		testSuiteService.UpdatTestSuite(testSuite);
	            return "redirect:/testSuite_lists";
	        } else {
	            return "redirect:/edit_testSuite/" + testSuite.getIdTestSuite();
	        	
	        }
			
	    } 
	    


	    @RequestMapping("/edit_testSuite/{idTestSuite}")
	    public ModelAndView editTestForm(@PathVariable(name = "idTestSuite") Long testsuiteId) {
	        ModelAndView modelAndView = new ModelAndView("edit_testSuite");
	        modelAndView.addObject("testSuite", testSuiteService.getTestSuiteById(testsuiteId));
	        return modelAndView;
	    }


	    
	    @RequestMapping("delete/{idTestSuite}")
	    public String deleteTest(@PathVariable(name = "idTestSuite") Long testId) {
	    	testSuiteService.deleteTestSuite(testId);
	        return "redirect:/testSuite_lists";
	    }
	    
	  
	  
}
