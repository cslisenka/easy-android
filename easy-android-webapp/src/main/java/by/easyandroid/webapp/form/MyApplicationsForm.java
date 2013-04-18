package by.easyandroid.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.User;
import by.easyandroid.service.compilation.ApplicationBuildService;
import by.easyandroid.service.exception.ApplicationServiceException;
import by.easyandroid.webapp.AbstractBaseForm;
import by.easyandroid.webapp.beans.UserBean;
import by.easyandroid.webapp.util.Bean;
import by.easyandroid.worker.client.WorkerWsClient;

@ManagedBean
@RequestScoped
public class MyApplicationsForm extends AbstractBaseForm {

	@ManagedProperty(value = "#{compileApplicationDialog}")
	private ApplicationDialog compileDialog;
	
	@ManagedProperty(value = "#{copyApplicationDialog}")
	private ApplicationDialog copyDialog;
	
	@ManagedProperty(value = "#{deleteApplicationDialog}")
	private ApplicationDialog deleteDialog;	
	
	@ManagedProperty(value = Bean.SRV_APPLICATION)
	private ApplicationInstanceService instanceService;
	
	@ManagedProperty(value = Bean.SRV_COMPILATION)
	private ApplicationBuildService compilationService;
	
	@ManagedProperty(value = Bean.BN_USER)
	private UserBean userBean;
	
	@ManagedProperty(value = "#{workerWsClient}")
	private WorkerWsClient wsClient;
	
	private List<ApplicationInstance> templates = new ArrayList<ApplicationInstance>();
	
	@Override
	public void init() {
		userBean.updateUserData();
		User currentUser = userBean.getUser();
		if (currentUser != null) {
			templates = currentUser.getApplications();
		}
	}

	public void compile(ActionEvent event) throws ApplicationServiceException {
		wsClient.callAndroidAppBuildWS(compileDialog.getObject().getId());
		compileDialog.close();
		init();
	}
	
	public List<ApplicationInstance> getTemplates() {
		return templates;
	}

	public ApplicationInstanceService getInstanceService() {
		return instanceService;
	}

	public void setInstanceService(ApplicationInstanceService instanceService) {
		this.instanceService = instanceService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ApplicationDialog getCompileDialog() {
		return compileDialog;
	}

	public void setCompileDialog(ApplicationDialog compileDialog) {
		this.compileDialog = compileDialog;
	}

	public ApplicationBuildService getCompilationService() {
		return compilationService;
	}

	public void setCompilationService(ApplicationBuildService compilationService) {
		this.compilationService = compilationService;
	}

	public ApplicationDialog getCopyDialog() {
		return copyDialog;
	}

	public void setCopyDialog(ApplicationDialog copyDialog) {
		this.copyDialog = copyDialog;
	}

	public ApplicationDialog getDeleteDialog() {
		return deleteDialog;
	}

	public void setDeleteDialog(ApplicationDialog deleteDialog) {
		this.deleteDialog = deleteDialog;
	}

	public WorkerWsClient getWsClient() {
		return wsClient;
	}

	public void setWsClient(WorkerWsClient wsClient) {
		this.wsClient = wsClient;
	}
}