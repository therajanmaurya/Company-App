package in.msme.cic;

import in.msme.cic.database.Gallerysql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Gallery extends Fragment {

	Context context;
	static DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	Gallerysql db;
	ListView right, left;
	GalleryAdapter gallery;
	Button refresh;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	private static final String ARG_SECTION_NUMBER = "section_number";
	ProgressDialog progress;
	private static String urljson = "http://pa1pal.tk/Gallery.json";
	private static final String TAG_Latest = "Gallery";
	private static final String TAG_TITLE = "Image";
	ProgressBar pro;
	JSONArray Latest = null;
	ArrayList<String> image = new ArrayList<String>();
    ArrayList<String> urlf = new ArrayList<String>();

	public static Gallery newInstance(int sectionNumber) {
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
		context =getActivity().getApplicationContext();
		View V = inflater.inflate(R.layout.fragment_gallery, container, false);	
		Button refresh = (Button) V.findViewById(R.id.ref);
		ListView left = (ListView) V.findViewById(R.id.left_list);
		ListView right = (ListView) V.findViewById(R.id.right_list);
	    imageloder();
	    db=new Gallerysql(context);
	    
	    ArrayList<String> sam = new ArrayList<String>();
		Log.e("tag", "working");
		sam = db.getAllSevices();
		

		for (int i = 0; i < sam.size(); i++) {
			String m1;
			
			m1 = sam.get(i);
			
			image.add(m1);
			Log.e("tag", m1);
		}
	    gallery = new GalleryAdapter(context, options, getActivity(), image);
	    
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {
			GetContacts Ldata = new GetContacts();
			context.deleteDatabase("GalleryDB");
			Ldata.execute();
			left.setAdapter(gallery);
			right.setAdapter(gallery);
			


		} else {

			Toast.makeText(getActivity(),
					"Please Connect To Internet Connection", Toast.LENGTH_LONG)
					.show();
			left.setAdapter(gallery);
			right.setAdapter(gallery);

		}

		refresh.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				if (isInternetPresent) {
					imageLoader.clearMemoryCache();
					imageLoader.clearDiscCache();
					gallery.notifyDataSetChanged();
				} else {

					Toast.makeText(getActivity(), "First Connect to Internet",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		return V;

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress = ProgressDialog.show(getActivity(), "Fetching Gallery",
					"Please Wait.........", true);

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

						String image_link = c.getString(TAG_TITLE);
						// tmp hashmap for single contact
						db.addService(image_link);
//						HashMap<String, String> contact = new HashMap<String, String>();
//						image.add(image_link);
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

		}

	}

	public void imageloder() {

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

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.Refresh:

			return true;

		}

		return super.onOptionsItemSelected(item);
	}
}

@SuppressLint("ViewHolder")
class GalleryAdapter extends BaseAdapter {

	private Activity activity;

	private static LayoutInflater inflater = null;

	float imageWidth;
	Context context;
	@SuppressLint("NewApi")
	protected ImageLoader imageloder = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions option;
	ArrayList<String> Img_link = new ArrayList<String>();
	

	public GalleryAdapter(Context context, DisplayImageOptions options,
			Activity a, ArrayList<String> image) {
		activity = a;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		option = options;
		Img_link = image;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Img_link.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public class Holder {
		ImageView image;

	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.downloaditem, null);
		Holder holder = new Holder();
		int[] no = { 250, 200, 238, 265, 280 };
		holder.image = (ImageView) v.findViewById(R.id.imageitem);
		holder.image.setMaxHeight(1000);
		v.setTag(holder);
		int rnd = new Random().nextInt(no.length);
		int number = no[rnd];
		if (position == 0) {
			holder.image.getLayoutParams().height = 400;
		} else {
			holder.image.getLayoutParams().height = number;
		}

		imageloder.displayImage(Img_link.get(position), holder.image, option,
				animateFirstListener);

		return v;
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
