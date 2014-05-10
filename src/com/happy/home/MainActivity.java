package com.happy.home;

import com.happy.home.R;
import com.happy.home.manager.FacilityManager;
import com.happy.home.utils.PositionRetreiver;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity {

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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			Button button = (Button)rootView.findViewById(R.id.button1);
			final EditText editText = (EditText)rootView.findViewById(R.id.editText1);
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),FractionActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("title", "幸 福 家 園");
					bundle.putString("url", editText.getText().toString());
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
			return rootView;
		}
	}

}
