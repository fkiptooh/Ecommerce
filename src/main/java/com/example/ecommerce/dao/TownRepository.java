package com.example.ecommerce.dao;

import com.example.ecommerce.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface TownRepository extends JpaRepository<Town, Integer> {
    List<Town> findByCountyCode (@Param("code") String code);
}
