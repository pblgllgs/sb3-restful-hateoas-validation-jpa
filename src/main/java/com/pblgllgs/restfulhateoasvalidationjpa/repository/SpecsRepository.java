package com.pblgllgs.restfulhateoasvalidationjpa.repository;

import com.pblgllgs.restfulhateoasvalidationjpa.model.Specs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecsRepository extends JpaRepository<Specs,Long> {
}
