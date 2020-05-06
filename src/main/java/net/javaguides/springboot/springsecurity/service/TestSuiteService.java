package net.javaguides.springboot.springsecurity.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.TestSuite;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.TestSuiteRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;
@Service
public class TestSuiteService {
	 @Autowired
	 TestSuiteRepository testSuiteRepository;
	 UserRepository  userRepository;
	 //private final TestSuiteRepository testSuiteRepository;
	 public TestSuiteService(TestSuiteRepository testSuiteRepository,UserRepository  userRepository) {
	        this.testSuiteRepository = testSuiteRepository;
	        this.userRepository=userRepository;
	    }

	    public List<TestSuite> getTestSuiteAllLists() {
	        return testSuiteRepository.findAll();
	    }

	    public void addTestSuite(TestSuite TestSuite) {
	    	testSuiteRepository.save(TestSuite);
	    }

	    public TestSuite getTestSuiteById(Long idTestSuite) {
	        return testSuiteRepository.findById(idTestSuite).get();
	    }

	    public void deleteTestSuite(Long idTestSuite) {
	    	testSuiteRepository.deleteById(idTestSuite);
	    	
	    }
	   
	    public TestSuite save(TestSuite testsuite){
	    	//TestSuite testsuite = new TestSuite();
	    	testsuite.setTestSuiteName(testsuite.getTestSuiteName());
	    	testsuite.setTestSuiteDescription(testsuite.getTestSuiteDescription());
	    	//testsuite.setTestSuiteCreator(testsuite.getTestSuiteCreator());
	    	testsuite.setTestSuiteCreatedDate(new Date());
	    	testsuite.setTestSuiteUpdatedDate(new Date());

	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   
	    	  String username = ((UserDetails)principal).getUsername();
	    	  System.out.println("chaymaaaaaaa11"+username);
	  
	       
			
			User user = userRepository.findByEmail(username);
			  System.out.println("chaymaaaaaaa11"+user.getId());
			  testsuite.setTestSuiteCreator(user.getFirstName()+"  "+user.getLastName());
	    	//Long iduser= user.getId();
	    	testsuite.setUser(user);
	  // testsuite.setUser(user);
	    	//user.getTestsuites().add(testsuite);

	       return testSuiteRepository.save(testsuite);
	    } 

	    
	    
	    
	    
	    
	    public TestSuite UpdatTestSuite(TestSuite testsuite){
	    	//TestSuite testsuite = new TestSuite();
	    	testsuite.setTestSuiteName(testsuite.getTestSuiteName());
	    	testsuite.setTestSuiteDescription(testsuite.getTestSuiteDescription());
	    	//testsuite.setTestSuiteCreator(testsuite.getTestSuiteCreator());
	    	testsuite.setTestSuiteCreatedDate(testsuite.getTestSuiteCreatedDate());
	    	
	    	testsuite.setTestSuiteUpdatedDate(new Date());

	   	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   
	    	  String username = ((UserDetails)principal).getUsername();
	    	  System.out.println("chaymaaaaaaa11"+username);
	  
	       
			
			User user = userRepository.findByEmail(username);
			  System.out.println("chaymaaaaaaa11"+user.getId());
			 // testsuite.setTestSuiteCreator(user.getFirstName()+"  "+user.getLastName());
	    	//Long iduser= user.getId();
	    	testsuite.setUser(user);
	  // testsuite.setUser(user);
	    	//user.getTestsuites().add(testsuite);

	       return testSuiteRepository.save(testsuite);
	    } 
	    
}
