package by.easyandroid.webapp.form.appCustomization.section;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.conference.SectionService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.conference.Section;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;

@ManagedBean
@RequestScoped
public class SectionsForm extends AbstractConferenceBaseForm {

	@ManagedProperty(value = "#{createSectionDialog}")
	private CreateSectionDialog createSectionDialog;
	
	@ManagedProperty(value = "#{deleteSectionDialog}")
	private DeleteSectionDialog deleteSectionDialog;
	
	@ManagedProperty(value = "#{sectionService}")
	private SectionService sectionService;
	
	private List<Section> sections = new ArrayList<Section>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO it's better to do that when enabling/disabling opening ConferenceForm
		// TODO check if selected conference belongs to user
		if (template != null) {
			sections = template.getModel().getSections();
		}
	}
	
	public void create(ActionEvent event) {
		Section section = new Section();
		section.setName(createSectionDialog.getName());
		
		try {
			if (template != null) {
				sectionService.add(section, template);
				createSectionDialog.close();
			}
		} catch (DatabaseServiceException e) {
			// TODO display error message
			e.printStackTrace();
		}
	}
	
	public void deleteById(String id) {
		sectionService.delete(id, template);
		deleteSectionDialog.close();
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public CreateSectionDialog getCreateSectionDialog() {
		return createSectionDialog;
	}

	public void setCreateSectionDialog(CreateSectionDialog createSectionDialog) {
		this.createSectionDialog = createSectionDialog;
	}

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public DeleteSectionDialog getDeleteSectionDialog() {
		return deleteSectionDialog;
	}

	public void setDeleteSectionDialog(DeleteSectionDialog deleteSectionDialog) {
		this.deleteSectionDialog = deleteSectionDialog;
	}
}