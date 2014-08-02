package in.msme.cic;

import in.msme.cic.ImageLoader.ImageLoader;
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
	/*OnTouchListener touchListener = new OnTouchListener() {
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
	};*/

	/**
	 * Synchronizing scrolling Distance from the top of the first visible
	 * element opposite list: sum_heights(opposite invisible screens) -
	 * sum_heights(invisible screens) + distance from top of the first visible
	 * child
	 */
	/*OnScrollListener scrollListener = new OnScrollListener() {

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
	};*/

	private void loadItems() {
		Integer[] leftItems = new Integer[] { R.drawable.iam3, R.drawable.iam4,
				R.drawable.iamsme, R.drawable.iam2, R.drawable.iam5,
				R.drawable.iam6, R.drawable.iam7, R.drawable.iam8,
				R.drawable.iam9, R.drawable.iam10, R.drawable.iam11,
				R.drawable.iam12, R.drawable.iam13, R.drawable.iam18,
				R.drawable.iam19, R.drawable.iam20, R.drawable.iam21,
				R.drawable.iam22, R.drawable.iam23, R.drawable.iam24,
				R.drawable.iam25, R.drawable.iam26, R.drawable.iam27,
				R.drawable.iam28, R.drawable.iam29, R.drawable.iam30,
				R.drawable.iam31, R.drawable.iam32, R.drawable.iam33,
				R.drawable.iam34, R.drawable.iam35, R.drawable.iam36,
				R.drawable.iam37, R.drawable.iam38, R.drawable.iam39,
				R.drawable.iam40, R.drawable.iam41, R.drawable.iam42,
				R.drawable.iam43, R.drawable.iam44, R.drawable.iam45,
				R.drawable.iam46, R.drawable.iam47, R.drawable.iam48,
				R.drawable.iam49, R.drawable.iam50, R.drawable.iam51,
				R.drawable.iam52, R.drawable.iam53, R.drawable.iam54,
				R.drawable.iam55, R.drawable.iam56, R.drawable.iam57,
				R.drawable.iam58, R.drawable.iam59, R.drawable.iam60 };
		Integer[] rightItems = new Integer[] { R.drawable.iam61,
				R.drawable.iam62, R.drawable.iam63, R.drawable.iam64,
				R.drawable.iam65, R.drawable.iam66, R.drawable.iam67,
				R.drawable.iam68, R.drawable.iam69, R.drawable.iam70,
				R.drawable.iam71, R.drawable.iam72, R.drawable.iam73,
				R.drawable.iam74, R.drawable.iam75, R.drawable.iam76,
				R.drawable.iam77, R.drawable.iam78, R.drawable.iam79,
				R.drawable.iam80, R.drawable.iam81, R.drawable.iam82,
				R.drawable.iam83, R.drawable.iam84, R.drawable.iam85,
				R.drawable.iam86, R.drawable.iam87, R.drawable.iam88,
				R.drawable.iam89, R.drawable.iam90, R.drawable.iam91,
				R.drawable.iam92, R.drawable.iam93, R.drawable.iam94,
				R.drawable.iam95, R.drawable.iam96, R.drawable.iam97,
				R.drawable.iam98, R.drawable.iam99, R.drawable.iam100,
				R.drawable.iam101, R.drawable.iam102, R.drawable.iam103,
				R.drawable.iam104, R.drawable.iam105, R.drawable.iam106,
				R.drawable.iam107, R.drawable.iam108, R.drawable.iam109,
				R.drawable.iam110, R.drawable.iam111, R.drawable.iam112,
				R.drawable.iam113, R.drawable.iam114, R.drawable.iam115,
				R.drawable.iam116, R.drawable.iam117, R.drawable.iam118,
				R.drawable.iam119, R.drawable.iam120, R.drawable.iam121 };

		leftAdapter = new ItemsAdapter(getActivity(), R.layout.staggereditem,
				leftItems, mStrings);
		rightAdapter = new ItemsAdapter(getActivity(), R.layout.staggereditem,
				rightItems, mStrings);
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
	String[] data;
	public ImageLoader imageLoader;

	public ItemsAdapter(Context context, int layoutResourceId, Integer[] items,
			String[] mStrings) {
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
	    ImageView image = holder.itemImage;
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