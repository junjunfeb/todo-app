package com.junjunfeb.todos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtpw;
    Button login, join;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.login);
        join = (Button)findViewById(R.id.join);
        txtid = (EditText)findViewById(R.id.txtid);
        txtpw = (EditText)findViewById(R.id.txtpw);

        sharedPreferences = this.getSharedPreferences("com.junjunfeb.todos", Context.MODE_PRIVATE);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, join.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String savedId = sharedPreferences.getString("id", "");
                //String savedpw = sharedPreferences.getString("pw","");//""는 기본값, sharedpreferences가 저장되지 않았을 경우 대신 넣어준다
                String id = txtid.getText().toString();
                String pw = txtpw.getText().toString();

                String savedPw = sharedPreferences.getString(id, ""); // 중요!

                if (savedPw.equals("")){
                    Toast.makeText(MainActivity.this, "회원가입을 먼저 하십시오", Toast.LENGTH_SHORT).show();
                }
                else if(!savedPw.equals(pw)){
                    Toast.makeText(MainActivity.this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                    Intent intentlogin = new Intent(MainActivity.this, todosActivity.class);
                    intentlogin.putExtra("id", id);
                    startActivity(intentlogin);

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }
}
