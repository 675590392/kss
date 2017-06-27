// �ۿ�Txt�ļ���Activity
// Leo @ 2010/09/23

package com.example.jyxmyt.book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.telephony.PhoneNumberUtils;
import android.text.ClipboardManager;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jyxmyt.R;
import com.example.jyxmyt.common.FilelUtil;

public class ReadBookActivity extends Activity implements OnClickListener{
	
	final int REQUST_CODE_GOTO_BOOKMARK = 1;
	
	CustomTxtView tvMain;			// ������ؼ����Զ���
	String strSelection = "";		// �û�ѡ����ַ���
	String strTxt = "";				// ������ʾ���ı��ַ���
	String strPath = "";			// �������ļ�·��
	int position = 0;				// ��ǰ�Ķ�λ�ã�ȡһ�е�����
	int markPos = 0;				// ��ǩλ��
	
	final int BUFFER_SIZE = 1024 * 3;		// ûʱ���ˣ���ʱ�Ȳ������ļ�������-_-||

	final int SCROLL_STEP = 1;				// �Զ������Ĳ���
	final int BEGIN_SCROLL = 1; 			// ��ʼ����
	final int END_SCROLL = 2;				// ��ֹ����
	final int STOP_SCROLL = 3;				// ��������
	
	final int MENU_BOOKMARK = Menu.FIRST;
	final int MENU_SEARCH = Menu.FIRST+1;
	
	final int DIALOG_AFTER_SELECTION = 4;
	final int DIALOG_GET_SEARCH_KEY_WORD = 5;
	
	boolean isAutoScrolling = false;
	boolean isInSearching = false;
	boolean hasBookMark = false;
	private ImageView brt;
    Button BtnAutoScroll;

	Handler autoScrollHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /*�ж���Ϣ*/
            switch (msg.what) {
            case BEGIN_SCROLL:
                if (tvMain.getScrollY() >= tvMain.getLineCount() * tvMain.getLineHeight() - tvMain.getHeight()) {
                	tvMain.scrollTo(0, tvMain.getLineCount() * tvMain.getLineHeight() - tvMain.getHeight());
                	autoScrollHandle.sendEmptyMessage(END_SCROLL);
                } else {
                    // ����������
                	tvMain.scrollTo(0, tvMain.getScrollY() + SCROLL_STEP);
                	autoScrollHandle.sendEmptyMessageDelayed(BEGIN_SCROLL, 50);
                }
                break;
            case END_SCROLL:
                // �Ѿ��������ײ�
            	//tv.scrollTo(0, tv.getLineCount() * tv.getLineHeight() - tv.getHeight());
            	autoScrollHandle.removeMessages(STOP_SCROLL);
            	autoScrollHandle.removeMessages(BEGIN_SCROLL);
            	break;
            case STOP_SCROLL:
            	// �û��жϹ���
            	autoScrollHandle.removeMessages(END_SCROLL);
            	autoScrollHandle.removeMessages(BEGIN_SCROLL);
            	break;
            default:
                break;
            }
        }
	};
	
	@SuppressLint("ResourceAsColor") public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readbook);
		brt=(ImageView) findViewById(R.id.breturn_but);
		brt.setOnClickListener(this);
		tvMain = (CustomTxtView)this.findViewById(R.id.viewtxt_main_view);	
		BtnAutoScroll = (Button)this.findViewById(R.id.viewtxt_auto_scroll_button);
		BtnAutoScroll.setOnClickListener(this);
		//registerForContextMenu(tv);				// ע��������Ĳ˵�������
	
        try {
            String str =FilelUtil.SDDIR + "/text.txt";  
            strPath = str;	// ����һ�ݸ���
            
        	//FileReader fr = new FileReader((new String("/sdcard/111.txt")));   
            FileReader fr = new FileReader(str); 
            String DecType = fr.getEncoding();
        	
        	char[] buf = new char[BUFFER_SIZE]; 
        	try {fr.read(buf);}
        	catch (IOException e) {}

        	//byte[] bytes = new byte[buf.length];
        	//String strDst = new String(bytes, 0, buf.length, DecType);
			strTxt = new String(buf);
			tvMain.setText(strTxt);
			tvMain.setBackgroundColor(Color.TRANSPARENT);
			tvMain.setTextColor(R.color.beige);
			tvMain.setTextSize(30);
			WindowManager.LayoutParams lp = getWindow().getAttributes();  
			lp.screenBrightness = 1.0f;  
			getWindow().setAttributes(lp); 
			
			tvMain.setCursorVisible(false);
			tvMain.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
        catch (FileNotFoundException e) {

        }
        
        // ���ð�ť�¼�������
        Button BtnPrePage = (Button)this.findViewById(R.id.viewtxt_pre_button);
        BtnPrePage.setOnClickListener(this);
        Button BtnNextPage = (Button)this.findViewById(R.id.viewtxt_next_button);
        BtnNextPage.setOnClickListener(this);
	}

	// ���»ص��������
	@Override
	public void onResume() {
		super.onResume();
		Layout l = tvMain.getLayout();
		if (null != l) {
			// �ص��ϴιۿ���λ��
			if (hasBookMark) {
				hasBookMark = false;
				position = markPos;
			} else {
				int line = l.getLineForOffset(position);
				float sy = l.getLineBottom(line);
				tvMain.scrollTo(0, (int) sy);
			}	
			Log.e("REALPOS_RESUME", "REAL_POS " + position);
		}
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.viewtxt_pre_button:
			if (tvMain.getScrollY() <= tvMain.getHeight())
				tvMain.scrollTo(0, 0);
			else
				tvMain.scrollTo(0, tvMain.getScrollY() - tvMain.getHeight());
			
			Log.e("", "LINEHEIGHT = "+tvMain.getLineHeight());
			break;
		case R.id.viewtxt_next_button:
			if (tvMain.getScrollY() >= tvMain.getLineCount() * tvMain.getLineHeight() - tvMain.getHeight()*2)
				tvMain.scrollTo(0, tvMain.getLineCount() * tvMain.getLineHeight() - tvMain.getHeight());
			else
				tvMain.scrollTo(0, tvMain.getScrollY() + tvMain.getHeight());
				
			Log.e("", "LINECOUNT*LINEHEIGHT = "+(tvMain.getLineCount()*tvMain.getLineHeight()-tvMain.getHeight()));
			Log.e("", "SCROLLY = "+tvMain.getScrollY());
			Log.e("", "TVHEIGHT = "+tvMain.getHeight());
			break;
		case R.id.viewtxt_auto_scroll_button:
	        isAutoScrolling = !isAutoScrolling;
			if (isAutoScrolling) {
		        autoScrollHandle.sendEmptyMessage(BEGIN_SCROLL);
		        //BtnAutoScroll.setText("ֹͣ����");
			} else {
				autoScrollHandle.sendEmptyMessage(STOP_SCROLL);
				//BtnAutoScroll.setText("�Զ�����");
			}
			break;
			
		case R.id.breturn_but:
			this.finish();
			break;

		default:
			break;
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if(keyCode==KeyEvent.KEYCODE_F12){
			 Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    
			 startActivityForResult(i, Activity.DEFAULT_KEYS_DIALER); 
		 }

		 return super.onKeyDown(keyCode, event);
	} 
	
}