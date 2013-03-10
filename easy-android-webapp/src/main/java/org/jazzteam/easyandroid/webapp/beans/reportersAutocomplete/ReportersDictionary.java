package org.jazzteam.easyandroid.webapp.beans.reportersAutocomplete;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.jazzteam.easyandroid.webapp.beans.PopupBean;

import by.easyandroid.model.conference.Reporter;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

@ManagedBean
public class ReportersDictionary {

	private List<Reporter> reporters = new ArrayList<Reporter>();
	private List<SelectItem> reportersMatchPossibilities = new ArrayList<SelectItem>();
	
	public ReportersDictionary() {
		// Init reporters list
		Reporter rp1 = new Reporter();
		rp1.setName("Dave Nilson");
		reporters.add(rp1);

		Reporter rp2 = new Reporter();
		rp2.setName("Konstantin Slisenko");
		reporters.add(rp2);
		
		Reporter rp3 = new Reporter();
		rp3.setName("Haroki Takama");
		reporters.add(rp3);

		Reporter rp4 = new Reporter();
		rp4.setName("Sergey Ivanov");
		reporters.add(rp4);
		
		Reporter rp5 = new Reporter();
		rp5.setName("Alexey Semenov");
		reporters.add(rp5);

		Reporter rp6 = new Reporter();
		rp6.setName("Nilie Mahindra");
		reporters.add(rp6);				
	}
	
	public void fillReporterMatches() {
		reportersMatchPossibilities.clear();
		for (Reporter r : reporters) {
			SelectItem si = new SelectItem(r, r.getName());
			reportersMatchPossibilities.add(si);
		}
	}
	
	public void selectInputValueChanged(ValueChangeEvent e) {
		if (e.getComponent() instanceof SelectInputText) {
			fillReporterMatches();
			PopupBean pb = (PopupBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("popupBean");
			pb.setCopyApplication(true);
		}
	}

	public List<SelectItem> getReportersMatchPossibilities() {
		return reportersMatchPossibilities;
	}
}