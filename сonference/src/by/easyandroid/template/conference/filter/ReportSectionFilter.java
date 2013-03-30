package by.easyandroid.template.conference.filter;

import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.model.Section;

public class ReportSectionFilter implements IFilter<Report> {

	private Section section;
	
	public ReportSectionFilter(Section section) {
		this.section = section;
	}

	@Override
	public boolean isMatch(Report report) {
		return report.getSection().getId().equals(section.getId());
	}
}