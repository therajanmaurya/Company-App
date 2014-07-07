package in.msme.cic;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class serviceadapterr extends BaseAdapter {
	String[] result;
	Context context;
	int[] imageId;
	private static LayoutInflater inflater = null;

	public serviceadapterr(Context services,
			String[] prgmNameList, int[] prgmImages) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		context = services;
		imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/*public serviceadapterr(FragmentActivity activity, String[] prgmNameList,
			int[] prgmImages) {
		// TODO Auto-generated constructor stub
	}*/

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
		TextView tv;
		ImageView img;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" }) @Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.servicelistitem, null);
	
		holder.tv = (TextView) rowView.findViewById(R.id.heading);
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

