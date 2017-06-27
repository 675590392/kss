package com.example.jyxmyt.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.http_url.ImageCacheLocation;
import com.example.jyxmyt.view.MyVideoView;

public class VideoPlaybackActivity extends BaseActivity {

	private MyVideoView videoView; // 视频播放控件
	private SeekBar seekBar; // 播放进度条
	private TextView playtime; // 视频播放时间
	private ImageButton playorpause; // 播放或暂停按钮
	private SeekBar music_volume; // 视频声音进度条
	public AudioManager audiomanage; //
	private int maxVolume; // 声音大小值
	private LinearLayout video_action; // 显示菜单总布局
	private ImageButton bacK_button; // 返回按钮
	private ImageButton volume_button; // 声音图片

	private Timer timer = new Timer();
	private MyTimerTask task;

	private int currentProgress; // 当前进度
	private String playtimeString;

	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		name = getIntent().getStringExtra("name");
		initView();
		audiomanage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audiomanage.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // 获取系统最大音量
		music_volume.setMax(maxVolume);
		int currentv = audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);
		music_volume.setProgress(currentv);
		if (currentv > 0) {
			volume_button.setBackgroundResource(R.drawable.volume_bigzero);
		} else {
			volume_button.setBackgroundResource(R.drawable.volume_zero);
		}
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.video_detail_play);
		seekBar = (SeekBar) findViewById(R.id.seekbar);
		playtime = (TextView) findViewById(R.id.play_time);
		playorpause = (ImageButton) findViewById(R.id.playorpause);
		videoView = (MyVideoView) findViewById(R.id.videoView);
		music_volume = (SeekBar) findViewById(R.id.volume);
		video_action = (LinearLayout) findViewById(R.id.video_action);
		bacK_button = (ImageButton) findViewById(R.id.bacK_button);
		volume_button = (ImageButton) findViewById(R.id.volume_button);

		String targeturl = "";// 目标网址（具体）
		String baseurl = "";// 连接目标网址失败进入的默认网址
		currentProgress = 0;
		
		playtimeString = String.format("%02d:%02d:%02d", 0, 0, 0);
		
		showDialog(VideoPlaybackActivity.this);

		Play(Environment.getExternalStorageDirectory() + "/" + name + ".mp4");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		videoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				videoView.seekTo(currentProgress);
				seekBar.setProgress(currentProgress);
				seekBar.setSecondaryProgress(currentProgress);
				int i = mp.getDuration();
				Log.d("onCompletion", "" + i);
				seekBar.setMax(i);
				i /= 1000;
				int minute = i / 60;
				int hour = minute / 60;
				int second = i % 60;
				minute %= 60;
				String alltimeString = String.format("%02d:%02d:%02d", hour,
						minute, second);

				playtime.setText(playtimeString + "/" + alltimeString);
				if (task != null) {
					task.cancel();
					task = null;
				}
				task = new MyTimerTask();
				timer.schedule(task, 100, 200);
				playorpause.setBackgroundResource(R.drawable.video_pause);
			}
		});
		videoView.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				playorpause.setBackgroundResource(R.drawable.video_play);
				seekBar.setProgress(0);
			}
		});
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				System.out.println("onStopTrackingTouch");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				System.out.println("onStartTrackingTouch");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (fromUser) {
					// 设置视频播放进度
					videoView.seekTo(seekBar.getProgress());
				}
			}
		});
		playorpause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (videoView != null && videoView.isPlaying()) {
					videoView.pause();
					videoView.seekTo(videoView.getCurrentPosition());
					playorpause.setBackgroundResource(R.drawable.video_play);
				} else {
					playorpause.setBackgroundResource(R.drawable.video_pause);
					videoView.start();
					/*
					 * if(seekBar.getProgress() <= 0){
					 * Play(Environment.getExternalStorageDirectory() +
					 * "/ceshi.mp4"); } else{ videoView.start(); }
					 */
				}
			}
		});
		music_volume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (fromUser) {
					audiomanage.setStreamVolume(AudioManager.STREAM_MUSIC,
							progress, 0);
					if (progress > 0) {
						volume_button
								.setBackgroundResource(R.drawable.volume_bigzero);
					} else {
						volume_button
								.setBackgroundResource(R.drawable.volume_zero);
					}
				}
			}
		});
		videoView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		bacK_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(0);
				VideoPlaybackActivity.this.finish();
			}
		});
		volume_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int currentv = audiomanage
						.getStreamVolume(AudioManager.STREAM_MUSIC);

				if (currentv <= 0) {
					music_volume.setProgress(3);
					audiomanage
							.setStreamVolume(AudioManager.STREAM_MUSIC, 3, 0);
					volume_button
							.setBackgroundResource(R.drawable.volume_bigzero);
				} else {
					music_volume.setProgress(0);
					audiomanage
							.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
					volume_button.setBackgroundResource(R.drawable.volume_zero);
				}
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP: {
			if (video_action.getVisibility() == View.VISIBLE) {
				video_action.setVisibility(View.GONE);
			} else {
				video_action.setVisibility(View.VISIBLE);
			}
		}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
