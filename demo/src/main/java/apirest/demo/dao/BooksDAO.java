package apirest.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import apirest.demo.entity.books;

public interface BooksDAO extends JpaRepository<books,Long> {
    
}
