package in.msme.cic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Latest extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	Context context;
	static DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	ProgressDialog progress;
	// URL to get contacts JSON
	LatestAdapter adp;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	private static String urljson = "http://pa1pal.tk/LE.json";

	// JSON Node names
	private static final String TAG_Latest = "Latest";
	private static final String TAG_TITLE = "Title";
	private static final String TAG_Date = "Date";
	private static final String TAG_Venue = "Venue";
	private static final String TAG_Description = "Description";
	private static final String TAG_ImageLink = "link";

	// contacts JSONArray
	JSONArray Latest = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;
	ArrayList<String> TitleS = new ArrayList<String>();
	ArrayList<String> DateS = new ArrayList<String>();
	ArrayList<String> VenueS = new ArrayList<String>();
	ArrayList<String> Ldesc = new ArrayList<String>();
	ArrayList<String> ImageLink = new ArrayList<String>();

	ExpandableListView expand;

	ArrayList<String> urlf = new ArrayList<String>();

	public static Latest newInstance(int sectionNumber) {
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
		View V = inflater.inflate(R.layout.fragment_latest, container, false);
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		if(isInternetPresent){
			 V = inflater.inflate(R.layout.fragment_latest, container, false);
		}else{
			V = inflater.inflate(R.layout.no_inetrnet, container, false);
		}
		
		expand = (ExpandableListView) V.findViewById(R.id.Expandlist);
		
		contactList = new ArrayList<HashMap<String, String>>();
		

		if (isInternetPresent) {
			GetContacts Ldata = new GetContacts();
			Ldata.execute();

		} else {
			
			
			
//			ImageView noInternet = (ImageView)V.findViewById(R.id.Nointernet);
//			noInternet.setVisibility(View.VISIBLE);
//			noInternet.setImageDrawable(getResources().getDrawable(R.drawable.no_internet));
//			Button ref = (Button)V.findViewById(R.id.ref);
//			ref.setVisibility(View.GONE);
//			Toast.makeText(getActivity(), "Please Connect To Internet Connection", Toast.LENGTH_LONG).show();
			
		}

		return V;

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = ProgressDialog.show(getActivity(),
					"Fetching Latest News", "Wait ........", true);
			progress.setCancelable(true);
			progress.setOnCancelListener(new Dialog.OnCancelListener() {

			    @Override
			    public void onCancel(DialogInterface dialog) {
			        Toast.makeText(getActivity(), "Please Wait While Fetching data", Toast.LENGTH_SHORT).show();
			    }
			});

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

					for (int i = 0; i < Latest.length(); i++) {
						JSONObject c = Latest.getJSONObject(i);

						String title = c.getString(TAG_TITLE);
						String date = c.getString(TAG_Date);
						String link = c.getString(TAG_Venue);
						String description = c.getString(TAG_Description);
						String imagelink = c.getString(TAG_ImageLink);

						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();
						TitleS.add(title);
						DateS.add(date);
						VenueS.add(link);
						Ldesc.add(description);
						ImageLink.add(imagelink);
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
			adp = new LatestAdapter(getActivity(), options, TitleS, DateS,
					VenueS, Ldesc, ImageLink);
			expand.setAdapter(adp);
		}

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

class LatestAdapter extends BaseExpandableListAdapter {
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

	public LatestAdapter(Context context, DisplayImageOptions options,
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
		return 3;
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
