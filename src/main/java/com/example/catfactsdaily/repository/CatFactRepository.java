package com.example.catfactsdaily.repository;

import com.example.catfactsdaily.entity.CatFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface CatFactRepository extends JpaRepository<CatFact, UUID> {

    List<CatFact> findByUserID(int userID);
}
