package by.easyandroid.webapp.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import by.easyandroid.model.Identity;

public class FacesUtil {

	public static <T extends Identity> List<SelectItem> toSelectItems(List<T> objects) {
		List<SelectItem> result = new ArrayList<SelectItem>();
		for (T obj : objects) {
			result.add(new SelectItem(obj.getId(), obj.toString()));
		}
		return result;
	}
	
	public static <T> T getRequestBean(String name) {
		return (T) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(name);
	}
	
	public static String getParameter(String parameterName) {
		String parameter = FacesContext.getCurrentInstance()
		    .getExternalContext().getRequestParameterMap()
		    .get(parameterName);
		return parameter;
	}	
	
	public static String getStringAttribute(ActionEvent event, String attrName) {
		Object value = event.getComponent().getAttributes().get(attrName);
		if (value != null) {
			return (String) value;
		}
		
		return null;
	}
	
	public static Object getAttribute(ActionEvent event, String attrName) {
		return event.getComponent().getAttributes().get(attrName);
	}	
}