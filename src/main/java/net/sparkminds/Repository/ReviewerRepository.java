package net.sparkminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Reviewer;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

}
