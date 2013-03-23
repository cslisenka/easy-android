package by.easyandroid.webapp.form;

import javax.faces.event.ActionEvent;

import by.easyandroid.webapp.AbstractBaseDialog;
import by.easyandroid.webapp.util.FacesUtil;
import by.easyandroid.webapp.util.Field;

public class AbstractDeleteDialog extends AbstractBaseDialog {

	private String deletedId;

	public void open(ActionEvent event) {
		deletedId = FacesUtil.getAttribute(event, Field.OBJID);
		super.open();
	}
	
	public String getDeletedId() {
		return deletedId;
	}

	public void setDeletedId(String deletedId) {
		this.deletedId = deletedId;
	}
}