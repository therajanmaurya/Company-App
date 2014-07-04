package in.msme.cic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Events extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static Events newInstance(int sectionNumber){
		Events fragment = new Events();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	public Events() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_events, container, false);
		return V;
	}
}