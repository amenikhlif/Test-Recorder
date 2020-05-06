package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.TestSuite;



public interface TestSuiteRepository extends JpaRepository<TestSuite, Long> {

	

	
	

//	TestSuite upd(TestSuite testsuite);

}
