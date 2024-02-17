package com.ait.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.entity.CompanyEntity;
import com.ait.entity.ReviewEntity;
import com.ait.repo.CompanyRepo;
import com.ait.repo.ReviewRepo;
import com.ait.service.CompanyService;
import com.ait.service.ReviewService;

@Service
public class ReviewServiceimpl implements ReviewService {
	
	@Autowired
	private ReviewRepo reviewrepo;
	
	@Autowired
	private CompanyRepo companyrepo;
	
	@Autowired
	private CompanyService companyService;
	
	@Override
	public void createReview(Integer id, ReviewEntity review) {
		Optional<CompanyEntity> findById = companyrepo.findById(id);
		if(findById.isPresent()) {
			CompanyEntity companyEntity = findById.get();
			review.setCompany(companyEntity);
			reviewrepo.save(review);
		}	
	}
	@Override
	public List<ReviewEntity> getReviews(Integer companyId) {
		Optional<CompanyEntity> findById = companyrepo.findById(companyId);
		if(findById.isPresent()) {
			CompanyEntity companyEntity = findById.get();
			List<ReviewEntity> review = companyEntity.getReview();
			return review;
		}
		return null;
	}
	@Override
	public ReviewEntity getReview(Integer companyId, Integer reviewId) {
		CompanyEntity companyById = companyService.getCompanyById(companyId);
		List<ReviewEntity> review = companyById.getReview();
		Optional<ReviewEntity> reviewbyId = review.stream().filter(reviews->reviews.getId().equals(reviewId)).findFirst();
		if(reviewbyId.isPresent()) {
			ReviewEntity reviewEntity = reviewbyId.get();
			return reviewEntity;
		}
		
		return null;
	}
	@Override
	public boolean updateReview(Integer companyId, Integer reviewId, ReviewEntity review) {
		CompanyEntity company = companyService.getCompanyById(companyId);
		if(company!=null) {
			Optional<ReviewEntity> findById = reviewrepo.findById(reviewId);
			
			if(findById.isPresent()) {
				ReviewEntity reviewEntity = findById.get();
				reviewEntity.setTitle(review.getTitle());
				reviewEntity.setDescription(review.getDescription());
				reviewEntity.setRating(review.getRating());
				reviewEntity.setCompany(company);
				reviewrepo.save(reviewEntity);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean deleteReview(Integer companyId, Integer reviewId) {
		
		CompanyEntity companyById = companyService.getCompanyById(companyId);
		if(companyById!=null && reviewId!=null) {
			reviewrepo.deleteById(reviewId);
			return true;
		}
		
		
		return false;
	}
}
