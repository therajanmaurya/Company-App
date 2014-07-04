package in.msme.cic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.viewpagerindicator.CirclePageIndicator;

import in.msme.cic.R;

public class PageSlidingTabStripFragment extends Fragment {
	
    CirclePageIndicator circle ;
    private static final String ARG_SECTION_NUMBER = "section_number";
    int []flag = new int[]{R.drawable.image1,R.drawable.image2,R.drawable.image3} ;

	/*public static final String TAG = PageSlidingTabStripFragment.class
			.getSimpleName();

	public static PageSlidingTabStripFragment newInstance() {
		return new PageSlidingTabStripFragment();
	}*/

	@SuppressLint("ValidFragment")
	public static PageSlidingTabStripFragment newInstance(int sectionNumber){
		PageSlidingTabStripFragment fragment = new PageSlidingTabStripFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
		return fragment;
		
	}

	 public PageSlidingTabStripFragment() {
		// TODO Auto-generated constructor stub
	} {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager, container, false);
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		//PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view
				//.findViewById(R.id.tabs);
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
		pager.setAdapter(adapter);
		//tabs.setViewPager(pager);
		circle = (CirclePageIndicator) view.findViewById(R.id.indicator);
		circle.setViewPager(pager);

	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "Categories", "Home", "Top Paid",
				"Top Free" };

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public SherlockFragment getItem(int position) {
			return SuperAwesomeCardFragment.newInstance(position);
		}

	}


}
