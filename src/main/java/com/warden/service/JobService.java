package com.warden.service;

import com.warden.model.JobEntity;

import java.util.List;
import java.util.jar.JarEntry;

/**
 * Created by Warden on 2017/4/4.
 */
public interface JobService {
    public boolean addJob (JobEntity job);
    public JobEntity updateJob(JobEntity job);
    public JobEntity getJob(int id);
    public JobEntity deleteJob(JobEntity job);
    public List <JobEntity> getJobs();
    public void flush();
}
