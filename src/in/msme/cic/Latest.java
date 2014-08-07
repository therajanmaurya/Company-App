package in.msme.cic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Latest extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	public static final Integer[] images = { R.drawable.image1,
			R.drawable.image2, R.drawable.image3, R.drawable.image4

	};
	Context contaxt;
	static DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	public static Latest newInstance(int sectionNumber) {
		Latest fragment = new Latest();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Latest() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_latest, container, false);

		@SuppressWarnings("deprecation")
		DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();

		// Create global configuration and initialize ImageLoader with this
		// configuration
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getActivity().getApplicationContext())
				.defaultDisplayImageOptions(displayimageOptions).build();
		ImageLoader.getInstance().init(config);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.image1)
				.showImageForEmptyUri(R.drawable.image1)
				.showImageOnFail(R.drawable.image1).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		ExpandableListView expand = (ExpandableListView) V
				.findViewById(R.id.Expandlist);
		myAdapter adp = new myAdapter(getActivity(), options);
		expand.setAdapter(adp);

		return V;

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			// Toast.makeText(getApplicationContext(), "back press",
			// Toast.LENGTH_LONG).show();

			return false;
		return false;
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onOptionsMenuClosed(menu);
	}

}

class myAdapter extends BaseExpandableListAdapter {
	Context context;
	Typeface type;

	protected ImageLoader imageloder = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions option;

	protected LayoutInflater inflater = null;
	static int[] image = { R.drawable.image1, R.drawable.image2,
			R.drawable.image3, R.drawable.image4 };
	static String[] parentlist = {
			"Pai, Bala, Prahlad Ask Infosys to Buy Back Rs 11,000-Crore Shares",
			"ViewPDF", "TODO List", "Contacts" };
	static String[][] childlist = {
			{ "Bangalore: Former Infosys top executives TV Mohandas Pai,V Balakrishnan and D N Prahlad have asked India's second largest software services firm to buy back shares worth Rs.11,200 crore, saying it will help check the asymmetry of information between management and investors.In a letter to Infosys board, the three former executives said there is a need to announce a large and consistent buyback programme to show confidence in the management and the business model " },
			{ "ViewPDF list \n 1.Adobe Reader \n 2. PDF Reader \n 3.All PDF" },
			{ "TODO list" }, { "Contacts list" } };

	public myAdapter(Context context, DisplayImageOptions options) {
		// TODO Auto-generated constructor stub
		this.context = context;
		option = options;

	}

	public static final String[] IMAGES = new String[] {
			// Heavy images
			"http://i.ndtvimg.com/i/2014-04/infosys_295x200_81397539806.jpg",
			"http://iamsmeofindia.com/sites/default/files/itree_page.jpg",
			"http://iamsmeofindia.com/sites/default/files/siti-logo.png",
			"http://iamsmeofindia.com/sites/default/files/energy.jpg",
			"http://iamsmeofindia.com/sites/default/files/inovation.jpg", };

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return parentlist.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childlist[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public class Holder {
		TextView tv, tv1;
		ImageView img;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.latestlistitem, null);
		}
		ImageView im = (ImageView) convertView.findViewById(R.id.latestimg);
		TextView tv = (TextView) convertView.findViewById(R.id.latestheading);
		imageloder.displayImage(IMAGES[groupPosition], im, option,
				animateFirstListener);
		tv.setText(parentlist[groupPosition]);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.childlist, null);
		}

		Button more = (Button) convertView.findViewById(R.id.more);
		final TextView des = (TextView) convertView
				.findViewById(R.id.textViewChild);
		des.setText(childlist[groupPosition][childPosition]);
		des.setTextSize(20);
		more.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				des.setTextSize(40);
			}
		});
		return convertView;

	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	public static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
