package com.dmadev.rest.repository;


import com.dmadev.rest.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepo extends JpaRepository<Cat,Long> {
}
