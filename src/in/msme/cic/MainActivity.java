package in.msme.cic;

import java.util.List;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.R.id;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
				android.R.layout.simple_dropdown_item_1line, titles));
		listView2.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, contacts));
		
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
		case R.id.Refresh:
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
				home firstFragment = new home();
				firstFragment.setArguments(getIntent().getExtras());
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content, firstFragment).commit();
				break;

			case 1:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Services.newInstance(position + 1)).commit();
				break;

			case 2:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Events.newInstance(position + 1))
						.commit();
				break;
			case 3:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content, Latest.newInstance(position + 1))
						.commit();
				break;
			case 4:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Connect.newInstance(position + 1)).commit();
				break;
			case 5:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content,
								Downloads.newInstance(position + 1)).commit();
				break;
			case 6:
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
				Intent facebookIntent = getOpenFacebookIntent(this
						.getApplicationContext());
				startActivity(facebookIntent);
				break;

			case 1:
				try {
					startActivity(new Intent(Intent.ACTION_VIEW,
							Uri.parse("twitter://user?screen_name="
									+ "iamsmeofindia")));
				} catch (Exception e) {
					startActivity(new Intent(Intent.ACTION_VIEW,
							Uri.parse("https://twitter.com/#!/"
									+ "iamsmeofindia")));
				}
				break;

			case 2:
				openGPlus("107620463159280242405");
				break;

			case 3:
				Intent intent = null;
				String url = "https://www.youtube.com/channel/UCvNLtCUbGzw4J5etQOHx52Q";
				try {
					intent = new Intent(Intent.ACTION_VIEW);
					intent.setPackage("com.google.android.youtube");
					intent.setData(Uri.parse(url));
					startActivity(intent);
				} catch (ActivityNotFoundException e) {
					intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(url));
					startActivity(intent);
				}
				break;

			case 4:
				Intent web = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://iamsmeofindia.com/"));
				startActivity(web);
				break;

			case 5:
				try {
					String address = "New Industrial Township, Faridabad, Haryana, India";// Get
																							// address
					address = address.replace(' ', '+');
					Intent geoIntent = new Intent(
							android.content.Intent.ACTION_VIEW,
							Uri.parse("geo:0,0?q=" + address)); // Prepare
																// intent
					startActivity(geoIntent); // Initiate lookup
				} catch (Exception e) {
					startActivity(new Intent(
							android.content.Intent.ACTION_VIEW,
							Uri.parse("http://maps.google.com/maps?ll=28.385703,77.299796&z=14&t=m&hl=en-US&gl=US&mapclient=embed&q=New%20Industrial%20Township%20Faridabad%2C%20Haryana%20121001%20India")));
				}
				break;

			}
		}
	}

	public void selectedItem(int position) {
		// TODO Auto-generated method stub
		if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
			listView.setItemChecked(position, true);

			if (position == 0) {
				setTitle("");

			} else {
				setTitle(titles[position]);
			}

			drawerLayout.closeDrawer(listView);

		}
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
			getSupportActionBar().setTitle(title);
		}
	}

	public static Intent getOpenFacebookIntent(Context context) {

		try {
			context.getPackageManager()
					.getPackageInfo("com.facebook.katana", 0);

			return new Intent(Intent.ACTION_VIEW,
					Uri.parse("fb://profile/631552371"));
		} catch (Exception e) {
			return new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://www.facebook.com/therajanmaurya"));
		}
	}

	public void openGPlus(String profile) {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setClassName("com.google.android.apps.plus",
					"com.google.android.apps.plus.phone.UrlGatewayActivity");
			intent.putExtra("customAppUri", profile);
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://plus.google.com/" + profile + "/posts")));
		}

	}

}
