package in.msme.cic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Connect extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
    ProgressBar progressBar;
    ConnectionDetector cd;
	Boolean isInternetPresent = false;
	public static Connect newInstance(int sectionNumber) {
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
		View V = inflater.inflate(R.layout.fragment_connect, container, false);
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		if(isInternetPresent){
			 V = inflater.inflate(R.layout.fragment_connect, container, false);
		}else{
			V = inflater.inflate(R.layout.no_inetrnet, container, false);
		}
        WebView web = (WebView)V.findViewById(R.id.web_connect);
        progressBar = (ProgressBar)V.findViewById(R.id.progressBar1);
        
		if (isInternetPresent) {
			web.setVisibility(View.VISIBLE);
			WebSettings webSettings = web.getSettings();
			web.setWebViewClient(new myWebClient());
			webSettings.setJavaScriptEnabled(true);		
			web.loadUrl("https://m.facebook.com/IamSMEofIndia?fref=ts");
			web.getSettings().setLoadWithOverviewMode(true);
			web.getSettings().setAppCacheEnabled(false);
			webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		    web.getSettings().setUseWideViewPort(true);
		    web.getSettings().setBuiltInZoomControls(true);

		} else {
//			progressBar.setVisibility(View.GONE);
//			//ImageView im = new ImageView(getActivity());
//			ImageView noInternet = (ImageView)V.findViewById(R.id.Nointernet);
//			noInternet.setVisibility(View.VISIBLE);
//			noInternet.setImageDrawable(getResources().getDrawable(R.drawable.no_internet));
//			Toast.makeText(getActivity(), "Please Connect To Internet Connection", Toast.LENGTH_LONG).show();
			
		}
        
		 
		 
		
		return V;
	}
	
	public class myWebClient extends WebViewClient
    {
    	@Override
    	public void onPageStarted(WebView view, String url, Bitmap favicon) {
    		// TODO Auto-generated method stub
    		super.onPageStarted(view, url, favicon);
    	}
    	
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url) {
    		// TODO Auto-generated method stub
    		
    		view.loadUrl(url);
    		return true;
    		
    	}
    	
    	@Override
    	public void onPageFinished(WebView view, String url) {
    		// TODO Auto-generated method stub
    		super.onPageFinished(view, url);
    		
    		progressBar.setVisibility(View.GONE);
    	}
    }
	
 
}
