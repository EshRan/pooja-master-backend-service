package com.divinova.core.repository;

import com.divinova.core.model.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {

}
