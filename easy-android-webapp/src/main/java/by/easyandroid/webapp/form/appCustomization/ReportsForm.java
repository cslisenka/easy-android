package by.easyandroid.webapp.form.appCustomization;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.easyandroid.model.conference.Report;

@ManagedBean
@RequestScoped
public class ReportsForm extends AbstractConferenceBaseForm {

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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
}