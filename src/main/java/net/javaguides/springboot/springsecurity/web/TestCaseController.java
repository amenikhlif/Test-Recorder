package net.javaguides.springboot.springsecurity.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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


import net.javaguides.springboot.springsecurity.model.TestCase;
import net.javaguides.springboot.springsecurity.model.TestSuite;
import net.javaguides.springboot.springsecurity.service.TestCaseService;
import net.javaguides.springboot.springsecurity.service.TestSuiteService;






@Controller
public class TestCaseController {

	 @Autowired  
	private final TestCaseService testCaseService;
	 private final TestSuiteService testSuiteService; 
	 static long testF;
	  
	  public TestCaseController(TestCaseService testCaseService,TestSuiteService testSuiteService) {
	        this.testCaseService = testCaseService;
	        this.testSuiteService=testSuiteService;
	        
	    }

	  
	  @RequestMapping("/testCase_lists")
	    public String viewHomePage(Model model,@RequestParam(defaultValue="") String TestCaseName,Long idTestSuite,HttpSession session) {
		  session.setAttribute("idTestSuite", idTestSuite);
		TestSuite  testSuite=testSuiteService.getTestSuiteById(idTestSuite);
		  model.addAttribute("testCase_lists", testCaseService.findTestSuiteTestCase(testSuite));
	   testF=idTestSuite;
		  return "testCase";
	    }
	  
	  
	  @GetMapping("/insert_testcase")
		 public String testCaseForm(Long idTestSuite, Model model, HttpSession session) {
			 
			 session.setAttribute("idTestSuite", idTestSuite); 
			 model.addAttribute("testCase", new TestCase());
			 return "add_testCase";
			 
		 }
		 
		 @PostMapping("/insert_testcase")
		 public String insertTestCase(@Valid TestCase testCase,BindingResult bindingResult, HttpSession session) {
			 if(bindingResult.hasErrors()) {
				 return "add_testCase";
			 }
		
			 Long idTestSuite = (Long) session.getAttribute("idTestSuite");
			  testCaseService.save(testCase, testSuiteService.getTestSuiteById(idTestSuite));
			
			  
			  return "redirect:/AddScenarioTS?idTestCase="+testCase.getIdTestCase();
		//	return  "redirect:/testCase_lists/"+"?idTestSuite="+idTestSuite ;
		 } 
	  

	  @RequestMapping("/insert_testcase")
	    public String insertTestCase(Model model) {
	        model.addAttribute("testCase", new TestCase());
	        return "add_testCase";
	    } 

	  @RequestMapping(value = "/saveTC", method = RequestMethod.POST)
	    
	    public String saveTestcase(@ModelAttribute("testCase") TestCase testCase) {

	     TestSuite ts = testSuiteService.getTestSuiteById(testF);
		 if (!testCase.getTestCaseName().trim().isEmpty() && !testCase.getTestCaseDescription().trim().isEmpty() && !testCase.getNavigator().trim().isEmpty() && !testCase.getUrl().trim().isEmpty() ) {
		   
		    testCaseService.save(testCase, ts);
		    long id= testCase.getIdTestCase();
			  System.out.println("iddddddd"+id);
			  return "redirect:/AddScenarioTS?idTestCase="+testCase.getIdTestCase();
			  // return  "redirect:/testCase_lists/"+"?idTestSuite="+ts.getIdTestSuite();
	       } else {
	           return "redirect:/insert_testcase";
	        }
		  //return "redirect:/";  
	    } 
	    
	   
	    
	    

	    @RequestMapping(value = "/testCase_lists/update", method = RequestMethod.POST)
	    public String updateTestCase(@ModelAttribute("testCase") TestCase testCase) {
	    	if (!testCase.getTestCaseName().trim().isEmpty() && !testCase.getTestCaseDescription().trim().isEmpty() && !testCase.getNavigator().trim().isEmpty() && !testCase.getUrl().trim().isEmpty() ) {
	   		      	//testSuiteService.addTestSuite(testSuite);
	    		//TestSuite t= testCaseService.getTestSuiteById(testSuite.getIdTestSuite());
	    		//User u = t.getUser();
	    		//t.setUser(u);
	    	TestCase	tc =  testCaseService.getTestCaseById(testCase.getIdTestCase());
	    	TestSuite ts = testSuiteService.getTestSuiteById(tc.getTestSuite().getIdTestSuite());
	    	testCase.setTestSuite(ts);
	    		testCaseService.UpdatTestCase(testCase);
	            return "redirect:/testCase_lists/"+"?idTestSuite="+ts.getIdTestSuite();
			 } 
	         else {
	            return "redirect:/edit_testCase/" + testCase.getIdTestCase();
	        	
	        }
			
	    } 
	    
	    

	
	    

	    @RequestMapping("/edit_testCase/{idTestCase}")
	    public ModelAndView editTestCaseForm(@PathVariable(name = "idTestCase") Long testcaseId) {
	        ModelAndView modelAndView = new ModelAndView("edit_testCase");
	        modelAndView.addObject("testCase", testCaseService.getTestCaseById(testcaseId));
	        return modelAndView;
	    }


	    
	    @RequestMapping("deleteTC/{idTestCase}")
	    public String deleteTestCase(@PathVariable(name = "idTestCase") Long testId) {
	    	TestCase	tc =  testCaseService.getTestCaseById(testId);
	    	TestSuite ts = testSuiteService.getTestSuiteById(tc.getTestSuite().getIdTestSuite());
	    	testCaseService.deleteTestCase(testId);
	    	  return "redirect:/testCase_lists/"+"?idTestSuite="+ts.getIdTestSuite();
	    }

	    

	  
	  
}
