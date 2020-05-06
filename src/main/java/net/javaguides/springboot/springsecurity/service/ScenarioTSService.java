package net.javaguides.springboot.springsecurity.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.ScenarioTS;
import net.javaguides.springboot.springsecurity.model.TestCase;

import net.javaguides.springboot.springsecurity.repository.ScenarioTSRepository;;


@Service
public class ScenarioTSService {

	 @Autowired
	 ScenarioTSRepository scenarioTSRepository;
	
	 //private final TestSuiteRepository testSuiteRepository;
	 public ScenarioTSService(ScenarioTSRepository scenarioTSRepository) {
	        this.scenarioTSRepository = scenarioTSRepository;
	      
	    }

	    public List<ScenarioTS> getScenarioTSAllLists() {
	        return scenarioTSRepository.findAll();
	    }

		 public List<ScenarioTS>  findTestCaseScenario(TestCase TestCase){
				
				return scenarioTSRepository.findByTestCase(TestCase);
			}
	    
	    public void addScenarioTS(ScenarioTS ScenarioTS) {
	    	scenarioTSRepository.save(ScenarioTS);
	    }

	    public ScenarioTS getScenarioTSById(Long idScenario) {
	        return scenarioTSRepository.findById(idScenario).get();
	    }

	    public void deleteScenarioTS(Long idScenario) {
	    	scenarioTSRepository.deleteById(idScenario);
	    	
	    }
	   
	   public ScenarioTS save(ScenarioTS scenarioTS,TestCase TestCase){
		   System.out.println(scenarioTS.toString());
		   scenarioTS.setCommande(scenarioTS.getCommande());
		   scenarioTS.setPath(scenarioTS.getPath());
		   scenarioTS.setValue(scenarioTS.getValue());
		   scenarioTS.setUrl(scenarioTS.getUrl());
	    	scenarioTS.setTestCase(TestCase);
	       return scenarioTSRepository.save(scenarioTS);
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

