package in.msme.cic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class Gallery extends Fragment {

	private ListView listViewLeft;
	private ListView listViewRight;
	private ItemsAdapter leftAdapter;
	private ItemsAdapter rightAdapter;

	int[] leftViewsHeights;
	int[] rightViewsHeights;

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static Gallery newInstance(int sectionNumber) {
		Gallery fragment = new Gallery();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Gallery() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_gallery, container, false);

		listViewLeft = (ListView) V.findViewById(R.id.list_view_left);
		listViewRight = (ListView) V.findViewById(R.id.list_view_right);

		loadItems();

		// listViewLeft.setOnTouchListener(touchListener);
		// listViewRight.setOnTouchListener(touchListener);
		// listViewLeft.setOnScrollListener(scrollListener);
		// listViewRight.setOnScrollListener(scrollListener);
		return V;
	}

	// Passing the touch event to the opposite list
	@SuppressLint("ClickableViewAccessibility")
	OnTouchListener touchListener = new OnTouchListener() {
		boolean dispatched = false;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v.equals(listViewLeft) && !dispatched) {
				dispatched = true;
				listViewRight.dispatchTouchEvent(event);
			} else if (v.equals(listViewRight) && !dispatched) {
				dispatched = true;
				listViewLeft.dispatchTouchEvent(event);
			}

			dispatched = false;
			return false;
		}
	};

	/**
	 * Synchronizing scrolling Distance from the top of the first visible
	 * element opposite list: sum_heights(opposite invisible screens) -
	 * sum_heights(invisible screens) + distance from top of the first visible
	 * child
	 */
	OnScrollListener scrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView v, int scrollState) {
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			if (view.getChildAt(0) != null) {
				if (view.equals(listViewLeft)) {
					leftViewsHeights[view.getFirstVisiblePosition()] = view
							.getChildAt(0).getHeight();

					int h = 0;
					for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
						h += rightViewsHeights[i];
					}

					int hi = 0;
					for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
						hi += leftViewsHeights[i];
					}

					int top = h - hi + view.getChildAt(0).getTop();
					listViewRight.setSelectionFromTop(
							listViewRight.getFirstVisiblePosition(), top);
				} else if (view.equals(listViewRight)) {
					rightViewsHeights[view.getFirstVisiblePosition()] = view
							.getChildAt(0).getHeight();

					int h = 0;
					for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
						h += leftViewsHeights[i];
					}

					int hi = 0;
					for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
						hi += rightViewsHeights[i];
					}

					int top = h - hi + view.getChildAt(0).getTop();
					listViewLeft.setSelectionFromTop(
							listViewLeft.getFirstVisiblePosition(), top);
				}

			}

		}
	};

	private void loadItems() {
		Integer[] leftItems = new Integer[] { R.drawable.ic_1, R.drawable.ic_2,
				R.drawable.ic_3, R.drawable.ic_4, R.drawable.ic_5 };
		Integer[] rightItems = new Integer[] { R.drawable.ic_6,
				R.drawable.ic_7, R.drawable.ic_8, R.drawable.ic_9,
				R.drawable.ic_10, R.drawable.ic_launcher, R.drawable.ic_1 };

		leftAdapter = new ItemsAdapter(getActivity(), R.layout.staggereditem,
				leftItems , mStrings);
		rightAdapter = new ItemsAdapter(getActivity(), R.layout.staggereditem,
				rightItems, mStrings );
		listViewLeft.setAdapter(leftAdapter);
		listViewRight.setAdapter(rightAdapter);

		leftViewsHeights = new int[leftItems.length];
		rightViewsHeights = new int[rightItems.length];

	}

	private String[] mStrings = {
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image7.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image8.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image9.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image10.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image7.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image8.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image9.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image10.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image1.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image2.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image3.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image5.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image6.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image7.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image8.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image9.png",
			"http://androidexample.com/media/webservice/LazyListView_images/image10.png"

	};
}

class ItemsAdapter extends ArrayAdapter<Integer> {

	Context context;
	LayoutInflater inflater;
	int layoutResourceId;
	float imageWidth;
	String [] data;
	public com.image.loading.ImageLoader imageLoader;

	public ItemsAdapter(Context context, int layoutResourceId, Integer[] items,String[] mStrings) {
		super(context, layoutResourceId, items);
		this.context = context;
		data = mStrings;
		this.layoutResourceId = layoutResourceId;

		float width = ((Activity) context).getWindowManager()
				.getDefaultDisplay().getWidth();
		float margin = (int) convertDpToPixel(10f, (Activity) context);
		// two images, three margins of 10dips
		imageWidth = ((width - (3 * margin)) / 2);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FrameLayout row = (FrameLayout) convertView;
		ItemHolder holder;
		Integer item = getItem(position);

		if (row == null) {
			holder = new ItemHolder();
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = (FrameLayout) inflater.inflate(layoutResourceId, parent,
					false);
			ImageView itemImage = (ImageView) row.findViewById(R.id.item_image);
			holder.itemImage = itemImage;
		} else {
			holder = (ItemHolder) row.getTag();
		}
		//ImageView image = holder.itemImage;
		row.setTag(holder);
		//imageLoader.DisplayImage(data[position], image);
		setImageBitmap(item, holder.itemImage);
		return row;
	}

	public static class ItemHolder {
		ImageView itemImage;
	}

	// resize the image proportionately so it fits the entire space
	private void setImageBitmap(Integer item, ImageView imageView) {
		Bitmap bitmap = BitmapFactory.decodeResource(getContext()
				.getResources(), item);
		float i = ((float) imageWidth) / ((float) bitmap.getWidth());
		float imageHeight = i * (bitmap.getHeight());
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView
				.getLayoutParams();
		params.height = (int) imageHeight;
		params.width = (int) imageWidth;
		imageView.setLayoutParams(params);
		imageView.setImageResource(item);
	}

	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

}