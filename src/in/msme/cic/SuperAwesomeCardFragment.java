package in.msme.cic;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragment;
import in.msme.cic.R;

public class SuperAwesomeCardFragment extends SherlockFragment{

	private static final String ARG_POSITION = "position";

	private int position;
    int [] flag  = new int[]{R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4}; 
	

	public static SuperAwesomeCardFragment newInstance(int position) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);

		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
				.getDisplayMetrics());

		//TextView v = new TextView(getActivity());
		ImageView im = new ImageView(getActivity()); 
		params.setMargins(margin, margin, margin, margin);
		im.setLayoutParams(params);
		im.setLayoutParams(params);
		//v.setGravity(Gravity.CENTER);
	   //im.setBackgroundResource(R.drawable.image1);
	   //im.setBackgroundResource(R.drawable.image2);
	   //im.setBackgroundResource(R.drawable.image3);
	   im.setImageResource(flag[position]);
	   //im.setBackgroundResource(R.drawable.venus);
		//v.setText("CARD " + (position + 1));

		fl.addView(im);
		return fl;
	}

}