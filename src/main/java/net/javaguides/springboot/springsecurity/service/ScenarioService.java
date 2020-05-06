package net.javaguides.springboot.springsecurity.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.DependentTestCase;
import net.javaguides.springboot.springsecurity.model.Scenario;

import net.javaguides.springboot.springsecurity.repository.ScenarioRepository;;


@Service
public class ScenarioService {

	 @Autowired
	 ScenarioRepository scenarioRepository;
	
	 //private final TestSuiteRepository testSuiteRepository;
	 public ScenarioService(ScenarioRepository scenarioRepository) {
	        this.scenarioRepository = scenarioRepository;
	      
	    }

	    public List<Scenario> getScenarioAllLists() {
	        return scenarioRepository.findAll();
	    }

		 public List<Scenario>  findTestCaseScenario(DependentTestCase dependentTestCase){
				
				return scenarioRepository.findByDependentTestCase(dependentTestCase);
			}
	    
	    public void addScenario(Scenario Scenario) {
	    	scenarioRepository.save(Scenario);
	    }

	    public Scenario getScenarioById(Long idScenario) {
	        return scenarioRepository.findById(idScenario).get();
	    }

	    public void deleteScenario(Long idScenario) {
	    	scenarioRepository.deleteById(idScenario);
	    	
	    }
	   
	   public Scenario save(Scenario scenario,DependentTestCase dependentTestCase){
		   System.out.println(scenario.toString());
		   scenario.setCommande(scenario.getCommande());
		   scenario.setPath(scenario.getPath());
		   scenario.setValue(scenario.getValue());

	    	scenario.setDependentTestCase(dependentTestCase);
	       return scenarioRepository.save(scenario);
	    } 

	    
	    
	    
	    
	    
/*	    public Scenario UpdatTestSuite(Scenario testsuite){
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
	
	*/
}
