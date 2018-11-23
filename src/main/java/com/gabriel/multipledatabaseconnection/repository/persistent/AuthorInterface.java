package com.gabriel.multipledatabaseconnection.repository.persistent;


import com.gabriel.multipledatabaseconnection.model.persistent.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorInterface extends CrudRepository<Author, Integer> {
}
