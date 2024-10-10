package com.jobs.firstjobapp.review.impl;

import com.jobs.firstjobapp.company.Company;
import com.jobs.firstjobapp.company.CompanyService;
import com.jobs.firstjobapp.review.Review;
import com.jobs.firstjobapp.review.ReviewRepository;
import com.jobs.firstjobapp.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewImpl implements ReviewService {

   ReviewRepository reviewRepository;
   CompanyService companyService;

    public ReviewImpl(ReviewRepository reviewRepository,CompanyService companyService) {

        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId,Review review) {

        Company company= companyService.getCompanyByCompanyId(companyId);

            if(company!=null){
                review.setCompany(company);
                System.out.println(review.getDescription());
                reviewRepository.save(review);
                return true;
            }else return false;

    }

    @Override
    public Review getReviewById(Long companyId,Long reviewId) {
       List<Review> reviewList= reviewRepository.findByCompanyId(companyId);


       return  reviewList
               .stream()
               .filter(review -> review.getId().equals(reviewId))
               .findFirst()
               .orElse(null);

    }

    @Override
    public boolean deleteReview(Long companyId,Long reviewId) {

        if(companyService.getCompanyByCompanyId(companyId)!=null
                && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyById(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }else{
            return false;
        }
    }
}
