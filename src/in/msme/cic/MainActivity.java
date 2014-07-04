/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.msme.cic;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.annotation.SuppressLint;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends SherlockFragmentActivity  implements OnItemClickListener{

	protected DrawerLayout drawerLayout;
	public ListView listView, listView2;
	public String[] titles; 
	public String[] contacts;
	private ActionBarDrawerToggle drawerListner;
	private static final String ARG_SECTION_NUMBER = "section_number";
	public int counter =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		listView=(ListView)findViewById(R.id.left_drawer);
		listView2=(ListView)findViewById(R.id.right_drawer);
		titles= getResources().getStringArray(R.array.msme_names);
		contacts = getResources().getStringArray(R.array.contact_me);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, titles));
		listView2.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_selectable_list_item, contacts));
		listView.setOnItemClickListener(this);
		listView2.setOnItemClickListener(this);
		
		drawerListner = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Drawer Open", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Drawer close", Toast.LENGTH_SHORT).show();
			}
		};
		drawerLayout.setDrawerListener(drawerListner);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content, PageSlidingTabStripFragment.newInstance(0))
			.commit();
		
		}
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home: {
			if (drawerLayout.isDrawerOpen(listView)) {
				drawerLayout.closeDrawer(listView);
			} else {
				drawerLayout.openDrawer(listView);
			}
			break;
		}
		case R.id.action_contact:
			// QuickContactFragment dialog = new QuickContactFragment();
			// dialog.show(getSupportFragmentManager(), "QuickContactFragment");
			// return true;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerListner.syncState();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if(drawerLayout.isDrawerVisible(Gravity.LEFT)){
		Toast.makeText(this , titles[position]+" item clicked", Toast.LENGTH_SHORT).show();
		selectedItem(position);
		
		switch(position){
		case 0: getSupportFragmentManager()
        				.beginTransaction()
        				.replace(R.id.content, PageSlidingTabStripFragment.newInstance(position + 1))
        				.commit();
						break;
		
		case 1: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Services.newInstance(position + 1))
						.commit();
						break;
		case 2: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Deals.newInstance(position + 1))
						.commit();
						break;	
		case 3: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Events.newInstance(position + 1))
						.commit();
						break;	
		case 4: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Latest.newInstance(position + 1))
						.commit();
						break;	
		case 5: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Connect.newInstance(position + 1))
						.commit();
						break;	
		case 6: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Downloads.newInstance(position + 1))
						.commit();
						break;	
		case 7: getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Gallery.newInstance(position + 1))
						.commit();
						break;
						
		}
		}
		else if(drawerLayout.isDrawerVisible(Gravity.RIGHT)){
			
			
			switch (position) {
			case 0: Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pa1.pal"));
            startActivity(facebook);
            break;

			case 1: Intent twitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/pa1pal"));
            startActivity(twitter);
            break;
            
			case 2: Intent gplus = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/+PawanPalPa1/posts"));
            startActivity(gplus);
            break;
            
			case 3: Intent yt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
            startActivity(yt);
            break;
            
			case 4: Intent ig = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/"));
            startActivity(ig);
            break;
            
			case 5: Intent tm = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tumblr.com/"));
            startActivity(tm);
            break;
            
			case 6: Intent pint = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/"));
            startActivity(pint);
            break;
            
			case 7: Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pa1.pal"));
            startActivity(web);
            break;
            
			case 8: Intent flickr = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flickr.com/"));
            startActivity(flickr);
            break;
            
			case 9: Intent su = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.stumbleupon.com/"));
            startActivity(su);
            break;
			
            
			}
		}
	}


	
	public void selectedItem(int position) {
		// TODO Auto-generated method stub
		if(drawerLayout.isDrawerVisible(Gravity.LEFT)){
		listView.setItemChecked(position, true);
		setTitle(titles[position]);}
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		if(drawerLayout.isDrawerVisible(Gravity.LEFT)){
		getSupportActionBar().setTitle(title);
	}}

	/**@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	
	
	
	public static class Main extends Fragment{
		
		
		@SuppressLint("ValidFragment")
		public static Main newInstance(int sectionNumber){
			Main fragment = new Main();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
	        fragment.setArguments(args);
			return fragment;
			
		}

		public Main() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			return inflater.inflate(R.layout.fragment_main, container, false);
		}
	}

public static class Services extends Fragment {
		
	public static Services newInstance(int sectionNumber){
		Services fragment = new Services();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
		public Services() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_services, container,
					false);
		}
	}

public static class Deals extends Fragment {
	
	public static Deals newInstance(int sectionNumber){
		Deals fragment = new Deals();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	
	public Deals() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_deals, container,
				false);
	}
}
public static class Events extends Fragment {
	
	public static Events newInstance(int sectionNumber){
		Events fragment = new Events();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	public Events() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_events, container,
				false);
	}
}

public static class Latest extends Fragment {
	
	public static Latest newInstance(int sectionNumber){
		Latest fragment = new Latest();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	public Latest() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_latest, container,
				false);
	}
}
public static class Connect extends Fragment {

	public static Connect newInstance(int sectionNumber){
		Connect fragment = new Connect();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
public Connect() {
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	return inflater.inflate(R.layout.fragment_connect, container,
			false);
}
}
public static class Downloads extends Fragment {

	public static Downloads newInstance(int sectionNumber){
		Downloads fragment = new Downloads();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	
public Downloads() {
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	return inflater.inflate(R.layout.fragment_downloads, container,
			false);
}
}
public static class Gallery extends Fragment {

	public static Gallery newInstance(int sectionNumber){
		Gallery fragment = new Gallery();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
public Gallery() {
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	return inflater.inflate(R.layout.fragment_gallery, container,
			false);
}
}

}
