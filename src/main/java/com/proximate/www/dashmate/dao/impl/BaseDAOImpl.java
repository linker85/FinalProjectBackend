package com.proximate.www.dashmate.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="baseDAO")
public class BaseDAOImpl{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private HibernateTemplate hibernateTemplateJV;		

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplateJV() {
		return hibernateTemplateJV;
	}

	public void setHibernateTemplateJV(HibernateTemplate hibernateTemplateJV) {
		this.hibernateTemplateJV = hibernateTemplateJV;
	}

}