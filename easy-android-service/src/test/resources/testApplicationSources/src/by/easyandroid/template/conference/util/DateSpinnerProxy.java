package by.easyandroid.template.conference.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Proxy for Date object with toString customized
 * 
 * @author kslisenko
 */
public class DateSpinnerProxy {

	private Date date;

	public DateSpinnerProxy(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return new SimpleDateFormat("EEEE, d MMMM", new Locale("ru")).format(date);
	}
}