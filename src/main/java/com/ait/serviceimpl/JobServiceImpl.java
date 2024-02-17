package com.ait.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.entity.CompanyEntity;
import com.ait.entity.JobEntity;
import com.ait.entity.ReviewEntity;
import com.ait.model.JobDto;
import com.ait.repo.CompanyRepo;
import com.ait.repo.JobRepo;
import com.ait.repo.ReviewRepo;
import com.ait.service.JobService;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobRepo jobrepo;
	
	@Autowired
	private CompanyRepo companyrepo;
	
	@Override
	public String createJob(JobEntity entity) {
		Optional<CompanyEntity> findById = companyrepo.findById(entity.getCompany().getId());
		if(findById.isPresent()) {
			CompanyEntity companyEntity = findById.get();
			entity.setCompany(companyEntity);
			jobrepo.save(entity);
			return "job created sucessfully";
		}
		return null;
		
		
	}
	
	@Override
	public boolean deleteJobId(Integer id) {
		Optional<JobEntity> findById = jobrepo.findById(id);
            if(findById.isPresent()) {
            	Integer id2 = findById.get().getId();
            	jobrepo.deleteById(id2);
            	return true;
            }
		
		return false;
	}
	@Override
	public List<JobEntity> getAllJobs() {
		return jobrepo.findAll();
		
	}
	@Override
	public JobEntity getJobById(Integer id) {
		Optional<JobEntity> findById = jobrepo.findById(id);
		if(findById.isPresent()) {
			JobEntity jobEntity = findById.get();
			return jobEntity;
		}
		return null;
	}
	@Override
	public JobEntity UpdateJob(Integer id, JobDto jobdto) {
		Optional<JobEntity> findById = jobrepo.findById(id);
		if(findById.isPresent()) {
			JobEntity jobEntity = findById.get();
			jobEntity.setDescription(jobdto.getDescription());
			jobEntity.setLocation(jobdto.getLocation());
			jobEntity.setMaxSalary(jobdto.getMaxSalary());
			jobEntity.setMinSalary(jobdto.getMinSalary());
			jobEntity.setTitle(jobdto.getTitle());
			JobEntity job = jobrepo.save(jobEntity);
			return job;
		}
		return null;
	}

}
