package com.divinova.core.repository;


import com.divinova.core.model.PoojaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoojaItemRepository extends JpaRepository<PoojaItem, Long> {
}
