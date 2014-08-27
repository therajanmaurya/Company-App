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

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Second extends Fragment {
	EventSQL db;
	private static final String ARG_SECTION_NUMBER = "section_number";
	Context context;
	ProgressBar progr;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	static DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	ProgressDialog progress;
	EventAdapter adp;
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
	ImageView image, image1;
	ArrayList<String> TitleS = new ArrayList<String>();
	ArrayList<String> DateS = new ArrayList<String>();
	ArrayList<String> VenueS = new ArrayList<String>();
	ArrayList<String> Ldesc = new ArrayList<String>();
	ArrayList<String> ImageLink = new ArrayList<String>();

	ExpandableListView expand;

	ArrayList<String> urlf = new ArrayList<String>();

	public static Second newInstance(int sectionNumber) {
		Second fragment = new Second();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Second() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.second, container, false);
		context = getActivity().getApplicationContext();
	    progr = (ProgressBar)V.findViewById(R.id.progressBar1);
	    progr.setVisibility(View.GONE);
		db = new EventSQL(context);
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

		// expand = (ExpandableListView) V.findViewById(R.id.Expandlist);
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		image = (ImageView) V.findViewById(R.id.SImage);
		image1 = (ImageView) V.findViewById(R.id.SImage2);

		image.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Events firstFragment2 = new Events();
				firstFragment2.setArguments(getActivity().getIntent()
						.getExtras());
				getFragmentManager().beginTransaction()
						.replace(R.id.content, firstFragment2).commit();

			}
		});

		image1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Events firstFragment2 = new Events();
				firstFragment2.setArguments(getActivity().getIntent()
						.getExtras());
				getFragmentManager().beginTransaction()
						.replace(R.id.content, firstFragment2).commit();

			}
		});

		if (isInternetPresent) {
			GetContacts Ldata = new GetContacts();
			context.deleteDatabase("EventDB");
			Ldata.execute();

		} else {

			Toast.makeText(getActivity(),
					"Please Connect To Internet Connection", Toast.LENGTH_LONG)
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
			imageLoader.displayImage(ImageLink.get(0), image, options,
					animateFirstListener);
			imageLoader.displayImage(ImageLink.get(1), image1, options,
					animateFirstListener);
		}

		return V;

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progr.setVisibility(View.VISIBLE);
//			progress = ProgressDialog.show(getActivity(),
//					"Fetching Latest News", "Wait ........", true);

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
			//progress.dismiss();
			progr.setVisibility(View.GONE);
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

			imageLoader.displayImage(ImageLink.get(0), image, options,
					animateFirstListener);
			imageLoader.displayImage(ImageLink.get(1), image1, options,
					animateFirstListener);

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
