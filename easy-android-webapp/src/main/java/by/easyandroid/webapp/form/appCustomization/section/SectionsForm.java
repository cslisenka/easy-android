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
import by.easyandroid.webapp.form.ICrudForm;
import by.easyandroid.webapp.form.appCustomization.AbstractConferenceBaseForm;
import by.easyandroid.webapp.util.Bean;

@ManagedBean
@RequestScoped
public class SectionsForm extends AbstractConferenceBaseForm implements ICrudForm<Section> {

	@ManagedProperty(value = "#{createSectionDialog}")
	private CreateSectionDialog createSectionDialog;
	
	@ManagedProperty(value = "#{deleteSectionDialog}")
	private DeleteSectionDialog deleteSectionDialog;
	
	@ManagedProperty(value = Bean.SRV_SECTION)
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
	
	@Override
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
	
	@Override
	public void delete(ActionEvent id) {
		sectionService.delete(deleteSectionDialog.getDeletedId(), template);
		deleteSectionDialog.close();
	}

	@Override
	public void edit(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public List<Section> getAll() {
		return sections;
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