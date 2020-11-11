package com.concept.test.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.concept.test.data.entity.Office;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface OfficeRepository extends CrudRepository<Office, String>, JpaSpecificationExecutor<Office> {
}
