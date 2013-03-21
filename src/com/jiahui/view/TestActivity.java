package com.jiahui.view;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jiahui.view.MySideBar.OnTouchingLetterChangedListener;

public class TestActivity extends Activity implements
		OnTouchingLetterChangedListener {

	private ListView lvShow;
	private List<UserInfo> userInfos;
	private TextView overlay;
	private MySideBar myView;
	private MyUserInfoAdapter adapter;

	private OverlayThread overlayThread = new OverlayThread();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		lvShow = (ListView) findViewById(R.id.lvShow);
		myView = (MySideBar) findViewById(R.id.myView);

		overlay = (TextView) findViewById(R.id.tvLetter);

		lvShow.setTextFilterEnabled(true);
		overlay.setVisibility(View.INVISIBLE);

		getUserInfos();

		Log.i("coder", "userInfos.size" + userInfos.size());
		adapter = new MyUserInfoAdapter(this, userInfos);

		lvShow.setAdapter(adapter);

		myView.setOnTouchingLetterChangedListener(this);

	}

	private void getUserInfos() {

		UserInfo[] userinfoArray = new UserInfo[] {
				new UserInfo("��ɮ", "18765432345", PinyinUtils.getAlpha("��ɮ")),
				new UserInfo("��ʦ��", "18765432345", PinyinUtils.getAlpha("��ʦ��")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("8899", "18765432345",
						PinyinUtils.getAlpha("8899")),
				new UserInfo("�����", "18765432345", PinyinUtils.getAlpha("�����")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("�Ŷ�B", "18876569008", PinyinUtils.getAlpha("�Ŷ�B")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("�Ŷ�B", "18876569008", PinyinUtils.getAlpha("�Ŷ�B")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("�Ŷ�B", "18876569008", PinyinUtils.getAlpha("�Ŷ�B")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("����", "18765432345", PinyinUtils.getAlpha("����")),
				new UserInfo("�Ŷ�B", "18876569008", PinyinUtils.getAlpha("�Ŷ�B")),
				new UserInfo("����", "18909876545", PinyinUtils.getAlpha("����")),
				new UserInfo("��С��", "18909876545", PinyinUtils.getAlpha("��С��")),
				new UserInfo("������", "18909876545", PinyinUtils.getAlpha("������")),
				new UserInfo("����", "18909876545", PinyinUtils.getAlpha("����")),
				new UserInfo("���޼�", "18909876545", PinyinUtils.getAlpha("���޼�")),
				new UserInfo("��С��", "18909876545", PinyinUtils.getAlpha("��С��")) };

		Arrays.sort(userinfoArray, new PinyinComparator());

		userInfos = Arrays.asList(userinfoArray);

	}

	private Handler handler = new Handler() {
	};

	private class OverlayThread implements Runnable {

		public void run() {
			overlay.setVisibility(View.GONE);
		}

	}

	@Override
	public void onTouchingLetterChanged(String s) {
		Log.i("coder", "s:" + s);

		overlay.setText(s);
		overlay.setVisibility(View.VISIBLE);
		handler.removeCallbacks(overlayThread);
		handler.postDelayed(overlayThread, 1000);
		if (alphaIndexer(s) > 0) {
			int position = alphaIndexer(s);
			Log.i("coder", "position:" + position);
			lvShow.setSelection(position);

		}
	}

	public int alphaIndexer(String s) {
		int position = 0;
		for (int i = 0; i < userInfos.size(); i++) {

			if (userInfos.get(i).getPy().startsWith(s)) {
				position = i;
				break;
			}
		}
		Log.i("coder", "i" + position + userInfos.get(position));
		return position;
	}

}
