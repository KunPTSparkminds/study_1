package net.sparkminds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	
	List<Application> findAll();
	
	Optional<Application> findByEmailAndDeletedFalse(String email);
	
	Optional<Application> findByIdAndDeletedFalse(Long id);
	
	boolean existsByEmail(String email);
}