/*			if (videoView.getCurrentPosition() <= 0) {
				return ;
			}
*/
			if (seekBar.getMax() < videoView.getDuration()) {
				seekBar.setMax(videoView.getDuration());
			}
			int j = videoView.getBufferPercentage();
			Message mes = new Message();
			mes.what = 112;
			mes.arg1 = videoView.getCurrentPosition();
			mes.arg2 = j * seekBar.getMax() / 100;
			handler.sendMessage(mes);

		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (videoView.isPlaying()) {
			task.cancel();
			videoView.stopPlayback();
		}
	}

	/**
	 * 视频播放
	 * 
	 * @param id
	 *            地址
	 */
	public void Play(String url) {
		if (videoView.isPlaying()) {
			videoView.stopPlayback();
		}
		ImageCacheLocation.getCacheFile(url);
		// Uri uri = Uri.parse(Environment.getExternalStorageDirectory()
		// + "/ceshi.mp4");

		// Intent it = new Intent("com.cooliris.media.MovieView");
		// it.setAction(Intent.ACTION_VIEW);
		// it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// Uri data = Uri.parse(url);
		// it.setDataAndType(data, "video/mp4");
		// startActivity(it);

		Uri uri = Uri.parse(url);
		videoView.setVideoURI(uri);
		videoView.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (videoView.isPlaying()) {
			task.cancel();
			videoView.pause();
			currentProgress = videoView.getCurrentPosition();
			
		}

		super.onPause();
	}
	
	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 112:
				int i = msg.arg1;
				seekBar.setProgress(i);
				seekBar.setSecondaryProgress(msg.arg2);
				i /= 1000;
				int minute = i / 60;
				int hour = minute / 60;
				int second = i % 60;
				minute %= 60;
				String alltimeString = playtime.getText().toString().split("/")[1];
				playtimeString = String.format("%02d:%02d:%02d", hour, minute,
						second);
				playtime.setText(playtimeString + "/" + alltimeString);
				closeDialog();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 获取手机当前音量值
		int i = audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);

		switch (keyCode) {
		// 音量减小
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			int volume_down = audiomanage
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			if (volume_down > 0) {
				audiomanage.setStreamVolume(AudioManager.STREAM_MUSIC,
						--volume_down, 0);
				music_volume.setProgress(--volume_down);
			} else {
				volume_button.setBackgroundResource(R.drawable.volume_zero);
			}
			return true;

			// 音量增大
		case KeyEvent.KEYCODE_VOLUME_UP:
			int volume_up = audiomanage
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			if (volume_up < 15) {
				volume_button.setBackgroundResource(R.drawable.volume_bigzero);
				audiomanage.setStreamVolume(AudioManager.STREAM_MUSIC,
						++volume_up, 0);
				music_volume.setProgress(++volume_up);
			}
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
