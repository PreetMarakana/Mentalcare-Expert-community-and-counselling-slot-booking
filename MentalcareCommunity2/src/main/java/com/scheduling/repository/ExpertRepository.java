package com.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Expert;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {

}
