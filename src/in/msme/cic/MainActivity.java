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
import android.annotation.TargetApi;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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

public class MainActivity extends SherlockFragmentActivity implements
		OnItemClickListener {

	protected DrawerLayout drawerLayout;
	public ListView listView, listView2;
	public String[] titles;
	public String[] contacts;
	private ActionBarDrawerToggle drawerListner;
	private static final String ARG_SECTION_NUMBER = "section_number";
	public int counter = 0;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		listView = (ListView) findViewById(R.id.left_drawer);
		listView2 = (ListView) findViewById(R.id.right_drawer);
		titles = getResources().getStringArray(R.array.msme_names);
		contacts = getResources().getStringArray(R.array.contact_me);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_selectable_list_item, titles));
		listView2.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_selectable_list_item, contacts));
		listView.setOnItemClickListener(this);
		listView2.setOnItemClickListener(this);

		drawerListner = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				// Toast.makeText(MainActivity.this, "Drawer Open",
				// Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				// Toast.makeText(MainActivity.this, "Drawer close",
				// Toast.LENGTH_SHORT).show();
			}
		};
		drawerLayout.setDrawerListener(drawerListner);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			getSupportFragmentManager()

			.beginTransaction().replace(R.id.content, home.newInstance(0))
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

		if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
			// Toast.makeText(this , titles[position]+" item clicked",
			// Toast.LENGTH_SHORT).show();
			selectedItem(position);

			switch (position) {
			case 0:
				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content, home.newInstance(position), home.TAG)
						.commit();
				break;

			case 1:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Services.newInstance(position + 1)).commit();
				break;

			case 2:
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content, Deals.newInstance(position + 1))
						.commit();
				break;
			case 3:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Events.newInstance(position + 1))
						.commit();
				break;
			case 4:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Latest.newInstance(position + 1))
						.commit();
				break;
			case 5:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Connect.newInstance(position + 1)).commit();
				break;
			case 6:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Downloads.newInstance(position + 1)).commit();
				break;
			case 7:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Gallery.newInstance(position + 1)).commit();
				break;
			/*
			 * default:
			 * 
			 * getSupportFragmentManager() .beginTransaction()
			 * .replace(R.id.content, Webview.newInstance(position +
			 * 1)).commit(); break;
			 */
			}
		} else if (drawerLayout.isDrawerVisible(Gravity.RIGHT)) {

			switch (position) {
			case 0:
				Intent facebook = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.facebook.com/pa1.pal"));
				startActivity(facebook);
				break;

			case 1:
				Intent twitter = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://twitter.com/pa1pal"));
				startActivity(twitter);
				break;

			case 2:
				Intent gplus = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://plus.google.com/u/0/+PawanPalPa1/posts"));
				startActivity(gplus);
				break;

			case 3:
				Intent yt = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.youtube.com"));
				startActivity(yt);
				break;

			case 4:
				Intent ig = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://instagram.com/"));
				startActivity(ig);
				break;

			case 5:
				Intent tm = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.tumblr.com/"));
				startActivity(tm);
				break;

			case 6:
				Intent pint = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.pinterest.com/"));
				startActivity(pint);
				break;

			case 7:
				Intent web = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.facebook.com/pa1.pal"));
				startActivity(web);
				break;

			case 8:
				Intent flickr = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.flickr.com/"));
				startActivity(flickr);
				break;

			case 9:
				Intent su = new Intent(Intent.ACTION_VIEW,
						Uri.parse("https://www.stumbleupon.com/"));
				startActivity(su);
				break;

			}
		}
	}

	public void selectedItem(int position) {
		// TODO Auto-generated method stub
		if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
			listView.setItemChecked(position, true);
			setTitle(titles[position]);
		}
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
			getSupportActionBar().setTitle(title);
		}
	}

}
