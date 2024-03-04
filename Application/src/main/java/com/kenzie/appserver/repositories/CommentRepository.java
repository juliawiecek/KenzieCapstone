package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.CommentRecord;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentRecord, String> {
}
