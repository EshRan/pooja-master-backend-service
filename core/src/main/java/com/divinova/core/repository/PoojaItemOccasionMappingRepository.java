package com.divinova.core.repository;


import com.divinova.core.model.PoojaItemOccasionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoojaItemOccasionMappingRepository extends JpaRepository<PoojaItemOccasionMapping, Long> {

}
