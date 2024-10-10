package com.jobs.firstjobapp.job.impl;

import com.jobs.firstjobapp.job.Job;
import com.jobs.firstjobapp.job.JobService;
import com.jobs.firstjobapp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServicesImp implements JobService {

   // private List<Job> jobs=new ArrayList<>();

    JobRepository jobRepository;


    public JobServicesImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();

     //   return jobs;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
        //jobs.add(job);
    }

    @Override
    public Job getJobByJobId(Long id) {
//        for(Job job:jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }

       return jobRepository.findById(id).orElse(null);

    }

    @Override
    public boolean deleteJobById(Long id) {
//        for(Job job:jobs){
//            if(job.getId().equals(id)){
//               jobs.remove(job);
//               return true;
//            }
//        }
        //------------------------------------------
//        Iterator<Job> iterator=jobs.iterator();
//        while (iterator.hasNext()){
//            Job job=iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
    try {
        jobRepository.deleteById(id);
        return true;
    }catch (Exception e) {
        return false;
    }

    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional= jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job=jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }

    return false;
    }
}
