package by.easyandroid.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.easyandroid.model.ApplicationTemplate;

@ManagedBean
@RequestScoped
public class MyApplicationsForm {

	private List<ApplicationTemplate> templates = new ArrayList<ApplicationTemplate>();
	
	public MyApplicationsForm() {
		ApplicationTemplate t1 = new ApplicationTemplate();
		t1.setId("1");
		t1.setTitle("Conference");
		t1.setDescription("This is basic application for conference. You can set events, and reports schedule.");
		
		templates.add(t1);
		
		ApplicationTemplate t2 = new ApplicationTemplate();
		t2.setId("2");
		t2.setTitle("Internet shop");
		t2.setDescription("This is some example application for e-commerce. You can set integration with payment systems here and with your e-commerce web-portal.");
		
		templates.add(t2);

		ApplicationTemplate t3 = new ApplicationTemplate();
		t3.setId("3");
		t3.setTitle("Catalog");
		t3.setDescription("Simple catalog for common needs. You can create your shop catalog here, or you can do helper book for learning something.");
		
		templates.add(t3);
	}

	public List<ApplicationTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(List<ApplicationTemplate> templates) {
		this.templates = templates;
	}
}