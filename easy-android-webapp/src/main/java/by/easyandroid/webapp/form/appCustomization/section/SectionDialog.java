package by.easyandroid.webapp.form.appCustomization.section;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.model.conference.Section;
import by.easyandroid.webapp.form.AbstractEntityDialog;

@ManagedBean
@SessionScoped
public class SectionDialog extends AbstractEntityDialog<Section> {

	public SectionDialog() throws InstantiationException, IllegalAccessException {
		super(Section.class);
	}
}