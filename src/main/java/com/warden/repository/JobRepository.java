package com.warden.repository;

import com.warden.model.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Warden on 2017/3/25.
 */
@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {
    Page<JobEntity> findAll(Pageable pageable);

}