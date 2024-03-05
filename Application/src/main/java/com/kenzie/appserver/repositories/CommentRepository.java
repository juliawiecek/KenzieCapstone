package com.kenzie.appserver.repositories;

<<<<<<< HEAD
import com.kenzie.appserver.repositories.model.ExampleRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CommentRepository extends CrudRepository<ExampleRecord, String> {
=======
import com.kenzie.appserver.repositories.model.CommentRecord;
import org.springframework.data.repository.CrudRepository;

