package in.msme.cic;

import com.actionbarsherlock.app.SherlockFragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Services extends SherlockFragment {

	// Resources res = getResources();
	// String[] prgmNameList = res.getStringArray(R.array.service);
	ListView listservice;
	Context context;
	private static final String ARG_SECTION_NUMBER = "section_number";
	public static int[] prgmImages = { R.drawable.service1,
			R.drawable.service2, R.drawable.service3, R.drawable.service4,
			R.drawable.service5 };
	public static String[] prgmNameList = { "CREDIT FACILITATION CENTRE",
			"I TREE", "SITI CENTRE", "ENERGY EFFICIENCY", "INNOVATION CLUSTER" };
	/*public static String[] subprgm = {
			"A Memorandum of understanding \n (MoU) was signed between SIDBI \n and FSIA on 20th december 2008",
			"iTree is innovative step towards skill \n  development wherein we fill skillgao \n  between the existing acadimic setup and industry",
			"SITI centre is step in helping the MSME'S Identify \n new business opportunities , create efficencies, \n new products development , identify ne markets through innovation and know how ",
			"iamsmeofindia  as a nodal agency is \n working towards making SME'S Energy efficient",
			"National Innovation Council (NInC) has selected IAMSME \n of India to implement the Innovation \n Cluster for Auto Components in Faridabad." };*/

	serviceadapter adapter;

	public static Services newInstance(int sectionNumber) {
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
		// context = this;
		listservice = (ListView) V.findViewById(R.id.servicelist);

		adapter = new serviceadapter(getActivity(), prgmNameList, prgmImages);

		listservice.setAdapter(adapter);
		return V;
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onOptionsMenuClosed(menu);
	}

}

@SuppressLint({ "ViewHolder", "InflateParams" })
class serviceadapter extends BaseAdapter {
	String[] result;
	Context context;
	int[] imageId;
	private static LayoutInflater inflater = null;
	
	public static String[] subprgm = {
		"A Memorandum of understanding \n (MoU) was signed between SIDBI \n and FSIA on 20th december 2008",
		"iTree is innovative step towards skill \n  development wherein we fill skillgao \n  between the existing acadimic setup and industry",
		"SITI centre is step in helping the MSME'S Identify \n new business opportunities , create efficencies, \n new products development , identify ne markets through innovation and know how ",
		"iamsmeofindia  as a nodal agency is \n working towards making SME'S Energy efficient",
		"National Innovation Council (NInC) has selected IAMSME \n of India to implement the Innovation \n Cluster for Auto Components in Faridabad." };

	public serviceadapter(Context services, String[] prgmNameList,
			int[] prgmImages) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		context = services;
		imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
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
		TextView tv ,tv1;
		ImageView img;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.servicelistitem, null);

		holder.tv = (TextView) rowView.findViewById(R.id.heading);
		holder.tv1= (TextView) rowView.findViewById(R.id.subhead);
		holder.tv1.setHeight(50);
		holder.tv1.setMinimumHeight(50);
		holder.tv1.setText(subprgm[position]);
		holder.img = (ImageView) rowView.findViewById(R.id.serviceimg);
		holder.tv.setHeight(100);
		holder.tv.setMinimumHeight(100);
		holder.tv.setText(result[position]);
		holder.img.setImageResource(imageId[position]);
		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "You Clicked " + result[position],
						Toast.LENGTH_LONG).show();
			}
		});
		return rowView;
	}
}
