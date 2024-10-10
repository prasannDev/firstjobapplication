package com.jobs.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review){
        System.out.println("review.getDescription()==="+review.getDescription());
        boolean isReviewAdded=reviewService.addReview(companyId,review);
        if(isReviewAdded) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewByReviewId(@PathVariable Long companyId,
                                                      @PathVariable Long reviewId){
        return  new ResponseEntity<>(reviewService.getReviewById(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReviewByReviewId(@PathVariable Long companyId,
                                                         @PathVariable Long reviewId){
        boolean isReviewDeleted=reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }else  return new ResponseEntity<>("Review not deleted ",HttpStatus.NOT_FOUND);
    }

}
