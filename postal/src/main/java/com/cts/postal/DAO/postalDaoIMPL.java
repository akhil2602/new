package com.cts.postal.DAO;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.postal.model.postal;
@Repository("postalDao")

public class postalDaoIMPL implements postalDao {
	@Autowired
	SessionFactory sessionFactory;

	public postal getSearch(String thePin) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb=session.getCriteriaBuilder();
		 CriteriaQuery<postal>  cq=cb.createQuery(postal.class);
		 Root<postal> root=cq.from(postal.class);
		 cq.select(root);
		 cq.where(cb.equal(root.get("pincode"),thePin));
		 TypedQuery<postal> typed=session.createQuery(cq);
		 try{ return
				  typed.getSingleResult();
		 } 
		 catch(Exception e) {
			 
				 System.out.println("NO search found"); 
				 return null;
				 }
		
	}

	
	/*
	 * public postal getSearch(int thePin) { // TODO Auto-generated method stub
	 * Session session = sessionFactory.getCurrentSession();
	 * 
	 * CriteriaBuilder cb=session.getCriteriaBuilder(); CriteriaQuery<postal>
	 * cq=cb.createQuery(postal.class); Root<postal> root=cq.from(postal.class);
	 * cq.select(root); cq.where(cb.equal(root.get("pincode"),thePin));
	 * TypedQuery<postal> typed=session.createQuery(cq); try{ return
	 * typed.getSingleResult(); } catch(Exception e) {
	 * System.out.println("NO search found"); return null; } }
	 */
	 
}
