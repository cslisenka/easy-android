package by.easyandroid.webapp.form.appCustomization.report;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.model.conference.Report;
import by.easyandroid.webapp.form.ICrudForm;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;

@ManagedBean
@RequestScoped
public class ReportsForm extends AbstractConferenceBaseForm implements ICrudForm<Report> {

	private List<Report> reports = new ArrayList<Report>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO check if selected conference belongs to user
		if (template != null) {
			reports = template.getModel().getReports();
		}
	}

	@Override
	public List<Report> getAll() {
		return reports;
	}

	@Override
	public void delete(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}