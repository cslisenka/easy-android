package com.example.androidappcreator;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class AndroidappcreatorApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Androidappcreator Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(new TestComposite());
		setMainWindow(mainWindow);
	}

}
