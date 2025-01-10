package com.homework.safetrust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homework.safetrust.entity.ContactEntity;
/**
* 
* Contact repository interface extends JpaRepository.
* 
*/
public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{

    @Query("select c from contact c where c.name like %?1%")
    List<ContactEntity> findByName(String name);
}
