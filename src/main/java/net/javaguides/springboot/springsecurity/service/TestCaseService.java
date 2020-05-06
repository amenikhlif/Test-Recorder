package net.javaguides.springboot.springsecurity.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.TestCase;
import net.javaguides.springboot.springsecurity.model.TestSuite;
import net.javaguides.springboot.springsecurity.repository.TestCaseRepository;


@Service
public class TestCaseService {

	
	
	 @Autowired
	 TestCaseRepository testCaseRepository;

	 
	 public TestCaseService(TestCaseRepository testCaseRepository) {
	        this.testCaseRepository = testCaseRepository;
	      
	    }

	 
	 public List<TestCase>  findTestSuiteTestCase(TestSuite testSuite){
			
			return testCaseRepository.findByTestSuite(testSuite);
		}
	 
	    public List<TestCase> getTestCaseAllLists() {
	        return testCaseRepository.findAll();
	    }

	    public void addTestCase(TestCase TestCase) {
	    	testCaseRepository.save(TestCase);
	    }

	    public TestCase getTestCaseById(Long idTestCase) {
	        return testCaseRepository.findById(idTestCase).get();
	    }

	    public void deleteTestCase(Long idTestCase) {
	    	testCaseRepository.deleteById(idTestCase);
	    	
	    }
	   
	    public TestCase save(TestCase testCase,TestSuite testSuite){
	    	
	    	testCase.setTestCaseName(testCase.getTestCaseName());
	    	
	    	testCase.setTestCaseDescription(testCase.getTestCaseDescription());
	    	testCase.setNavigator(testCase.getNavigator());
	    	testCase.setUrl(testCase.getUrl());
	    	testCase.setTestCaseCreatedDate(new Date());
	    	testCase.setTestCaseUpdatedDate(new Date());
	    	testCase.setTestSuite(testSuite);
	       return testCaseRepository.save(testCase);
	    } 

	    
	    
	    
	    
	    
	    public TestCase UpdatTestCase(TestCase testCase){
	    	testCase.setTestCaseName(testCase.getTestCaseName());
	    	
	    	testCase.setTestCaseDescription(testCase.getTestCaseDescription());
	    	testCase.setNavigator(testCase.getNavigator());
	    	testCase.setUrl(testCase.getUrl());
	    	testCase.setTestCaseCreatedDate(testCase.getTestCaseCreatedDate());

	    	
	    	testCase.setTestCaseUpdatedDate(new Date());

	  /* 	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   
	    	  String username = ((UserDetails)principal).getUsername();
	    	  System.out.println("chaymaaaaaaa11"+username);
	  
	       
			
			User user = userRepository.findByEmail(username);
			  System.out.println("chaymaaaaaaa11"+user.getId());
			 // testsuite.setTestSuiteCreator(user.getFirstName()+"  "+user.getLastName());
	    	//Long iduser= user.getId();
	    	testsuite.setUser(user);
	  // testsuite.setUser(user);
	    	//user.getTestsuites().add(testsuite);
*/
	       return testCaseRepository.save(testCase);
	    } 
	
	
}
