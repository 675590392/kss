package com.example.jyxmyt.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jyxmyt.R;
import com.example.jyxmyt.adapter.SPDBGridViewAdapter;
import com.example.jyxmyt.entity.Video;
import com.example.jyxmyt.http_url.CommonSigns;
import com.example.jyxmyt.http_url.RequestCode;
import com.example.jyxmyt.http_url.WebServiceUtilThreadString;

/**
 * 视屏点播
 * 
 * @author 123
 * 
 */
public class VideoActivity extends BaseActivity implements OnClickListener {

	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	
	private ImageView qhds; //切换之电视
	
	private RadioGroup rGroup;
	private RadioButton sp_cj;  //推荐
	private RadioButton sp_jy;  //教育
	private RadioButton sp_xx;  //学习
	private RadioButton sp_yl;  //娱乐
	private RadioButton sp_dy;  //电影
	private RadioButton sp_zh;  //综合
	private RadioButton sp_qb;  //全部

	private GridView gridView; // 视频列表
	private String set[] = {
			"中小学生感恩教育讲座_于丹_2014-04-04 12:30_教育_00:04:58_告诉人员要懂得感恩。_wang01",
			"感悟之智慧之道_于丹_2014-04-04 12:30_教育_00:17:51_告诉人员要懂得感恩。_wang02",
			"于丹论语感悟1-2_于丹_2014-04-04 12:30_教育_00:17:51_告诉人员要懂得感恩。_wang03",
			"于丹论语感悟2-2_于丹_2014-04-04 12:30_教育_00:17:51_告诉人员要懂得感恩。_wang04" };
	private Integer set1[] = { R.drawable.jy, R.drawable.jy, R.drawable.gw,
			R.drawable.gw };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.video_activity);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);
		
		qhds = (ImageView) findViewById(R.id.qhds);
		qhds.setOnClickListener(this);

		sp_cj = (RadioButton)findViewById(R.id.sp_cj);
		sp_jy = (RadioButton)findViewById(R.id.sp_jy);
		sp_xx = (RadioButton)findViewById(R.id.sp_xx);
		sp_yl = (RadioButton)findViewById(R.id.sp_yl);
		sp_dy = (RadioButton)findViewById(R.id.sp_dy);
		sp_zh = (RadioButton)findViewById(R.id.sp_zh);
		sp_qb = (RadioButton)findViewById(R.id.sp_qb);
		rGroup = (RadioGroup)findViewById(R.id.rGroup);
		
		rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == sp_cj.getId()){
					Log.i("as", "你点击============------》推荐");
				}else if(checkedId == sp_jy.getId()){
					Log.i("as", "你点击============------》教育");
				}else if(checkedId == sp_xx.getId()){
					Log.i("as", "你点击============------》学习");
				}else if(checkedId == sp_yl.getId()){
					Log.i("as", "你点击============------》娱乐");
				}else if(checkedId == sp_dy.getId()){
					Log.i("as", "你点击============------》电影");
				}else if(checkedId == sp_zh.getId()){
					Log.i("as", "你点击============------》综合");
				}else if(checkedId == sp_qb.getId()){
					Log.i("as", "你点击============------》全部");
				}
			}
		});
		
		
		gridView = (GridView) findViewById(R.id.spdb_grid);
		gridView.setAdapter(new SPDBGridViewAdapter(VideoActivity.this, set,
				set1) {

			@Override
			public void OnDemand(int position) {
				// playVideo(Environment.getExternalStorageDirectory()
				// + "/ceshi.mp4");
				// Intent it = new Intent("com.cooliris.media.MovieView");
				// it.setAction(Intent.ACTION_VIEW);
				// it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// Uri data =
				// Uri.parse();
				// it.setDataAndType(data, "video/mp4");
				// startActivity(it);
				Intent intent = new Intent(VideoActivity.this,
						VideoPlaybackActivity.class);
				intent.putExtra("name", set[position].split("_")[6]);
				System.out.println("--------------------->>|="+set[position].split("_")[6]);
				startActivity(intent);
			}
		});
	}

	private void playVideo(String videoPath) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String strend = "";
		if (videoPath.toLowerCase().endsWith(".mp4")) {
			strend = "mp4";
		} else if (videoPath.toLowerCase().endsWith(".3gp")) {
			strend = "3gp";
		} else if (videoPath.toLowerCase().endsWith(".mov")) {
			strend = "mov";
		} else if (videoPath.toLowerCase().endsWith(".wmv")) {
			strend = "wmv";
		}

		intent.setDataAndType(Uri.parse(videoPath), "video/" + strend);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		if (v == home_but) {
			finish();
		}else if (v == return_but) {
			finish();
		}else if (v == qhds) { //切换之电视
			Intent intent = new Intent(VideoActivity.this,
					TVActivity.class);
			startActivity(intent);
			finish();
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			switch (msg.what) {
			case CommonSigns.SPDB_LB: //视频信息检索成功
				String resul = (String) msg.obj;
				try {
					JSONObject object = new JSONObject(resul);
					List<Video> list = new ArrayList<Video>();
					JSONArray array = object.getJSONArray("row");
					for (int i = 0; i < array.length(); i++) {
						JSONObject object2;
						object2 = array.getJSONObject(i);
						Video video = new Video();
						video.setUser(object2.getString("user"));
						video.setName(object2.getString("name"));
						video.setTime(object2.getString("time"));
						video.setFocus(object2.getString("focus"));
						video.setTimeLeng(object2.getString("timeLeng"));
						video.setContent(object2.getString("content"));
						video.setUrl(object2.getString("url"));
						video.setImage(object2.getString("image"));
						list.add(video);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			default:
				break;
			}

			super.handleMessage(msg);
		}

	};

	/**
	 * 
	 * @param splx
	 *            视频类型
	 * @类名 LegalAidActivity.java
	 * @包名 com.example.jyxmyt.activity
	 * @作者 毅
	 * @时间 2014年4月15日 下午5:58:03
	 * @Email ChunTian1314@vip.qq.com
	 * @版本 V1.0
	 * @功能 (请求检索视频的信息)
	 */
	private void requestRetrieve(String splx) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("RITP", RequestCode.SPDB_LB));
		list.add(new BasicNameValuePair("SPLX", splx));
		WebServiceUtilThreadString threadString = new WebServiceUtilThreadString(
				handler, CommonSigns.url, list, CommonSigns.SPDB_LB);
		CommonSigns.pool.submit(threadString);
	}

}
