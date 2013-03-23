package by.easyandroid.webapp.form;

import javax.faces.event.ActionEvent;

import by.easyandroid.webapp.AbstractBaseDialog;
import by.easyandroid.webapp.util.FacesUtil;
import by.easyandroid.webapp.util.Field;

public class AbstractIdDialog extends AbstractBaseDialog {

	private String objectId;

	public void open(ActionEvent event) {
		super.open();
		objectId = FacesUtil.getAttribute(event, Field.OBJID);
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}