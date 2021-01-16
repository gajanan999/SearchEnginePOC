package com.searchengine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchengine.entities.PageDetails;
import com.searchengine.repository.PageDetailsRepository;

@Service
public class PageService {
	
	@Autowired
	PageDetailsRepository pageDetailsRepository;

	public List<String> getAllRelatedPages(String keyword){
		List<PageDetails> pageList = pageDetailsRepository.findByAddressContainingIgnoreCase(keyword);
		List<String> addresses = 
				pageList.stream()
			              .map(PageDetails::getAddress)
			              .collect(Collectors.toList());
		List<String> finalList = new ArrayList<String>();
		List<String> firstList = addresses.stream()
        .filter(str -> str.startsWith(keyword))
        .collect(Collectors.toList());
		Collections.sort(firstList);
		addresses.removeAll(firstList);
		finalList.addAll(firstList);
		finalList.addAll(addresses);
		
		return finalList;
	}
}
