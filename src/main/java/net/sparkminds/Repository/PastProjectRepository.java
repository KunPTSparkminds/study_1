package net.sparkminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.PastProject;

public interface PastProjectRepository extends JpaRepository<PastProject, Long> {

}
