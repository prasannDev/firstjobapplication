package com.jobs.firstjobapp.company;


import com.jobs.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {


    List<Company> getAllCompanies();

    void  createCompany(Company company);

    Company getCompanyByCompanyId(Long id);

    boolean deleteCompanyById(Long id);

    boolean updateCompanyById(Long id, Company updatedCompany);
}
