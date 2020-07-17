package com.concept.test.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.concept.test.data.entity.Manager;

public interface ManagerRepository extends CrudRepository<Manager, String>
{

}
