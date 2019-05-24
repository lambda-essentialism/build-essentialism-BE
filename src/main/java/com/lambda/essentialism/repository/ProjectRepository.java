package com.lambda.essentialism.repository;

import com.lambda.essentialism.model.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Modifying
    @Query(value = "SELECT projectid, title FROM projects WHERE userid=:userid", nativeQuery = true)
    @Transactional
    List<Project> findUserProjects(long userid);
}