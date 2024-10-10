package com.jobs.firstjobapp.review;

import java.util.List;

public interface ReviewService {
     List<Review> getAllReview(Long CompanyId);

     boolean addReview(Long companyId, Review review);

     Review getReviewById(Long companyId,Long reviewId);
     boolean deleteReview(Long companyId,Long reviewId);

}
