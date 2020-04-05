package com.api.vishal.repository;
import com.api.vishal.model.Vishal;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VishalRepository extends MongoRepository<Vishal, String> {

	public List findByName(String name);
}
