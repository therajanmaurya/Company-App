package in.msme.cic;

import in.msme.cic.database.Event;
import in.msme.cic.database.EventSQL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Events extends Fragment {
	EventSQL db;
	private static final String ARG_SECTION_NUMBER = "section_number";
	Context context;
	static DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	ProgressDialog progress;
	EventAdapter adp;
	ProgressBar Pevent;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	private static String urljson = "http://pa1pal.tk/LE.json";
	private static final String TAG_Latest = "Latest";
	private static final String TAG_TITLE = "Title";
	private static final String TAG_Date = "Date";
	private static final String TAG_Venue = "Venue";
	private static final String TAG_Description = "Description";
	private static final String TAG_ImageLink = "link";
	JSONArray Latest = null;
	ArrayList<String> TitleS = new ArrayList<String>();
	ArrayList<String> DateS = new ArrayList<String>();
	ArrayList<String> VenueS = new ArrayList<String>();
	ArrayList<String> Ldesc = new ArrayList<String>();
	ArrayList<String> ImageLink = new ArrayList<String>();

	ExpandableListView expand;

	ArrayList<String> urlf = new ArrayList<String>();

	public static Events newInstance(int sectionNumber) {
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
		View V = inflater.inflate(R.layout.fragment_latest, container, false);
		Pevent = (ProgressBar)V.findViewById(R.id.progressBar1);
		context = getActivity().getApplicationContext();
		db = new EventSQL(context);
		EventImageloder();
		expand = (ExpandableListView) V.findViewById(R.id.Expandlist);
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {
			GetContacts Ldata = new GetContacts();
			context.deleteDatabase("EventDB");
			Ldata.execute();

		} else {

			Toast.makeText(getActivity(),
					"Please Connect To Internet Connection", Toast.LENGTH_SHORT)
					.show();
			ArrayList<Event> sam = new ArrayList<Event>();
			sam = db.getAllEvent();
			Event sagar = null;

			for (int i = 0; i < sam.size(); i++) {
				String m1, m2, m3, m4, m5;
				sagar = new Event();
				sagar = sam.get(i);
				m1 = sagar.getTitle();
				m2 = sagar.getDes();
				m3 = sagar.getImage_url();
				m4 = sagar.getvenue();
				m5 = sagar.getDate();
				TitleS.add(m1);
				Ldesc.add(m2);
				ImageLink.add(m3);
				DateS.add(m5);
				VenueS.add(m4);
				Log.e("tag", sagar.toString());
			}
			adp = new EventAdapter(getActivity(), options, TitleS, DateS,
					VenueS, Ldesc, ImageLink);
			expand.setAdapter(adp);
		}

		return V;

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = ProgressDialog.show(getActivity(),
					"Fetching Latest News", "Wait ........", true);
			//Pevent.setVisibility(View.VISIBLE);

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			ServiceHandler sh = new ServiceHandler();

			String jsonStr = sh.makeServiceCall(urljson, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					Latest = jsonObj.getJSONArray(TAG_Latest);
					Event ser = null;
					for (int i = 0; i < Latest.length(); i++) {
						JSONObject c = Latest.getJSONObject(i);

						
						ser = new Event();
						ser.setTitle(c.getString(TAG_TITLE));
						ser.setDes(c.getString(TAG_Description));
						ser.setImage_url(c.getString(TAG_ImageLink));
						ser.setvenue(c.getString(TAG_Venue));
						ser.setDate(c.getString(TAG_Date));
						db.addService(ser);
					
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progress.dismiss();
			//Pevent.setVisibility(View.GONE);
			ArrayList<Event> sam = new ArrayList<Event>();
			Log.e("tag", "working");
			sam = db.getAllEvent();
			Event sagar = null;

			for (int i = 0; i < sam.size(); i++) {
				String m1, m2, m3, m4, m5;
				sagar = new Event();
				sagar = sam.get(i);
				m1 = sagar.getTitle();
				m2 = sagar.getDes();
				m3 = sagar.getImage_url();
				m4 = sagar.getvenue();
				m5 = sagar.getDate();
				TitleS.add(m1);
				Ldesc.add(m2);
				ImageLink.add(m3);
				DateS.add(m5);
				VenueS.add(m4);
				Log.e("tag", sagar.toString());
			}

			adp = new EventAdapter(getActivity(), options, TitleS, DateS,
					VenueS, Ldesc, ImageLink);
			expand.setAdapter(adp);
		}

	}

	public void EventImageloder() {

		DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getActivity().getApplicationContext())
				.defaultDisplayImageOptions(displayimageOptions).build();
		ImageLoader.getInstance().init(config);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.image1)
				.showImageForEmptyUri(R.drawable.image1)
				.showImageOnFail(R.drawable.image1).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			// Toast.makeText(getApplicationContext(), "back press",
			// Toast.LENGTH_LONG).show();

			return false;
		return false;
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onOptionsMenuClosed(menu);
	}

}

class EventAdapter extends BaseExpandableListAdapter {
	Context context;
	Typeface type;

	protected ImageLoader imageloder = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions option;

	ArrayList<String> T = new ArrayList<String>();
	ArrayList<String> D = new ArrayList<String>();
	ArrayList<String> V = new ArrayList<String>();
	ArrayList<String> desT = new ArrayList<String>();
	ArrayList<String> Im_link = new ArrayList<String>();

	protected LayoutInflater inflater = null;

	public EventAdapter(Context context, DisplayImageOptions options,
			ArrayList<String> titleS, ArrayList<String> dateS,
			ArrayList<String> venueS, ArrayList<String> ldesc,
			ArrayList<String> imageLink) {
		// TODO Auto-generated constructor stub
		T = titleS;
		D = dateS;
		desT = ldesc;
		V = venueS;
		Im_link = imageLink;

		this.context = context;
		option = options;

	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return T.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
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

	public class Holder {
		TextView tv, tv1;
		ImageView img;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.latestlistitem, null);
		}
		ImageView im = (ImageView) convertView.findViewById(R.id.latestimg);
		TextView tv = (TextView) convertView.findViewById(R.id.latestheading);
		TextView date = (TextView) convertView.findViewById(R.id.latestDate);
		TextView venue = (TextView) convertView.findViewById(R.id.latestvenue);
		imageloder.displayImage(Im_link.get(groupPosition), im, option,
				animateFirstListener);

		tv.setText(T.get(groupPosition));
		date.setText(D.get(groupPosition));
		venue.setText(V.get(groupPosition));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.childlist, null);
		}
		final TextView des = (TextView) convertView
				.findViewById(R.id.textViewChild);
		des.setText(desT.get(groupPosition));

		return convertView;

	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	public static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
