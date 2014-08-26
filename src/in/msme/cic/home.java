package in.msme.cic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class home extends Main{
	
	public static final String TAG = home.class
			.getSimpleName();
	private static final String ARG_SECTION_NUMBER = "section position";
	@SuppressLint("ValidFragment")
	public static home newInstance(int sectionNumber){
		home fragment = new home();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;
		
	}

	public home() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View V = inflater.inflate(R.layout.home, container, false);
		
		PageSlidingTabStripFragment firstFragment1 = new PageSlidingTabStripFragment();
		firstFragment1.setArguments(getActivity().getIntent().getExtras());
		getFragmentManager().beginTransaction()
				.replace(R.id.fragmentfirst, firstFragment1).commit();
		
		Second firstFragment2 = new Second();
		firstFragment2.setArguments(getActivity().getIntent().getExtras());
		getFragmentManager().beginTransaction()
				.replace(R.id.fragmentsecond, firstFragment2).commit();
		
		Third firstFragment3 = new Third();
		firstFragment3.setArguments(getActivity().getIntent().getExtras());
		getFragmentManager().beginTransaction()
				.replace(R.id.fragmentthird, firstFragment3).commit();
		
		First firstFragment4 = new First();
		firstFragment4.setArguments(getActivity().getIntent().getExtras());
		getFragmentManager().beginTransaction()
				.replace(R.id.fragmentforth, firstFragment4).commit();
		
		
		
		
		return V;
	}
}