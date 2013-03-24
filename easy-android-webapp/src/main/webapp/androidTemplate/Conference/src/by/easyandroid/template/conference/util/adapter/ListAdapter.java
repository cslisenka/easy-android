package by.easyandroid.template.conference.util.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import by.easyandroid.template.conference.model.Identity;

public abstract class ListAdapter<T extends Identity> extends BaseAdapter {

	private List<T> items = new ArrayList<T>();
	private Context context;
	private int viewId;
	
	public ListAdapter(Context context, List<T> items, int viewId) {
		this.context = context;
		this.items = items;
		this.viewId = viewId;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public T getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(items.get(position).getId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listElementView = inflater.inflate(viewId, parent, false);
		fillView(items.get(position), listElementView);
		return listElementView;
	}
	
	protected abstract void fillView(T item, View view);
}