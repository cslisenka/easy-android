package org.jazzteam.easyandroid.webapp;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class PopupBean {

	private boolean copyApplication = false;
	private boolean compileApplication = false;
	private boolean deleteApplication = false;
	private boolean registerAccount = false;

	public void openRegister() {
		registerAccount = true;
	}
	
	public void closeRegister() {
		registerAccount = false;
	}
	
	public void openCopy() {
		copyApplication = true;
	}
	
	public void closeCopy() {
		copyApplication = false;
	}
	
	public void openCompile() {
		compileApplication = true;
	}
	
	public void closeCompile() {
		compileApplication = false;
	}	

	public void openDelete() {
		deleteApplication = true;
	}
	
	public void closeDelete() {
		deleteApplication = false;
	}	

	public boolean isCopyApplication() {
		return copyApplication;
	}

	public void setCopyApplication(boolean copyApplication) {
		this.copyApplication = copyApplication;
	}

	public boolean isCompileApplication() {
		return compileApplication;
	}

	public void setCompileApplication(boolean compileApplication) {
		this.compileApplication = compileApplication;
	}

	public boolean isDeleteApplication() {
		return deleteApplication;
	}

	public void setDeleteApplication(boolean deleteApplication) {
		this.deleteApplication = deleteApplication;
	}

	public boolean isRegisterAccount() {
		return registerAccount;
	}

	public void setRegisterAccount(boolean registerAccount) {
		this.registerAccount = registerAccount;
	}
}