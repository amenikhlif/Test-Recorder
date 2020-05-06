package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.TestCase;
import net.javaguides.springboot.springsecurity.model.TestSuite;

public interface TestCaseRepository  extends JpaRepository<TestCase, Long>{

	List<TestCase> findByTestSuite(TestSuite testSuite); 
}
