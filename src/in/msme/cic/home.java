package in.msme.cic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class home extends Fragment{
	
	
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
		return inflater.inflate(R.layout.home, container, false);
	}
}