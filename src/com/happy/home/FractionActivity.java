package com.happy.home;

import com.happy.home.R;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FractionActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			double longitude,latitude;
			View rootView = inflater.inflate(R.layout.fragment_fraction,
					container, false);
			Intent intent = getActivity().getIntent();
			Bundle bundle = intent.getExtras();
			String url = bundle.getString("url");
			TextView placeTextView = (TextView)rootView.findViewById(R.id.titleTextView);
			TextView fractionTextView = (TextView)rootView.findViewById(R.id.fractionTextView);
			TextView commentTextView = (TextView)rootView.findViewById(R.id.commentTextView);
			Button getDetailButton = (Button)rootView.findViewById(R.id.getDetailButton);
			
			InitSize.getInstance(getActivity()).resetViewMarginTop(placeTextView);
			InitSize.getInstance(getActivity()).resetViewMarginTop(fractionTextView);
			InitSize.getInstance(getActivity()).resetViewMarginTop(commentTextView);
			
			placeTextView.setText(bundle.getString("title"));
			float fraction = getFraction();
			fractionTextView.setText(String.valueOf(fraction));
			String comment;
			if(fraction>90){
				comment="優等家園!!\n你這輩子不用搬家了!";
			}else if(fraction>80){
				comment="不錯唷!\n這裡的環境住起來還不錯";
			}else if(fraction>70){
				comment="恭喜你!\n家裡附近設施還算堪用";
			}else if(fraction>60){
				comment="恩...\n也許要考慮搬家了...";
			}else{
				comment="(驚)!\n這裡你也住得下去!?";
			}
			commentTextView.setText(comment);
			getDetailButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),DetailActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("title", "幸 福 家 園");
					bundle.putString("detail", "detail");
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
			return rootView;
		}
		
		private void getCoordinates(String url){
			
		}
		
		private float getFraction(){
			return 71;
		}
	}

}
