package com.jobs.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return  ResponseEntity.ok(companyService.getAllCompanies());
    }


    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("company create successfully", HttpStatus.OK);
    }


    @GetMapping("/getCompany/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
       Company company= companyService.getCompanyByCompanyId(id);
       if(company!=null)
        return new ResponseEntity<>(company, HttpStatus.OK);
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
      boolean isDelete=  companyService.deleteCompanyById(id);
      if(isDelete) {
          return ResponseEntity.ok("company deleted successfully");
      }else {
          return new ResponseEntity<>("company not found",HttpStatus.NOT_FOUND);
      }
    }

}
