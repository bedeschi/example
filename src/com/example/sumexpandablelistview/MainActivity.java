package com.example.sumexpandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.sumexpandablelistview.adapter.ExpandableListAdapter;
import com.example.sumexpandablelistview.commons.Child;
import com.example.sumexpandablelistview.commons.Header;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(new Header("header1"));
		headerList.add(new Header("header2"));
		
		List<Child> childsHeader1 = new ArrayList<Child>();
		childsHeader1.add(new Child("child1 - header1"));
		childsHeader1.add(new Child("child2 - header1"));
		childsHeader1.add(new Child("child3 - header1"));
		
		List<Child> childsHeader2 = new ArrayList<Child>();
		childsHeader2.add(new Child("child1 - header2"));
		childsHeader2.add(new Child("child2 - header2"));
		childsHeader2.add(new Child("child3 - header2"));
		
		HashMap<String, List<Child>> childList = new HashMap<String, List<Child>>();
		
		childList.put("header1", childsHeader1);
		childList.put("header2", childsHeader2);
		
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandableSelectionList);
		
		ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, headerList, childList);
		expListView.setAdapter(listAdapter);
		
	}


}
