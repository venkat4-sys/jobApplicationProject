package com.ait.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.entity.ReviewEntity;
import com.ait.service.ReviewService;

import lombok.Delegate;

@RestController
@RequestMapping("/companies/{id}")
public class ReviewController {
	
	@Autowired	
	private ReviewService reviewService;
	
	@PostMapping("/review")
	public ResponseEntity<?> addReview(@PathVariable Integer id,@RequestBody ReviewEntity review){
		reviewService.createReview(id, review);
		return new ResponseEntity<>("review Added successfully",HttpStatus.OK);
	}
	@GetMapping("/get")
	public ResponseEntity<?> getAllReviews(@PathVariable Integer id){
		List<ReviewEntity> reviews = reviewService.getReviews(id);
		return new ResponseEntity<>(reviews,HttpStatus.OK);
	}
	@GetMapping("/get/{reviewId}")
	public ResponseEntity<?> getAllReviews(@PathVariable Integer reviewId,@PathVariable Integer id){
		ReviewEntity review = reviewService.getReview(id, reviewId);
		if(review!=null) {
			return new ResponseEntity<>(review,HttpStatus.OK);
		}
		return new ResponseEntity<>("review not found",HttpStatus.NOT_FOUND);
	}
	@PutMapping("/get/{reviewId}")
	public ResponseEntity<?> updateReview(@PathVariable Integer reviewId,@PathVariable Integer id,@RequestBody ReviewEntity entity){
     boolean status = reviewService.updateReview(id, reviewId,entity);
		if(status) {
			return new ResponseEntity<>("review updated sucessfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("review not found",HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/delete/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId,@PathVariable Integer id){
     boolean status = reviewService.deleteReview(id, reviewId);
		if(status) {
			return new ResponseEntity<>("review deleted",HttpStatus.OK);
		}
		return new ResponseEntity<>("review not found",HttpStatus.NOT_FOUND);
	}
}
