package com.pbl4.server.repository;

import com.pbl4.server.entity.DeviceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<DeviceEntity, Integer> {
}
