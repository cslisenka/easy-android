package by.easyandroid.webapp.util;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class FacesUtil {

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