package net.sparkminds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	@Query(value = "SELECT distinct a.id, a.email, a.github, a.deleted, a.name FROM application a JOIN past_project b ON a.id = b.application_id WHERE a.id = b.application_id and a.deleted = false", nativeQuery = true)
	List<Application> getAllApplication();
	
	List<Application> findByEmailAndDeletedFalse(String email);
	
	Optional<Application> findByIdAndDeletedFalse(Long id);
}
