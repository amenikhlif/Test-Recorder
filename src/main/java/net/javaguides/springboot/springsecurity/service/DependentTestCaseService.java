package net.javaguides.springboot.springsecurity.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.DependentTestCase;
import net.javaguides.springboot.springsecurity.model.TestCase;
import net.javaguides.springboot.springsecurity.model.TestSuite;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.DependentTestCaseRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;

@Service
public class DependentTestCaseService {
	 @Autowired
	 DependentTestCaseRepository dependentTestCaseRepository;
	 UserRepository  userRepository;
	 //private final TestSuiteRepository testSuiteRepository;
	 public DependentTestCaseService(DependentTestCaseRepository dependentTestCaseRepository,UserRepository  userRepository) {
	        this.dependentTestCaseRepository = dependentTestCaseRepository;
	        this.userRepository=userRepository;
	    }
	 
	 
	
	    public List<DependentTestCase> getDependentTestCaseAllLists() {
	        return dependentTestCaseRepository.findAll();
	    }

	    public void addDependentTestCase(DependentTestCase TestCase) {
	    	dependentTestCaseRepository.save(TestCase);
	    }

	    public DependentTestCase getDependentTestCaseById(Long idTestCase) {
	        return dependentTestCaseRepository.findById(idTestCase).get();
	    }

	    public void deleteDependentTestCase(Long idTestCase) {
	    	dependentTestCaseRepository.deleteById(idTestCase);
	    	
	    }
	    
	    
	    public DependentTestCase save(DependentTestCase dependentTestCase){
	    	
	    	dependentTestCase.setTestCaseName(dependentTestCase.getTestCaseName());
	    	
	    	dependentTestCase.setTestCaseDescription(dependentTestCase.getTestCaseDescription());
	    	dependentTestCase.setNavigator(dependentTestCase.getNavigator());
	    	dependentTestCase.setUrl(dependentTestCase.getUrl());
	    	dependentTestCase.setTestCaseCreatedDate(new Date());
	    	dependentTestCase.setTestCaseUpdatedDate(new Date());
	    	
	       	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	   
	    	  String username = ((UserDetails)principal).getUsername();
	    	  System.out.println("chaymaaaaaaa11"+username);
	  
	       
			
			User user = userRepository.findByEmail(username);
			  System.out.println("chaymaaaaaaa11"+user.getId());
			  dependentTestCase.setTestCaseCreator(user.getFirstName()+"  "+user.getLastName());
	    	
			  dependentTestCase.setUser(user);

	       return dependentTestCaseRepository.save(dependentTestCase);
	    }
	    
	    
	    public DependentTestCase UpdatdependentTestCase(DependentTestCase dependentTestCase){
	    	dependentTestCase.setTestCaseName(dependentTestCase.getTestCaseName());
	    	
	    	dependentTestCase.setTestCaseDescription(dependentTestCase.getTestCaseDescription());
	    	dependentTestCase.setNavigator(dependentTestCase.getNavigator());
	    	dependentTestCase.setUrl(dependentTestCase.getUrl());
	    	dependentTestCase.setTestCaseCreatedDate(dependentTestCase.getTestCaseCreatedDate());
	    	dependentTestCase.setTestCaseUpdatedDate(new Date());

	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 	   
	    	  String username = ((UserDetails)principal).getUsername();
	    	  System.out.println("chaymaaaaaaa11"+username);
	  
	       
			
			User user = userRepository.findByEmail(username);
			  System.out.println("chaymaaaaaaa11"+user.getId());

			  dependentTestCase.setUser(user);

	       return dependentTestCaseRepository.save(dependentTestCase);
	    } 
	    
	 

}
