package net.sparkminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Reviewer;

public interface LoginRepository extends JpaRepository<Reviewer, Long> {
    Reviewer findByEmail(String email);
    
    Reviewer findOneByEmailIgnoreCaseAndPassword(String email, String password);
}
 