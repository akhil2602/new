package com.cts.postal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.postal.model.postal;
import com.cts.postal.service.postal_service;

@Controller
@RequestMapping("/postal")

public class postal_controller {
	
	@Autowired
	postal_service ps;
	
	@GetMapping("/search")
	
	public String search_postal(@RequestParam("pin") String thePin,Model theModel ) {
		
		postal thePostal = ps.getSearch(thePin);
		theModel.addAttribute("postal", thePostal);
		return "abcd";
	}

}
