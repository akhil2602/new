package com.cts.postal.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cts.postal.DAO.postalDao;
import com.cts.postal.model.postal;

@Service("postal_service")


public class postal_serviceIMPL implements postal_service {

	@Autowired
	
    postalDao c;
	
	
	@Transactional
	public postal getSearch(String thePin) {
		// TODO Auto-generated method stub
		return c.getSearch(thePin);
	}

}
