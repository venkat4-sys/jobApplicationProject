package com.ait.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.entity.CompanyEntity;
import com.ait.entity.ReviewEntity;
import com.ait.repo.CompanyRepo;
import com.ait.repo.ReviewRepo;
import com.ait.service.CompanyService;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepo companyrepo;
	@Autowired
	private ReviewRepo reviewrepo;

	@Override
	public String createCompany(CompanyEntity entity) {
		companyrepo.save(entity);
		return "Company Created Sucessfully";
	}

	@Override
	public boolean deleteCompanyId(Integer id) {

		Optional<CompanyEntity> findById = companyrepo.findById(id);
		if (findById.isPresent()) {
			Integer id2 = findById.get().getId();
			companyrepo.deleteById(id2);
			return true;
		}

		return false;
	}

	@Override
	public List<CompanyEntity> getAllCompanies() {
		return companyrepo.findAll();

	}

	@Override
	public CompanyEntity getCompanyById(Integer id) {
		 Optional<CompanyEntity> findById = companyrepo.findById(id);
		    if (findById.isPresent()) {
		        CompanyEntity companyentity = findById.get();
		        List<ReviewEntity> reviews = companyentity.getReview();
		        return companyentity;
		    }
		    return null;
	}

	@Override
	public CompanyEntity UpdateCompany(Integer id, CompanyEntity entity) {
		Optional<CompanyEntity> findById = companyrepo.findById(id);
		if (findById.isPresent()) {
			CompanyEntity companyEntity = findById.get();
			companyEntity.setDescription(entity.getDescription());
			companyEntity.setName(entity.getName());
			companyEntity.setJob(entity.getJob());
			CompanyEntity company = companyrepo.save(companyEntity);
			return company;
		}

		return null;
	}

}
