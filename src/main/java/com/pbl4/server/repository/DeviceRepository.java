package com.pbl4.server.repository;

import com.pbl4.server.entity.DeviceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface DeviceRepository extends MongoRepository<DeviceEntity, String> {
}
