package com.searchengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.searchengine.entities.PageDetails;

public interface PageDetailsRepository extends JpaRepository<PageDetails, Long> {

	List<PageDetails> findByAddressContainingIgnoreCase(String keyword);
}
