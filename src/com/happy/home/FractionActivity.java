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
				comment="�u���a��!!\n�A�o���l���ηh�a�F!";
			}else if(fraction>80){
				comment="������!\n�o�̪����Ҧ�_���٤���";
			}else if(fraction>70){
				comment="���ߧA!\n�a�̪���]�I�ٺ����";
			}else if(fraction>60){
				comment="��...\n�]�\�n�Ҽ{�h�a�F...";
			}else{
				comment="(��)!\n�o�̧A�]��o�U�h!?";
			}
			commentTextView.setText(comment);
			getDetailButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),DetailActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("title", "�� �� �a ��");
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
			return 91;
		}
	}

}
