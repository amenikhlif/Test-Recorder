package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.DependentTestCase;

public interface DependentTestCaseRepository extends JpaRepository<DependentTestCase, Long> {

}
