package com.example.jyxmyt.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jyxmyt.R;


/**
 * 
 * �����Զ���DIALOG��ʾ��
 * 
 * @since 2012-09-24 10:06:00
 * 
 */
public class SaituDialog extends Dialog {

	public SaituDialog(Context context, int theme){
		super(context, theme);
	}

	public SaituDialog(Context context){
		super(context);
	}

	@Override
	public void setCancelable(boolean flag){
		super.setCancelable(flag);
	}

	/**
	 * Helper class for creating a custom dialog
	 */

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private String neutralButtonTextText;
		private View contentView;
		private boolean b = true;
		private DialogInterface.OnClickListener positiveButtonClickListener;
		private DialogInterface.OnClickListener negativeButtonClickListener;
		private DialogInterface.OnClickListener neutralButtonTextClickListener;

		public Builder(Context context){
			this.context = context;
		}

		/**
		 * Set the Dialog message from String
		 * 
		 * @param title
		 * @return
		 */

		public Builder setMessage(String message){
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */

		public Builder setMessage(int message){
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title){
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title){
			this.title = title;
			return this;
		}

		/**
		 * Set a custom content view for the Dialog. If a message is set, the
		 * contentView is not added to the Dialog...
		 * 
		 * @param v
		 * @return
		 */
		public Builder setContentView(View v){
			this.contentView = v;
			return this;
		}

		/**
		 * ����Ի�������ʱ�ļ����¼�
		 * 
		 * @param b
		 * @return
		 */
		public Builder setCanceleable(boolean b){
			this.b = b;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText,
				DialogInterface.OnClickListener listener){
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the positive button text and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener){
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int negativeButtonText,
				DialogInterface.OnClickListener listener){
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener){
			// System.out.println("****111111111****");
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNeutralButton(int neutralButtonTextText,
				DialogInterface.OnClickListener listener){
			this.neutralButtonTextText = (String) context
					.getText(neutralButtonTextText);
			this.neutralButtonTextClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNeutralButton(String neutralButtonTextText,
				DialogInterface.OnClickListener listener){
			// System.out.println("****111111111****");
			this.neutralButtonTextText = neutralButtonTextText;
			this.neutralButtonTextClickListener = listener;
			return this;
		}

		/**
		 * Create the custom dialog
		 */
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@SuppressLint("NewApi")
		public SaituDialog create(){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// instantiate the dialog with the custom Theme
			final SaituDialog dialog = new SaituDialog(context, R.style.Dialog);
			View layout = inflater.inflate(R.layout.saitu_dialog, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

			// set the dialog title
			((TextView) layout.findViewById(R.id.title))
					.setText(title == null ? "      " : title);

			ImageView image_delect = (ImageView) layout.findViewById(R.id.image_delect);
			image_delect.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			
			// set the confirm button
			Button positiveButton = (Button) layout
					.findViewById(R.id.positiveButton);
			if(positiveButtonText != null){
				positiveButton.setText(positiveButtonText);
				positiveButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v){
						if(positiveButtonClickListener == null){
							dialog.dismiss();
						}
						else{
							positiveButtonClickListener.onClick(dialog,
									DialogInterface.BUTTON_POSITIVE);
						}
					}
				});
			}
			else{
				positiveButton.setVisibility(View.GONE);
			}

			Button neutralButton = (Button) layout
					.findViewById(R.id.neutralButton);
			if(neutralButtonTextText != null){
				neutralButton.setText(neutralButtonTextText);
				neutralButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v){
						if(neutralButtonTextClickListener == null){
							dialog.dismiss();
						}
						else{
							neutralButtonTextClickListener.onClick(dialog,
									DialogInterface.BUTTON_POSITIVE);
						}
					}
				});
			}
			else{
				neutralButton.setVisibility(View.GONE);
			}

			// set the cancel button
			Button negativeButton = (Button) layout
					.findViewById(R.id.negativeButton);
			if(negativeButtonText != null){
				negativeButton.setText(negativeButtonText);
				negativeButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v){
						if(negativeButtonClickListener == null){
							dialog.dismiss();
						}
						else{
							negativeButtonClickListener.onClick(dialog,
									DialogInterface.BUTTON_POSITIVE);
						}
					}
				});
			}
			else{
				negativeButton.setVisibility(View.GONE);
			}

			// set the content message
			TextView messageText = (TextView) layout.findViewById(R.id.message);
			if(message == null){
				messageText.setVisibility(View.GONE);
			}
			else{
				messageText.setVisibility(View.VISIBLE);
				messageText.setText(message);
			}

			// set the content view
			if(contentView != null){
				LinearLayout contentView = (LinearLayout) layout
						.findViewById(R.id.content);
				contentView.setVisibility(View.VISIBLE);
				// ((LinearLayout) layout.findViewById(R.id.main_CD001))
				// .removeView(layout.findViewById(R.id.message));
				// ((LinearLayout) layout.findViewById(R.id.content))
				// .removeAllViewsInLayout();
				contentView.addView(this.contentView, new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				dialog.setContentView(layout);
			}

			dialog.setCancelable(b);
			return dialog;
		}
	}
}
