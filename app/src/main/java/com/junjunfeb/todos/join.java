package com.junjunfeb.todos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class join extends AppCompatActivity {

    EditText joinid, joinpw, joinpw2;
    Button btnjoin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinid = (EditText) findViewById(R.id.joinid);
        joinpw = (EditText) findViewById(R.id.joinpw);
        joinpw2 = (EditText) findViewById(R.id.joinpw2);
        btnjoin = (Button)findViewById(R.id.btnjoin);

        sharedPreferences = this.getSharedPreferences("com.junjunfeb.todos", Context.MODE_PRIVATE);


        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()){

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String id = joinid.getText().toString();
                    String pw = joinpw.getText().toString();

                    editor.putString(id, pw);
                    editor.commit();

                    Toast.makeText(join.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    boolean isValid() {

        boolean isIdEdited = joinid.getText().toString().length() > 0;
        boolean isPwEdited = joinpw.getText().toString().length() > 0;
        boolean isPw2Edited = joinpw2.getText().toString().length() > 0;

        if (!isIdEdited || !isPwEdited || !isPw2Edited){
            Toast.makeText(join.this, "필수 입력 항목을 전부 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        String pw = joinpw.getText().toString();
        String pw2 = joinpw2.getText().toString();

        if (!pw.equals(pw2)){
            Toast.makeText(join.this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
