package com.ait.service;

import java.util.List;

import com.ait.entity.ReviewEntity;

public interface ReviewService {
	
	public void createReview(Integer id,ReviewEntity entity);
	
	public List<ReviewEntity> getReviews(Integer companyId);
	
    public ReviewEntity getReview(Integer companyId,Integer reviewId);
    
    public boolean updateReview(Integer companyId,Integer reviewId,ReviewEntity review);
    
    public boolean deleteReview(Integer companyId,Integer reviewId);
}
