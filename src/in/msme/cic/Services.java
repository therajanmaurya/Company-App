package in.msme.cic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Services extends Fragment {

	MySQLiteHelper db;
	ProgressDialog progress;
	// URL to get contacts JSO
	private static String urljson = "http://pa1pal.tk/Services";
    ProgressBar proBar;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
    ImageView no ;
	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_TITLE = "Title";
	private static final String TAG_Description = "Desc";
	private static final String TAG_link = "link";

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;
	ArrayList<Service> services = new ArrayList<Service>();
	ArrayList<String> TitleS = new ArrayList<String>();
	ArrayList<String> DescS = new ArrayList<String>();
	ArrayList<String> linkS = new ArrayList<String>();
	String[] ti = null;

	ArrayList<String> urlf = new ArrayList<String>();
	String[] url = {
			"http://iamsmeofindia.com/services/credit-facilitation-centre",
			"http://iamsmeofindia.com/services/i-tree",
			"http://iamsmeofindia.com/siti-centre",
			"http://iamsmeofindia.com/services/energy-efficiency",
			"http://iamsmeofindia.com/services/innovation-cluster" };

	ListView listservice;
	WebView web;
	Context context;
	Button bac;
	private static final String ARG_SECTION_NUMBER = "section_number";

	static DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	serviceadapter adapter;

	public static Services newInstance(int sectionNumber) {
		Services fragment = new Services();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Services() {
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_services, container, false);
		proBar = (ProgressBar)V.findViewById(R.id.PService);
		listservice = (ListView) V.findViewById(R.id.servicelist);
		@SuppressWarnings("deprecation")
	    
		DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getActivity().getApplicationContext())
				.defaultDisplayImageOptions(displayimageOptions).build();
		ImageLoader.getInstance().init(config);
		context = getActivity().getApplicationContext();
		db = new MySQLiteHelper(context);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.image1)
				.showImageForEmptyUri(R.drawable.image1)
				.showImageOnFail(R.drawable.image1).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {
			GetContacts data = new GetContacts();
			data.execute();

		} else {

			Toast.makeText(getActivity(),
					"Please Connect To Internet Connection", Toast.LENGTH_SHORT)
					.show();
			ArrayList<Service> sam = new ArrayList<Service>();
			Log.e("tag", "working");
			sam = db.getAllSevices();
			Service readdata = null;

			for (int i = 0; i < sam.size(); i++) {
				String m1, m2, m3;
				readdata = new Service();
				readdata = sam.get(i);
				m1 = readdata.getTitle();
				m2 = readdata.getDes();
				m3 = readdata.getImage_url();

				TitleS.add(m1);
				DescS.add(m2);
				linkS.add(m3);
				Log.e("tag", readdata.toString());
				adapter = new serviceadapter(getActivity(), options, TitleS,
						DescS, linkS);
				listservice.setAdapter(adapter);
			}

		}
        no  = (ImageView)V.findViewById(R.id.ima);
		web = (WebView) V.findViewById(R.id.webView1);
		urlf.add(url[0]);
		urlf.add(url[1]);
		urlf.add(url[2]);
		urlf.add(url[3]);
		urlf.add(url[4]);
		listservice.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg) {

				if(isInternetPresent){
					
					listservice.setVisibility(View.GONE);

					web.setVisibility(View.VISIBLE);
					//bac.setVisibility(View.VISIBLE);
					WebSettings webSettings = web.getSettings();
					webSettings.setJavaScriptEnabled(true);
					web.loadUrl(url[position]);
					web.getSettings().setLoadWithOverviewMode(true);
					web.getSettings().setAppCacheEnabled(false);
					webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
					web.getSettings().setCacheMode(position);
					web.getSettings().setUseWideViewPort(true);
					web.getSettings().setBuiltInZoomControls(true);
					web.setWebViewClient(new WebViewClient() {
						@Override
						public boolean shouldOverrideUrlLoading(WebView view,
								String url) {
							view.loadUrl(url);
							return true;
						}
					});
					
				}else {
					
					listservice.setVisibility(View.GONE);
					no.setVisibility(View.VISIBLE);
					
				}
				
				

			}
		});

