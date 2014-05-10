package com.happy.home;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.happy.home.R;
import com.happy.home.api.ParseGarbageRecycle;
import com.happy.home.api.ParseHospital;
import com.happy.home.api.ParseParkingApi;
import com.happy.home.manager.FacilityManager;
import com.happy.home.model.Facility;
import com.happy.home.utils.PositionRetreiver;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
			float fraction = getFraction(url);
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
			initParse();
			
			return rootView;
		}
		
		private void initParse(){
			Activity activity = getActivity();
			SharedPreferences sharedPreferences= activity.getSharedPreferences("SETTING",0);
			if(sharedPreferences.getBoolean("initParse", true)){
				ParseParkingApi.parseParking(activity);
				ParseHospital.parseHospital(activity);
				ParseGarbageRecycle.parseGarbageRecycle(activity);
				sharedPreferences.edit().putBoolean("initParse", false).commit();
			}
		}
		
		private float getFraction(String url){
			int count = 50;
			LatLng latLng = getLatLng(url);
			if(latLng==null)return 0;
			for(int i=1;i<=3;i++){
				List<Facility> facility;
				facility = FacilityManager.getInstance().fetchAroundFacilities(i, latLng.longitude,latLng.latitude);
				Log.v("facility", "======"+i+"=====");
				for(Facility f:facility){
					Log.v("facility", f.getTitle());
				}
			}
			return count;
		}
		
		private LatLng getLatLng(String url){
			return PositionRetreiver.getGPSLocationFromAddressString(url);
		}
	}

}
