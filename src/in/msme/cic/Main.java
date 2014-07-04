package in.msme.cic;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.DocumentsContract.Document;
import android.renderscript.Element;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class Main extends Fragment {
    
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	@SuppressLint("ValidFragment")
	public static Main newInstance(int sectionNumber) {
		Main fragment = new Main();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;

	}

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View V = inflater.inflate(R.layout.facebook, container, false);

		WebView cardapio = (WebView) V.findViewById(R.id.webView1);
		cardapio.getSettings().setJavaScriptEnabled(true);
		cardapio.loadUrl("https://www.facebook.com");
		return V;

	}
}
