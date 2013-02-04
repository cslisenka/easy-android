package org.jazzteam.easyandroid.webapp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.jazzteam.easyandroid.webapp.model.Template;

@ManagedBean
public class MyTemplatesBean {

	private List<Template> templates = new ArrayList<Template>();
	
	public MyTemplatesBean() {
		// TODO [kslisenko] 23.08.2012: init templates
		Template t1 = new Template();
		t1.setId("1");
		t1.setName("Solit 2012");
		t1.setDate("22.10.2012");
		
		templates.add(t1);
		
		Template t2 = new Template();
		t2.setId("2");
		t2.setName("Solit 2013");
		t2.setDate("22.10.2013");
		
		templates.add(t2);

		Template t3 = new Template();
		t3.setId("3");
		t3.setName("BSUIR conference 2012");
		t3.setDate("10.06.2012");
		
		templates.add(t3);
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
}