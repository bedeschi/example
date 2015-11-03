package com.example.sumexpandablelistview.adapter;

import java.util.HashMap;
import java.util.List;

import com.example.sumexpandablelistview.R;
import com.example.sumexpandablelistview.commons.Child;
import com.example.sumexpandablelistview.commons.Header;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	
	private Context context;
	private List<Header> headerList;
	private HashMap<String, List<Child>> childList;
	
	public ExpandableListAdapter(Context context, List<Header> headerList, HashMap<String, List<Child>> childList) {
		this.context = context;
		this.headerList = headerList;
		this.childList = childList;
	}

	static class ViewHolderChild {
		protected TextView childName;
		protected EditText edit;
	}
	
	static class ViewHolderHeader {
		protected TextView name;
		protected TextView count;
	}

	@Override
	public int getGroupCount() {
		return headerList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		Header header = getGroup(groupPosition);
		List<Child> childList = this.childList.get(header.getName());
		return childList.size();
	}

	@Override
	public Header getGroup(int groupPosition) {
		return this.headerList.get(groupPosition);
	}
	
	@Override
	public Child getChild(int groupPosition, int childPosition) {
		
		Header header = getGroup(groupPosition);
		List<Child> childList = this.childList.get(header.getName());
		return childList.get(childPosition);
	}	


	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		ViewHolderHeader holder = null;
		if (convertView == null) {

			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.item_header, parent, false);

			holder = new ViewHolderHeader();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.count = (TextView) convertView.findViewById(R.id.count);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolderHeader) convertView.getTag();
		}

		Header header = (Header) getGroup(groupPosition);
		holder.name.setText(header.getName());
		
		List<Child> childList = this.childList.get(header.getName());
		int sum = 0;
		for (Child child : childList) {
			Integer value = child.getValue();
			if(value != null){
				sum += value;
			}
		}
		
		holder.count.setText(Integer.toString(sum));
		
		return convertView;

	}

	@Override
	public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		ViewHolderChild holder = null;
		if (convertView == null) {

			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.item_child, parent, false);

			holder = new ViewHolderChild();
			holder.childName = (TextView) convertView.findViewById(R.id.childName);
			holder.edit = (EditText) convertView.findViewById(R.id.editText);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolderChild) convertView.getTag();
		}
		
		final Child child = (Child) getChild(groupPosition, childPosition);
		
		holder.childName.setText(child.getName());
		
		Integer value = child.getValue() != null?child.getValue():0;
		
		holder.edit.setText(Integer.toString(value));
		holder.edit.addTextChangedListener(new TextWatcher() {

	        @Override
	        public void afterTextChanged(Editable s) {}

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {

	        	String value = s.toString();
	        	if(value != null && !value.equals("")){
					child.setValue(Integer.parseInt(value));
		        	notifyDataSetChanged();
	        	}

	        }

	    });
		
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

