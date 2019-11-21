package com.way.yahoo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 注册页
 * 
 */
public class RegisterActivity extends BaseActivity {
	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.em_activity_register);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		confirmPwdEditText = (EditText) findViewById(R.id.confirm_password);
	}

	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		final String username = userNameEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		String confirm_pwd = confirmPwdEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			userNameEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(confirm_pwd)) {
			Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
			confirmPwdEditText.requestFocus();
			return;
		} else if (!pwd.equals(confirm_pwd)) {
			Toast.makeText(this, "密码输入不一样", Toast.LENGTH_SHORT).show();
			return;
		}

		SharedPreferences mySharedPreferences = getSharedPreferences("test",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putString("name", username);
		editor.putString("word", pwd);
		editor.commit();
		Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
		finish();
	}

	public void back(View view) {
		finish();
	}

}
