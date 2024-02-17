package com.ait.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entity.ReviewEntity;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Integer>{

}
