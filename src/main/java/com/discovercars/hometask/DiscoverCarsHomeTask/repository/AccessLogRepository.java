package com.discovercars.hometask.DiscoverCarsHomeTask.repository;

import com.discovercars.hometask.DiscoverCarsHomeTask.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