//		bac = (Button) V.findViewById(R.id.back);
//		bac.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				listservice.setVisibility(View.VISIBLE);
//				web.setVisibility(View.GONE);
//				bac.setVisibility(View.INVISIBLE);
//			}
//		});

		return V;

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			proBar.setVisibility(View.VISIBLE);
//			progress = ProgressDialog.show(getActivity(), "Fetching Services",
//					"Wait ........", true);

		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(urljson, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					context.deleteDatabase("SevicesDB");
					contacts = jsonObj.getJSONArray(TAG_CONTACTS);
					Service ser = null;
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);
						ser = new Service();
						ser.setTitle(c.getString(TAG_TITLE));
						ser.setDes(c.getString(TAG_Description));
						ser.setImage_url(c.getString(TAG_link));
						db.addService(ser);
						// services.add(ser);
					}
					Log.e("where", "skdfnsdjkgnfjksdng");

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			proBar.setVisibility(View.GONE);
			//progress.dismiss();
			ArrayList<Service> sam = new ArrayList<Service>();
			Log.e("tag", "working");
			sam = db.getAllSevices();
			Service sagar = null;

			for (int i = 0; i < sam.size(); i++) {
				String m1, m2, m3;
				sagar = new Service();
				sagar = sam.get(i);
				m1 = sagar.getTitle();
				m2 = sagar.getDes();
				m3 = sagar.getImage_url();

				TitleS.add(m1);
				DescS.add(m2);
				linkS.add(m3);
				Log.e("tag", sagar.toString());
			}
			adapter = new serviceadapter(getActivity(), options, TitleS, DescS,
					linkS);
			listservice.setAdapter(adapter);
		}

	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onOptionsMenuClosed(menu);
	}

}

@SuppressLint({ "ViewHolder", "InflateParams" })
class serviceadapter extends BaseAdapter {
	String[] result;
	Context context;
	int[] imageId;
	ArrayList<String> TitleSe = new ArrayList<String>();
	ArrayList<String> DescSe = new ArrayList<String>();
	ArrayList<String> linkSe = new ArrayList<String>();
	private static LayoutInflater inflater = null;

	protected ImageLoader imageloder = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions option;
	public static String[] subprgm = {
			"A Memorandum of understanding \n (MoU) was signed between SIDBI \n and FSIA on 20th december 2008",
			"iTree is innovative step towards skill \n  development wherein we fill skillgao \n  between the existing acadimic setup and industry",
			"SITI centre is step in helping the MSME'S Identify \n new business opportunities , create efficencies, \n new products development , identify ne markets through innovation and know how ",
			"iamsmeofindia  as a nodal agency is \n working towards making SME'S Energy efficient",
			"National Innovation Council (NInC) has selected IAMSME \n of India to implement the Innovation \n Cluster for Auto Components in Faridabad." };

	public serviceadapter(Context services, DisplayImageOptions options,
			ArrayList<String> titleS, ArrayList<String> descS,
			ArrayList<String> linkS) {
		// TODO Auto-generated constructor stub
		context = services;
		option = options;
		TitleSe = titleS;
		DescSe = descS;
		linkSe = linkS;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TitleSe.size();
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
		TextView tv, tv1;
		ImageView img;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.servicelistitem, null);

		holder.tv = (TextView) rowView.findViewById(R.id.heading);
		holder.tv1 = (TextView) rowView.findViewById(R.id.subhead);
		holder.tv1.setHeight(50);
		holder.tv1.setMinimumHeight(50);

		holder.img = (ImageView) rowView.findViewById(R.id.serviceimg);
		holder.tv.setHeight(100);
		holder.tv.setMinimumHeight(100);

		holder.tv.setText(TitleSe.get(position));
		holder.tv1.setText(DescSe.get(position));

		imageloder.displayImage(linkSe.get(position), holder.img, option,
				animateFirstListener);

		return rowView;
	}

	// this is to get curved the image in all sides  
	
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
