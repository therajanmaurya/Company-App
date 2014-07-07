package in.msme.cic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled") public class Webview extends Fragment {

	WebView web;

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static Webview newInstance(int sectionNumber) {
		Webview fragment = new Webview();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Webview() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.webview, container, false);

		web = (WebView) V.findViewById(R.id.website);
        web.getSettings().setJavaScriptEnabled(true);		
		web.loadUrl("www.google.com");

		return V;
	}
}