package by.easyandroid.template.conference.util.adapter;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import by.easyandroid.template.conference.model.Identity;

public class SpinnerIdentityListAdapter<T extends Identity> extends ArrayAdapter<T> {

	public SpinnerIdentityListAdapter(Context context, List<T> data) {
		super(context, android.R.layout.simple_spinner_item, data);
		setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(getItem(position).getId());
	}
}