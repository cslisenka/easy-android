package org.jazzteam.easyandroid.webapp.util;

import javax.faces.context.FacesContext;

public class FacesUtil {

	public static <T> T getRequestBean(String name) {
		return (T) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(name);
	}
}