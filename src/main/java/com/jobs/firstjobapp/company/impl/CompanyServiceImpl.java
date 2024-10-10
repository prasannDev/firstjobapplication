package com.jobs.firstjobapp.company.impl;

import com.jobs.firstjobapp.company.Company;
import com.jobs.firstjobapp.company.CompanyRepository;
import com.jobs.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> list=companyRepository.findAll();
        System.out.println(""+list.size());
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyByCompanyId(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean updateCompanyById(Long id, Company updatedCompany) {
        Optional<Company> optionalCompany=companyRepository.findById(id);

        if(optionalCompany.isPresent()){
            Company companyUpdate=optionalCompany.get();
            companyUpdate.setName(updatedCompany.getName());
            companyUpdate.setDescription(updatedCompany.getDescription());
            companyUpdate.setJobs(updatedCompany.getJobs());
            return true;
        }
        return false;
    }
}
