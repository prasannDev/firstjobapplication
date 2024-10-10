package com.jobs.firstjobapp.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/job")
public class JobController {
   private JobService jobServices;

    public JobController(JobService jobServices) {
        this.jobServices = jobServices;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobServices.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJobs(@RequestBody Job job){
        jobServices.createJob(job);
        return ResponseEntity.ok("job created successfully");
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobByJobId(@PathVariable Long id){
        Job job=jobServices.getJobByJobId(id);
        if(job!=null)
            return   ResponseEntity.ok(job);
        return  new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-job/{id}")
    public ResponseEntity<String> getDeleteJobById(@PathVariable Long id){
      boolean deleted=  jobServices.deleteJobById(id);
      if(deleted)
        return ResponseEntity.ok("job deleted successfully");
      return new ResponseEntity("job not found",HttpStatus.NOT_FOUND);

    }

    @PutMapping("/update-job/{id}")
   // @RequestMapping(value = "/update-job/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatedJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){

        boolean update=jobServices.updateJobById(id,updatedJob);
        if(update)
            return new ResponseEntity("job updated successfully",HttpStatus.OK);
        return new ResponseEntity("job not updated",HttpStatus.NOT_FOUND);
    }

}
