package by.easyandroid.webapp.form;

import javax.faces.event.ActionEvent;

import by.easyandroid.model.Identity;
import by.easyandroid.webapp.AbstractBaseDialog;
import by.easyandroid.webapp.util.FacesUtil;
import by.easyandroid.webapp.util.Field;

public abstract class AbstractEntityDialog<T extends Identity> extends AbstractBaseDialog {

	private T object;

	public AbstractEntityDialog(Class<T> entityType) throws InstantiationException, IllegalAccessException {
		object = entityType.newInstance();
	}
	
	public void open(ActionEvent event) {
		super.open();
		
		Object obj = FacesUtil.getAttribute(event, Field.OBJECT);
		if (obj != null && obj instanceof Identity) {
			object = (T) obj;
		}
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
}