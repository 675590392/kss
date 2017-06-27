// �Զ��������ǩ�Ի���
// Leo @ 2010/10/06

package com.example.jyxmyt.book;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.jyxmyt.R;

class AddBookMarkDlg extends Dialog implements android.view.View.OnClickListener{
	 public AddBookMarkDlg(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	Button btnConfirm;
	Button btnCancel;
	EditText editMarkName;
	String markName = "";
	
	public void setDisplay(){
		setContentView(R.layout.addbookmarkdlg);	// ���öԻ���Ĳ���
		btnConfirm = (Button)findViewById(R.id.addbookmark_dlg_btn_confirm);
		btnConfirm.setOnClickListener(this);
		btnCancel = (Button)findViewById(R.id.addbookmark_dlg_btn_cancel);
		btnCancel.setOnClickListener(this);
		editMarkName = (EditText)findViewById(R.id.addbookmark_dlg_edit_markname);
	    setProperty(); 
	    setTitle("��ǩ��");							// �趨�Ի���ı���
	    show();										// ��ʾ�Ի���   
	    }
	    
	public void setProperty(){
		Window window = getWindow();					// �õ��Ի���Ĵ���
	    WindowManager.LayoutParams wl = window.getAttributes();
	    wl.x = 0;							
	    wl.y = 0;
	    wl.width = 240;
	    wl.height = 180;
	    wl.alpha = 0.9f;
	    wl.gravity = Gravity.CENTER;         
	    window.setAttributes(wl); 
	    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addbookmark_dlg_btn_confirm:
			markName = editMarkName.getText().toString();
			dismiss();
			break;
		case R.id.addbookmark_dlg_btn_cancel:
			markName = "";
			dismiss();
			break;
		}
	}
	
	public String getMarkName() {
		return markName;
	}
}