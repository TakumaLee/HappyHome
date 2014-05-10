package com.happy.home;

import android.app.Activity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class InitSize {	
	private volatile static InitSize initSize;
	private float ratio;
	private Display defDisp;
	private InitSize(Activity activity){
		defDisp=activity.getWindowManager().getDefaultDisplay();
		setRatio(defDisp.getHeight());
	}
	public static InitSize getInstance(Activity activity){
		if (initSize == null) {
			synchronized (InitSize.class) {
				if (initSize == null) {
					initSize=new InitSize(activity);
				}
			}
		}
		return initSize;
	}
	private void setRatio(int height){
		if(height>=950)
			this.ratio=1;
		else 
			this.ratio=0.75f;
	}
	public int getDefaultDisplayHeight(){
		return defDisp.getHeight();
	}
	public int getDefaultDisplayWidth(){
		return defDisp.getWidth();
	}
	public float getRatio(){
		return ratio;
	}
	public void resetViewSize(View view){
		resetViewHeight(view);
		resetViewWidth(view);
	}
	public void resetViewHeight(View view){
		if(view==null)return ;
		LayoutParams params=view.getLayoutParams();
		if(params!=null){
			if(params.height==0){
				view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
				params.height=(int) (view.getMeasuredHeight()*ratio);
			}else{
				params.height*=ratio;
			}
			view.setLayoutParams(params);
		}
	}
	public void resetViewWidth(View view){
		if(view==null)return ;
		LayoutParams params=view.getLayoutParams();
		if(params!=null){
			if(params.width==0){
				view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
				params.width=(int) (view.getMeasuredWidth()*ratio);
			}else{
				params.width*=ratio;
			}
			view.setLayoutParams(params);
		}
	}
	public void resetViewMargin(View view){
		resetViewMarginTop(view);
		resetViewMarginBottom(view);
		resetViewMarginLeft(view);
		resetViewMarginRight(view);
	}
	public void resetViewMarginTop(View view){
		if(view==null)return ;
		LayoutParams params=(android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
		if(params!=null){
			if(params instanceof RelativeLayout.LayoutParams)
				((RelativeLayout.LayoutParams)params).topMargin*=ratio;
			else if(params instanceof LinearLayout.LayoutParams)
				((LinearLayout.LayoutParams)params).topMargin*=ratio;
			view.setLayoutParams(params);
		}
	}
	public void resetViewMarginBottom(View view){
		if(view==null)return ;
		LayoutParams params=(android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
		if(params!=null){
			if(params instanceof RelativeLayout.LayoutParams)
				((RelativeLayout.LayoutParams)params).bottomMargin*=ratio;
			else if(params instanceof LinearLayout.LayoutParams)
				((LinearLayout.LayoutParams)params).bottomMargin*=ratio;
			view.setLayoutParams(params);
		}
	}
	public void resetViewMarginLeft(View view){
		if(view==null)return ;
		LayoutParams params=(android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
		if(params!=null){
			if(params instanceof RelativeLayout.LayoutParams)
				((RelativeLayout.LayoutParams)params).leftMargin*=ratio;
			else if(params instanceof LinearLayout.LayoutParams)
				((LinearLayout.LayoutParams)params).leftMargin*=ratio;
			view.setLayoutParams(params);
		}
	}
	public void resetViewMarginRight(View view){
		if(view==null)return ;
		LayoutParams params=(android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
		if(params!=null){
			if(params instanceof RelativeLayout.LayoutParams)
				((RelativeLayout.LayoutParams)params).rightMargin*=ratio;
			else if(params instanceof LinearLayout.LayoutParams)
				((LinearLayout.LayoutParams)params).rightMargin*=ratio;
			view.setLayoutParams(params);
		}
	}
	public void resetTextMaxLines(TextView textView,int lines){
		if(textView==null)return ;
		if(ratio!=1){
			textView.setMaxLines(lines);
		}
		//textView.setTextSize(textView.getTextSize()*ratio);
	}
	public void ressetTextSize(TextView textView){
		if(textView==null)return ;
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize()*ratio);
	}
}
