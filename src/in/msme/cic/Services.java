package in.msme.cic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Services extends Fragment {

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
	public static String[] prgmNameList = { "CREDIT FACILITATION CENTRE",
			"I TREE", "SITI CENTRE", "ENERGY EFFICIENCY", "INNOVATION CLUSTER" };

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

		DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();

		// Create global configuration and initialize ImageLoader with this
		// configuration
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

		listservice = (ListView) V.findViewById(R.id.servicelist);
		web = (WebView) V.findViewById(R.id.webView1);
		adapter = new serviceadapter(getActivity(), prgmNameList, options);
		listservice.setAdapter(adapter);
		urlf.add(url[0]);
		urlf.add(url[1]);
		urlf.add(url[2]);
		urlf.add(url[3]);
		urlf.add(url[4]);
		listservice.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg) {

				listservice.setVisibility(View.GONE);

				web.setVisibility(View.VISIBLE);
				bac.setVisibility(View.VISIBLE);
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

				SharedPreferences sharedPref = getActivity().getPreferences(
						Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putString("url_link", url[position]);
				editor.commit();

			}
		});
		
		bac = (Button) V.findViewById(R.id.back);
		bac.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listservice.setVisibility(View.VISIBLE);
				web.setVisibility(View.GONE);
				bac.setVisibility(View.INVISIBLE);
			}
		});

		return V;

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

	public serviceadapter(Context services, String[] prgmNameList,
			DisplayImageOptions options) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		context = services;
		option = options;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public static final String[] IMAGES = new String[] {
			// Heavy images
			"http://iamsmeofindia.com/sites/default/files/SIDBI_FSIA-pic1.jpg",
			"http://iamsmeofindia.com/sites/default/files/itree_page.jpg",
			"http://iamsmeofindia.com/sites/default/files/siti-logo.png",
			"http://iamsmeofindia.com/sites/default/files/energy.jpg",
			"http://iamsmeofindia.com/sites/default/files/inovation.jpg", };

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
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
		holder.tv1.setText(subprgm[position]);
		holder.img = (ImageView) rowView.findViewById(R.id.serviceimg);
		holder.tv.setHeight(100);
		holder.tv.setMinimumHeight(100);
		holder.tv.setText(result[position]);
		imageloder.displayImage(IMAGES[position], holder.img, option,
				animateFirstListener);
		// holder.img.setImageResource(imageId[position]);
		/*
		 * rowView.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Toast.makeText(context, "You Clicked " + result[position],
		 * Toast.LENGTH_LONG).show(); } });
		 */
		return rowView;
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
