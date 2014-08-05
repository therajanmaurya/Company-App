package in.msme.cic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Connect extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

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
        WebView web = (WebView)V.findViewById(R.id.web_connnect);
        web.setVisibility(View.VISIBLE);
		WebSettings webSettings = web.getSettings();
		webSettings.setJavaScriptEnabled(true);		
		web.loadUrl("https://m.facebook.com/IamSMEofIndia?fref=ts");
		web.getSettings().setLoadWithOverviewMode(true);
		web.getSettings().setAppCacheEnabled(false);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
	    web.getSettings().setUseWideViewPort(true);
	    web.getSettings().setBuiltInZoomControls(true);
		web.setWebViewClient(new WebViewClient(){
			@Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url){
		      view.loadUrl(url);
		      return true;
		    }
		});
		//Intent facebookIntent = getOpenFacebookIntent(getActivity());
		//startActivity(facebookIntent);
		
		return V;
	}

	public static Intent getOpenFacebookIntent(Context context) {

		try {
			context.getPackageManager()
					.getPackageInfo("com.facebook.katana", 0); // Checks if FB
																// is even
																// installed.
			return new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://www.facebook.com/therajanmaurya")); // Trys to make
																// intent with
																// FB's URI
		} catch (Exception e) {
			return new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://www.facebook.com/IamSMEofIndia?fref=ts")); // catches
																		// and
																		// opens
																		// a url
																		// to
																		// the
																		// desired
																		// page
		}
	}
}
