package com.luosu.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.luosu.dezoupoke.R;
import com.luosu.entity.JsonData;
import com.luosu.utils.MyHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends Activity {
    private EditText edit_user, edit_pwd;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        edit_user= (EditText) findViewById(R.id.edit_user);
        edit_pwd= (EditText) findViewById(R.id.edit_pwd);
        register= (Button) findViewById(R.id.register);
        registerListener();
    }

    private void registerListener() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHttpUtils utils= new MyHttpUtils();
                String url = "http://192.168.3.66:8030/MyTomCatTest/Myserver";
                StringBuilder sb = new StringBuilder(url);
                sb.append("?act=common_reg");
                sb.append("&username=" + edit_user.getText().toString());
                sb.append("&userpassword=" + edit_pwd.getText().toString());

                utils.httpget(sb.toString(), new MyHttpUtils.ResultListener() {
                    @Override
                    public void onResult(String result) {
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jo = new JSONObject(result);
                            int resultint = jo.getInt("result");
                            if (resultint == 1) {
                                JsonData data = JSON.parseObject(result, JsonData.class);

                                Intent intent= new Intent();
                                intent.putExtra("user",result);
                                setResult(RESULT_OK,intent);
                                finish();
                                register.setText(data.getUser().toString());
                            } else {
                                register.setText(resultint+"");
                            }
                        } catch (JSONException e) {

                        }

                    }
                });
            }
        });
    }
}
