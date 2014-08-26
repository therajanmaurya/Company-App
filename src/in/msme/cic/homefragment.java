package in.msme.cic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class homefragment extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static homefragment newInstance(int sectionNumber){
		homefragment fragment = new homefragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	
	public homefragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.homefragment, container, false);
		return V;
	}
}

