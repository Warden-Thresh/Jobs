package com.warden.model;

import javax.persistence.*;

/**
 * Created by Warden on 2017/4/4.
 */
@Entity
@Table(name = "job", schema = "jobs", catalog = "")
public class JobEntity {
    private int jobId;
    private String jobName;
    private String jobDetial;
    private Integer userId;

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_name", nullable = true, length = 255)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_detial", nullable = true, length = 255)
    public String getJobDetial() {
        return jobDetial;
    }

    public void setJobDetial(String jobDetial) {
        this.jobDetial = jobDetial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobEntity jobEntity = (JobEntity) o;

        if (jobId != jobEntity.jobId) return false;
        if (jobName != null ? !jobName.equals(jobEntity.jobName) : jobEntity.jobName != null) return false;
        if (jobDetial != null ? !jobDetial.equals(jobEntity.jobDetial) : jobEntity.jobDetial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId;
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobDetial != null ? jobDetial.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
