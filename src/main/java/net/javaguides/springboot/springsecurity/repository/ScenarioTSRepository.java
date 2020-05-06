package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.TestCase;

import net.javaguides.springboot.springsecurity.model.ScenarioTS;

public interface ScenarioTSRepository extends JpaRepository<ScenarioTS, Long> {
	List<ScenarioTS> findByTestCase(TestCase TestCase);
}
