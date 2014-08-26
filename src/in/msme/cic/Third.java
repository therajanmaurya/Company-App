package in.msme.cic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Third extends Fragment {
    
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	@SuppressLint("ValidFragment")
	public static Third newInstance(int sectionNumber) {
		Third fragment = new Third();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;

	}

	public Third() {
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View V = inflater.inflate(R.layout.third, container, false);
		return V;

	}
}
