package org.jazzteam.easyandroid.webapp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.jazzteam.easyandroid.webapp.model.Template;

@ManagedBean
public class PublicTemplatesBean {

	private List<Template> templates = new ArrayList<Template>();
	
	public PublicTemplatesBean() {
		// TODO [kslisenko] 23.08.2012: init templates
		Template t1 = new Template();
		t1.setId("1");
		t1.setName("Conference");
		t1.setDate("22/10/2012");
		t1.setDescription("This is basic application for conference. You can set events, and reports schedule.");
		
		templates.add(t1);
		
		Template t2 = new Template();
		t2.setId("2");
		t2.setName("Internet shop");
		t2.setDate("22/10/2013");
		t2.setDescription("This is some example application for e-commerce. You can set integration with payment systems here and with your e-commerce web-portal.");
		
		templates.add(t2);

		Template t3 = new Template();
		t3.setId("3");
		t3.setName("Catalog");
		t3.setDate("10/06/2012");
		t3.setDescription("Simple catalog for common needs. You can create your shop catalog here, or you can do helper book for learning something.");
		
		templates.add(t3);
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
}