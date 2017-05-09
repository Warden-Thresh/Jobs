package com.warden.repository;

import com.warden.model.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Warden on 2017/5/7.
 */
public interface JobSearch extends PagingAndSortingRepository <JobEntity, Integer>{
    Page<JobEntity> findAll(Pageable pageable);
}
