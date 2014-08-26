package in.msme.cic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class Downloads extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	Context context;
	static DisplayImageOptions options;

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
		DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();

		// Create global configuration and initialize ImageLoader with this
		// configuration
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getActivity().getApplicationContext())
				.defaultDisplayImageOptions(displayimageOptions).build();
		ImageLoader.getInstance().init(config);
		Button refresh = (Button) V.findViewById(R.id.ref);
		ListView left = (ListView) V.findViewById(R.id.left_list);
		ListView right = (ListView) V.findViewById(R.id.right_list);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.image1)
				.showImageForEmptyUri(R.drawable.image2)
				.showImageOnFail(R.drawable.image3).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();
		final adapter Adapterr = new adapter(context, options, getActivity());
		left.setAdapter(Adapterr);
		right.setAdapter(Adapterr);

		refresh.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Adapterr.imageLoader.clearCache();
				Adapterr.notifyDataSetChanged();
			}
		});

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

	float imageWidth;
	Context context;
	@SuppressLint("NewApi")
	protected ImageLoader imageloder = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions option;
	public static final String[] IMAGES = new String[] {
			// Heavy images
			"http://iamsmeofindia.com/sites/default/files/3_2.jpg",
			"http://iamsmeofindia.com/sites/default/files/4_2.jpg",
			"http://iamsmeofindia.com/sites/default/files/2_2.jpg",
			"http://iamsmeofindia.com/sites/default/files/1_2.jpg",
			"http://iamsmeofindia.com/sites/default/files/13_0.jpg",
			"http://iamsmeofindia.com/sites/default/files/11_2.jpg",
			"http://iamsmeofindia.com/sites/default/files/14_0.jpg",
			"http://iamsmeofindia.com/sites/default/files/12_0.jpg",
			"http://www.frenchrevolutionfood.com/wp-content/uploads/2009/04/Twitter-Bird.png",
			"http://3.bp.blogspot.com/-ka5MiRGJ_S4/TdD9OoF6bmI/AAAAAAAAE8k/7ydKtptUtSg/s1600/Google_Sky%2BMaps_Android.png",
			"http://www.desiredsoft.com/images/icon_webhosting.png",
			"http://goodereader.com/apps/wp-content/uploads/downloads/thumbnails/2012/01/hi-256-0-99dda8c730196ab93c67f0659d5b8489abdeb977.png",
			"http://1.bp.blogspot.com/-mlaJ4p_3rBU/TdD9OWxN8II/AAAAAAAAE8U/xyynWwr3_4Q/s1600/antivitus_free.png",
			"http://cdn3.iconfinder.com/data/icons/transformers/computer.png",
			"http://cdn.geekwire.com/wp-content/uploads/2011/04/firefox.png?7794fe",
			"https://ssl.gstatic.com/android/market/com.rovio.angrybirdsseasons/hi-256-9-347dae230614238a639d21508ae492302340b2ba",
			"http://androidblaze.com/wp-content/uploads/2011/12/tablet-pc-256x256.jpg",
			"http://www.theblaze.com/wp-content/uploads/2011/08/Apple.png",
			"http://1.bp.blogspot.com/-y-HQwQ4Kuu0/TdD9_iKIY7I/AAAAAAAAE88/3G4xiclDZD0/s1600/Twitter_Android.png",
			"http://3.bp.blogspot.com/-nAf4IMJGpc8/TdD9OGNUHHI/AAAAAAAAE8E/VM9yU_lIgZ4/s1600/Adobe%2BReader_Android.png",
			"http://cdn.geekwire.com/wp-content/uploads/2011/05/oovoo-android.png?7794fe",
			"http://icons.iconarchive.com/icons/kocco/ndroid/128/android-market-2-icon.png",
			"http://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png",
			"http://c.wrzuta.pl/wm16596/a32f1a47002ab3a949afeb4f",
			"http://macprovid.vo.llnwd.net/o43/hub/media/1090/6882/01_headline_Muse.jpg",

	};

	public adapter(Context context, DisplayImageOptions options, Activity a) {
		activity = a;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		option = options;
		// Create ImageLoader object to download and show image in list
		// Call ImageLoader constructor to initialize FileCache

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return IMAGES.length;
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
		int[] no = { 250, 200, 238, 265,280 };
		holder.image = (ImageView) v.findViewById(R.id.imageitem);
		holder.image.setMaxHeight(1000);
		v.setTag(holder);
		int rnd = new Random().nextInt(no.length);
		int number = no[rnd];
		if (position == 0) {
			holder.image.getLayoutParams().height = 400;
		}else {
			holder.image.getLayoutParams().height = number;
		}

		ImageView image = holder.image;
		image.setMaxHeight(500);

		imageloder.displayImage(IMAGES[position], holder.image, option,
				animateFirstListener);

		return v;
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
