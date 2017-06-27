// ���ļ���Activity
// Leo @ 2010/10/05

package com.example.jyxmyt.book;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.FileListAdapter;
import com.example.jyxmyt.adapter.FileListItem;

public class OpenFileActivity extends Activity {
	private final static String mFileExt = "txt";	// ֻ����txt��ʽ���ļ� 
	public final static int TXT_FILE = 0;			// �ı��ļ��ı�־
	public final static int FOLDER = 1;				// �ļ��еı�־
	
	private File mCurrentDirectory = new File("/sdcard/");	// ��Ŀ¼
	private ListView mlvFileList;							// ��ʾ�ļ�ѡ�������
	private FileListAdapter mAdapter;						// �ļ��б�������
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.openfile);
		
		mlvFileList = (ListView)findViewById(R.id.openfile_listview_file_select);
		mAdapter = new FileListAdapter(this);
		mlvFileList.setAdapter(mAdapter);
		
		// ���õ㵽ListView��ʱ�ļ�������
		ListView.OnItemClickListener itemClick = new ListView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int itemType = mAdapter.getItemType((int)id);
				String mPath = "";
				if (itemType == FOLDER){								// ���ļ���
					String s = mAdapter.getItem((int)id).name;
					if (s.equals("..")){
						mPath = mCurrentDirectory.getParent();
						} else {
							mPath = mCurrentDirectory.getPath() + "/" + s + "/";
							}
					
					mCurrentDirectory = new File(mPath);
					ListFile(mCurrentDirectory);
					} else {											// ���ļ�
						Bundle bundle = new Bundle();
						bundle.putString("FILE_PATH", mCurrentDirectory.getPath() + "/" 
								+ mAdapter.getItem((int)id).name);
						Intent mIntent = new Intent();
						mIntent.putExtras(bundle);
						setResult(RESULT_OK, mIntent);
						OpenFileActivity.this.finish();
						}
			}
		};
		
		ListFile(mCurrentDirectory);
		mlvFileList.setOnItemClickListener(itemClick);
	}
	
	private void ListFile(File aDirectory) {
		mAdapter.clearItems();
		mAdapter.notifyDataSetChanged();
		mlvFileList.postInvalidate();
		
		if (!aDirectory.getPath().equals("/sdcard")){
			FileListItem item = new FileListItem();
			item.name = "..";
			item.type = FOLDER;
			mAdapter.addItem(item);
			}
		
		for(File f : aDirectory.listFiles()){
			if (f.isDirectory()){
				FileListItem item = new FileListItem();
				item.name = f.getName();
				item.type = FOLDER;
				mAdapter.addItem(item);
				} else {
					if (checkExt(f.getName().toLowerCase())) {
						FileListItem item = new FileListItem();
						item.name = f.getName();
						item.type = TXT_FILE;
						mAdapter.addItem(item);
						}
				}
			}
		
		mAdapter.notifyDataSetChanged();
		mlvFileList.postInvalidate();
		}
	
	private boolean checkExt(String itemName) {
		return itemName.endsWith(mFileExt);
	}

}
