package com.luosu.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.luosu.dezoupoke.R;
import com.luosu.entity.JsonData;
import com.luosu.utils.MyHttpUtils;
import com.luosu.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText edit_user, edit_pwd;
    private Button login;
    private TextView register;
    private static final int REGISTERACTIVITY_RESPCODE = 11;
    private static final int REGISTERACTIVITY_RESULTCODE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        registeListener();
    }

    private void registeListener() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login:
                MyHttpUtils utils = new MyHttpUtils();
                String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
                StringBuilder sb = new StringBuilder(url);
                sb.append("?act=login");
                sb.append("&username=" + edit_user.getText().toString());
                sb.append("&userpassword=" + edit_pwd.getText().toString());

                utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                    @Override
                    public void onResult(String result) {
                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jo = new JSONObject(result);
                            int resultint = jo.getInt("result");
                            if (resultint == 1) {
                                JsonData data = JSON.parseObject(result, JsonData.class);
                                login.setText(data.getResult() + "");
                                PrefUtils prefutils = new PrefUtils(LoginActivity.this, PrefUtils.user_messages);
                                prefutils.setUser(data.getUser());
                                LoginActivity.this.startActivity(new Intent(LoginActivity.this, NiuMorePeopleActivity.class));
                            } else {
                                login.setText(resultint + "");
                            }
                        } catch (JSONException e) {

                        }


                    }



                });


                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTERACTIVITY_RESPCODE);





                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case REGISTERACTIVITY_RESPCODE:
                if (resultCode == RESULT_OK) {
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, NiuMorePeopleActivity.class));
                    PrefUtils prefutils = new PrefUtils(LoginActivity.this, PrefUtils.user_messages);
                    JsonData jsonob = JSON.parseObject(data.getStringExtra("user"), JsonData.class);
                    prefutils.setUser(jsonob.getUser());
                }
                break;

        }
    }
}
