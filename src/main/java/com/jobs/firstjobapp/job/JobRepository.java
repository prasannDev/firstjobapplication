package com.jobs.firstjobapp.job;

import com.jobs.firstjobapp.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {

}
