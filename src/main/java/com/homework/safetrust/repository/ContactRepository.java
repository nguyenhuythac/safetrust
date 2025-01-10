package com.homework.safetrust.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homework.safetrust.entity.ContactEntity;
/**
* 
* Contact repository interface extends JpaRepository.
* 
* @author Thac Nguyen
*/
public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{

    /**
    * 
    * <p>Find Contacts by first name or last name</p>
    * @param name the name is used to find contacts
    * @return the amount of contacts
    *
    */
    @Query("select c from contact c where c.name like %?1%")
    List<ContactEntity> findByName(String name);
}
