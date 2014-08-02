package in.msme.cic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import in.msme.cic.ImageLoader.*;

public class Downloads extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	Context context;

	public static Downloads newInstance(int sectionNumber) {
		Downloads fragment = new Downloads();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Downloads() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater
				.inflate(R.layout.fragment_downloads, container, false);
		ListView left = (ListView) V.findViewById(R.id.left_list);
		ListView right = (ListView) V.findViewById(R.id.right_list);
		adapter Adapterr = new adapter(context, getActivity());
		left.setAdapter(Adapterr);
		right.setAdapter(Adapterr);
		return V;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getActivity().getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.Refresh:

			return true;

		}

		return super.onOptionsItemSelected(item);
	}

}

@SuppressLint("ViewHolder")
class adapter extends BaseAdapter {

	private Activity activity;

	private static LayoutInflater inflater = null;
	ImageLoader imageLoader;
	float imageWidth;
	Context context;
	private String[] mStrings = {
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",

	};

	public adapter(Context context, Activity a) {
		activity = a;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Create ImageLoader object to download and show image in list
		// Call ImageLoader constructor to initialize FileCache
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mStrings.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public class Holder {
		ImageView image;

	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.downloaditem, null);
		Holder holder = new Holder();
		holder.image = (ImageView) v.findViewById(R.id.imageitem);
		v.setTag(holder);

		// v.setMinimumHeight(500);
		ImageView image = holder.image;

		imageLoader.DisplayImage(mStrings[position], image);

		return v;
	}

}
