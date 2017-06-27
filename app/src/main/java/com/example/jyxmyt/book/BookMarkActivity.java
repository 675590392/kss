// ��ǩ�����Activity
// Leo @ 2010/10/06
package com.example.jyxmyt.book;


import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.example.jyxmyt.R;

public class BookMarkActivity extends Activity{
	
	Button btnAdd;
	Button btnClear;
	Button btnGo;
	
	AddBookMarkDlg addBookMarkDlg;
	OnClickListener oclClick;
	
	// ���ݿ����
    public static final String DB_NAME = "bm1.db";
    public static final int VERSION = 1;
    
    BookMarkDBHelper dbHelper;
    SQLiteDatabase db;
    
    Cursor c;					// ��ѯ������α�
    Spinner s;					// ��ʾ���ݿ�Ŀؼ�
    
    // ��ǩ
    String bookName = "";		// ����
    int position = 0;			// �����ﴫ����λ��
    int markGo = 0;				// ��ǩλ��
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark);
    	
        // ȡ�ÿؼ�
        btnAdd = (Button)this.findViewById(R.id.bookmark_btn_add);
        btnClear = (Button)this.findViewById(R.id.bookmark_btn_clear);
        btnGo = (Button)this.findViewById(R.id.bookmark_btn_go);
        s = (Spinner)findViewById(R.id.bookmark_spinner_list);
        
        // ��Bundle��ȡ��������λ��
        Bundle b = getIntent().getExtras();
        bookName = b.getString("BOOKNAME");
        position = b.getInt("POSITION");
        
        /// �������ݿ�
        dbHelper = new BookMarkDBHelper(this, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();  
        updateSpinner();
        
		// ע��һ�������ǩ�Ի����䷵���¼�
        addBookMarkDlg = new AddBookMarkDlg(this);
        addBookMarkDlg.setOnDismissListener(new OnDismissListener() {

			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				String markName = addBookMarkDlg.getMarkName();
				if (markName.length() > 0) {
					// ��ǩ���ǿգ��������ݿ�
			        ContentValues values = new ContentValues();
			        values.put(BookMarkDBHelper.POSITION, position);
			        values.put(BookMarkDBHelper.MARK_NAME, markName);
			        values.put(BookMarkDBHelper.BOOK_NAME, bookName);
			        db.insert(BookMarkDBHelper.TB_NAME,BookMarkDBHelper.POSITION, values);
		        
				} else {
					// Ĭ��Ϊ��δ��������ǩ���������ݿ�
			        ContentValues values = new ContentValues();
			        values.put(BookMarkDBHelper.POSITION, position);
			        values.put(BookMarkDBHelper.MARK_NAME, "unknown");
			        values.put(BookMarkDBHelper.BOOK_NAME, bookName);
			        db.insert(BookMarkDBHelper.TB_NAME,BookMarkDBHelper.POSITION, values);
				}
			    
				updateSpinner();
			}});
		
        
    	// ʵ�ֵ���¼��ļ����ӿ�
    	oclClick = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				// �����ǩ
				case R.id.bookmark_btn_add: {
					addBookMarkDlg.setDisplay();
				}
					break;
					
				// �����ǩ	
				case R.id.bookmark_btn_clear:
					db.delete(BookMarkDBHelper.TB_NAME,null,null);
					updateSpinner();
					break;
					
				// ȥ����ǩ	
				case R.id.bookmark_btn_go:
					Bundle bundle = new Bundle();
					bundle.putInt("POSITION", markGo);
					Intent mIntent = new Intent();
					mIntent.putExtras(bundle);
					setResult(RESULT_OK, mIntent);
					BookMarkActivity.this.finish();
					break;
				}
			}
    		
    	};
    	
    	// �¼��������Ͱ�ť��
    	btnAdd.setOnClickListener(oclClick);
    	btnClear.setOnClickListener(oclClick);
    	btnGo.setOnClickListener(oclClick);
    }
    
	// ���»ص���ǩ���棬�����û���������
	@Override
	public void onResume() {
		super.onResume();
		// ������ǩ��Ϣ
        Bundle b = getIntent().getExtras();
        bookName = b.getString("BOOKNAME");
        position = b.getInt("POSITION");
	}
	
	// �����˳�ʱ�ͷ����ݿ�
	@Override
	public void onDestroy() {
		super.onDestroy();
		db.close();
	}
	
	// ����Spinner�ؼ�����
	public void updateSpinner() {
		String sql = "SELECT * FROM "
				   + "'" + BookMarkDBHelper.TB_NAME + "'"
				   + " WHERE "
				   + BookMarkDBHelper.BOOK_NAME
				   + "=" + "'" + bookName + "'";

		c = db.rawQuery(sql, null);
        
        // ���α��ѯ
        final int markIdx = c.getColumnIndexOrThrow(BookMarkDBHelper.POSITION);
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(BookMarkActivity.this,
                android.R.layout.simple_spinner_item, 
                c,
                new String[] {BookMarkDBHelper.MARK_NAME}, 
                new int[] {android.R.id.text1});
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> adapter,View v,
                    int pos, long id) {
            	
                c.moveToPosition(pos);
                markGo = c.getInt(markIdx);
                Log.i("", "markGO = " + markGo);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
	}
}