package by.easyandroid.webapp.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.webapp.AbstractBaseDialog;

@ManagedBean
@SessionScoped
public class CopyTemplateDialog extends AbstractBaseDialog {

	private String templateId;
	private String templateTitle;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateTitle() {
		return templateTitle;
	}

	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}
}