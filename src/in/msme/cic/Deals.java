package in.msme.cic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Deals extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static Deals newInstance(int sectionNumber){
		Deals fragment = new Deals();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
	
	public Deals() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_deals, container, false);
		return V;
	}
}
