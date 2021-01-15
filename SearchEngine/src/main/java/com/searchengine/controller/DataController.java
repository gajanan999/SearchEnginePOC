package com.searchengine.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchengine.model.Data;
import com.searchengine.model.ResponseModel;
import com.searchengine.service.PageService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {
	
	@Autowired
	PageService pageService;

	@GetMapping(path = "/getSearchData", produces = "application/json")
	public ResponseEntity searchData(@RequestParam("keyword") String keyword) {
		
		if(null != keyword && !keyword.isEmpty()) {
			List<String> list = pageService.getAllRelatedPages(keyword);
			ResponseModel model = new ResponseModel();
			model.setMessage(HttpStatus.OK);
			Data data = new Data();
			data.setDataList(list);
			model.setData(data);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}else {
			ResponseModel model = new ResponseModel();
			model.setMessage(HttpStatus.OK);
			List<String> list = new ArrayList<>();
			Data data = new Data();
			data.setDataList(list);
			model.setData(data);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
				
	}
	
}
