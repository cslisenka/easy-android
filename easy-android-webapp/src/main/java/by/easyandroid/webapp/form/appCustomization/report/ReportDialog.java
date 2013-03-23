package by.easyandroid.webapp.form.appCustomization.report;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.model.conference.Report;
import by.easyandroid.webapp.form.AbstractEntityDialog;

@ManagedBean
@SessionScoped
public class ReportDialog extends AbstractEntityDialog<Report> {

	public ReportDialog() throws InstantiationException, IllegalAccessException {
		super(Report.class);
	}
}