/*
 * Copyright (c) 2020. komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youvigo.wms;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.youvigo.wms.data.LocalRepository;

public class MainActivity extends AppCompatActivity {
	private long firstTime = 0;
	protected Toolbar toolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		init(savedInstanceState);
	}

	/**
	 * 添加 ToolBar按钮
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_settings, menu);
		return true;
	}

	/**
	 * 添加ToolBar设置按钮事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Intent mainIntent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(mainIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void init(Bundle savedInstanceState) {
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if (savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.container, MainFragment.newInstance())
					.commitNow();
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {//点击返回键
			long secondTime = System.currentTimeMillis();//以毫秒为单位
			if (secondTime - firstTime > 2000) {
				Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
				firstTime = secondTime;
			} else {
				LocalRepository.clearLoggedInPreferences(getApplicationContext());
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

}
