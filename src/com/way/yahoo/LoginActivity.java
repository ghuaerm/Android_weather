/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.way.yahoo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登陆页面
 * 
 */
public class LoginActivity extends BaseActivity {
	private EditText usernameEditText;
	private EditText passwordEditText;

	private boolean autoLogin = false;

	private String currentUsername;
	private String currentPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 如果登录成功过，直接进入主页面
		// if (DemoHelper.getInstance().isLoggedIn()) {
		// autoLogin = true;
		// startActivity(new Intent(LoginActivity.this, MainActivity.class));
		// return;
		// }
		setContentView(R.layout.em_activity_login);

		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);

		// if (DemoHelper.getInstance().getCurrentUsernName() != null) {
		// usernameEditText.setText(DemoHelper.getInstance().getCurrentUsernName());
		// }
	}

	/**
	 * 登录
	 * 
	 * @param view
	 */
	public void login(View view) {
		currentUsername = usernameEditText.getText().toString().trim();
		currentPassword = passwordEditText.getText().toString().trim();

		if (TextUtils.isEmpty(currentUsername)) {
			Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(currentPassword)) {
			Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			return;
		}

		SharedPreferences sharedPreferences = getSharedPreferences("test",
				Activity.MODE_PRIVATE);
		String name = sharedPreferences.getString("name", "").trim();
		String word = sharedPreferences.getString("word", "").trim();

		if (name.equals(currentUsername) && word.equals(currentPassword)) {
			startActivity(new Intent(this, MainActivity.class));
			finish();
		} else {
			Toast.makeText(this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		startActivityForResult(new Intent(this, RegisterActivity.class), 0);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (autoLogin) {
			return;
		}
	}
}
