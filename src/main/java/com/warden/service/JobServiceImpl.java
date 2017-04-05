package com.warden.service;

import com.warden.model.JobEntity;
import com.warden.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Warden on 2017/4/4.
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

    @Override
    public boolean addJob(JobEntity job) {
        return false;
    }

    @Override
    public JobEntity updateJob(JobEntity job) {
        jobRepository.saveAndFlush(job);
        return null;
    }

    @Override
    public JobEntity getJob(int id) {
        JobEntity jobEntity = jobRepository.findOne(id);
        return jobEntity;
    }

    @Override
    public JobEntity deleteJob(JobEntity job) {
        return null;
    }

    @Override
    public List <JobEntity> getJobs() {
        List<JobEntity> jobList = jobRepository.findAll();
        return jobList;
    }

    @Override
    public void flush() {

    }
}
