package in.msme.cic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Services extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public static Services newInstance(int sectionNumber){
		Services fragment = new Services();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;		
	}
		public Services() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View V = inflater.inflate(R.layout.fragment_services, container, false);
			return V;
		}
	}