package in.msme.cic;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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
public class Latest extends SherlockFragment {

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
	public static int[] prgmImages = { R.drawable.service1,
			R.drawable.service2, R.drawable.service3, R.drawable.service4,
			R.drawable.service5 };
	public static String[] prgmNameList = { "CREDIT FACILITATION CENTRE",
			"I TREE", "SITI CENTRE", "ENERGY EFFICIENCY", "INNOVATION CLUSTER" };

	serviceadapter adapter;

	public static Latest newInstance(int sectionNumber) {
		Latest fragment = new Latest();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Latest() {
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_latest, container, false);
		listservice = (ListView) V.findViewById(R.id.servicelist);
		web = (WebView) V.findViewById(R.id.webView1);
		adapter = new serviceadapter(getActivity(), prgmNameList, prgmImages);
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
				// Intent intent = new Intent(getActivity(), Webview.class);
				// intent.putStringArrayListExtra("weburl", urlf);
				// startActivity(intent);
				/*
				 * String url = "http://www.google.com"; Intent i = new
				 * Intent(Intent.ACTION_VIEW); i.setData(Uri.parse(url));
				 * startActivity(i);
				 */

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
class latestadapter extends BaseAdapter {
	String[] result;
	Context context;
	int[] imageId;
	private static LayoutInflater inflater = null;

	public static String[] subprgm = {
			"A Memorandum of understanding \n (MoU) was signed between SIDBI \n and FSIA on 20th december 2008",
			"iTree is innovative step towards skill \n  development wherein we fill skillgao \n  between the existing acadimic setup and industry",
			"SITI centre is step in helping the MSME'S Identify \n new business opportunities , create efficencies, \n new products development , identify ne markets through innovation and know how ",
			"iamsmeofindia  as a nodal agency is \n working towards making SME'S Energy efficient",
			"National Innovation Council (NInC) has selected IAMSME \n of India to implement the Innovation \n Cluster for Auto Components in Faridabad." };

	public latestadapter(Context services, String[] prgmNameList,
			int[] prgmImages) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		context = services;
		imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

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
		holder.img.setImageResource(imageId[position]);
		/*
		 * rowView.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Toast.makeText(context, "You Clicked " + result[position],
		 * Toast.LENGTH_LONG).show(); } });
		 */
		return rowView;
	}
}
