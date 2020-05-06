package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springboot.springsecurity.model.DependentTestCase;
import net.javaguides.springboot.springsecurity.model.TestSuite;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.service.DependentTestCaseService;

@Controller
public class DependentTestCaseController {

	
	 @Autowired  
	private final DependentTestCaseService dependentTestCaseService;
	 
	  
	  public DependentTestCaseController(DependentTestCaseService dependentTestCaseService,UserRepository userRepository ) {
	        this.dependentTestCaseService = dependentTestCaseService;
	        
	    }
	  
	  @RequestMapping("/dependentTestCase_lists")
	    public String viewHomePage(Model model) {
	       model.addAttribute("dependentTestCase_lists", dependentTestCaseService.getDependentTestCaseAllLists());
	   
		  return "dependentTestCase";
	    }
	  
	  
	  @RequestMapping("/insert_dependentTestCase")
	    public String insertDependentTestCase(Model model) {
	        model.addAttribute("dependentTestCase", new DependentTestCase());
	        return "add_dependentTestCase";
	    }

@RequestMapping(value = "/saveDTC", method = RequestMethod.POST)
	    public String saveDependentTestCase(@ModelAttribute("dependentTestCase") DependentTestCase dependentTestCase) {
		 
	

		  if (!dependentTestCase.getTestCaseName().trim().isEmpty() && !dependentTestCase.getTestCaseDescription().trim().isEmpty() && !dependentTestCase.getNavigator().trim().isEmpty() && !dependentTestCase.getUrl().trim().isEmpty() ) {
				 	
			  dependentTestCaseService.save(dependentTestCase);
			 long id= dependentTestCase.getIdTestCase();
			  System.out.println("iddddddd"+id);
	            return "redirect:/AA?idTestCase="+dependentTestCase.getIdTestCase();
	            
	        } else {
	            return "redirect:/insert_dependentTestCase";
	        }
		  
	    } 



@RequestMapping(value = "/dependentTestCase_lists/update", method = RequestMethod.POST)
public String updatedependentTestCase(@ModelAttribute("dependentTestCase") DependentTestCase dependentTestCase) {
	 
	  if (!dependentTestCase.getTestCaseName().trim().isEmpty() && !dependentTestCase.getTestCaseDescription().trim().isEmpty() && !dependentTestCase.getNavigator().trim().isEmpty() && !dependentTestCase.getUrl().trim().isEmpty() ) {
		//testSuiteService.addTestSuite(testSuite);
		  DependentTestCase t= dependentTestCaseService.getDependentTestCaseById(dependentTestCase.getIdTestCase());
		User u = t.getUser();
		t.setUser(u);
		
		dependentTestCaseService.UpdatdependentTestCase(dependentTestCase);
        return "redirect:/dependentTestCase_lists";
    } else {
        return "redirect:/edit_dependentTestCase/" + dependentTestCase.getIdTestCase();
    	
    }
	
} 




@RequestMapping("/edit_dependentTestCase/{idTestCase}")
public ModelAndView editTestForm(@PathVariable(name = "idTestCase") Long idTestCase) {
    ModelAndView modelAndView = new ModelAndView("edit_dependentTestCase");
    modelAndView.addObject("dependentTestCase", dependentTestCaseService.getDependentTestCaseById(idTestCase));
    return modelAndView;
}



@RequestMapping("deleteDTC/{idTestCase}")
public String deleteUser(@PathVariable(name = "idTestCase") Long idTestCase) {
	dependentTestCaseService.deleteDependentTestCase(idTestCase);
    return "redirect:/dependentTestCase_lists";
}



	  
}
