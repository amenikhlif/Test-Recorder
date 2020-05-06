package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.DependentTestCase;
import net.javaguides.springboot.springsecurity.model.Scenario;
import net.javaguides.springboot.springsecurity.model.TestCase;
import net.javaguides.springboot.springsecurity.model.TestSuite;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

	List<Scenario> findByDependentTestCase(DependentTestCase dependentTestCase);

}
