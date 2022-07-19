package net.sparkminds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.sparkminds.entity.PastProject;

@Repository
public interface PastProjectRepository extends JpaRepository<PastProject, Long> {

    List<PastProject> findAllByApplicationId(Long applicationId);
}
