package by.easyandroid.template.conference.filter;

import by.easyandroid.template.conference.model.Category;
import by.easyandroid.template.conference.model.Report;

public class ReportCategoryFilter implements IFilter<Report> {

	private Category category;
	
	public ReportCategoryFilter(Category category) {
		this.category = category;
	}

	@Override
	public boolean isMatch(Report report) {
		return report.getCategory().getId().equals(category.getId());
	}
}