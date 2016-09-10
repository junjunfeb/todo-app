package com.junjunfeb.todos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class todosActivity extends AppCompatActivity {

    TextView username;
    Button addtodo;
    ListView todolist;

    ArrayList<String> todoList = new ArrayList<>();
    ArrayAdapter<String> adapterTodolist;

    SharedPreferences sharedPreferences;

    String userid = "";


    void addTodo(String todo){
        todoList.add(todo);
        adapterTodolist.notifyDataSetChanged();
    }
    void savetodos(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String todosString = "";


        for (String todo : todoList){
            todosString += todo + "///";//todo는 a,b,c,  todosString = a + "="  --> todoString = a=, b=, c= 하지만 todosString += a + "=" --> todosString = a=, a=b=, a=b=c=
        }
        System.out.println("saved: " + todosString);
        editor.putString("todos"+userid, todosString);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        sharedPreferences = this.getSharedPreferences("com.junjunfeb.todos", Context.MODE_PRIVATE);

        username = (TextView) findViewById(R.id.username);
        addtodo = (Button) findViewById(R.id.addtodo);
        todolist = (ListView) findViewById(R.id.todolist);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            userid = bundle.getString("id");
            username.setText(userid);
        }


        adapterTodolist = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoList );

        todolist.setAdapter(adapterTodolist);
        addtodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(todosActivity.this);
                builder.setTitle("할 일을 추가해주세요");

                /* TODO 코드로 UI 추가! */
                final EditText editTextAddtodo = new EditText(todosActivity.this);
                builder.setView(editTextAddtodo);

                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO; 알럿 다이얼로그 안의 에딧텍스트에 있는 값을 투두리스트에 추가해진다.
                        String todo = editTextAddtodo.getText().toString();
                        addTodo(todo);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        todolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
            }
        });
        todolist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Long clicked: " + position);

                final int p = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(todosActivity.this);
                builder.setTitle("삭제하시겠습니까?");

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removetodo(p);
                    }
                });


                builder.show();

                return true;
            }
        });

        loadtodos();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        savetodos();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }

    void loadtodos() {
//        "할일1///할일2///할일3///" -> 쪼갬 "할일1", "할일2", "할일3" 을 ArrayAdapter에 너어줌
//        split();
        String savedTodoString = sharedPreferences.getString("todos"+userid, "");

        System.out.println("load: " + savedTodoString);

        if (!savedTodoString.equals("")) {

            for (String todo : savedTodoString.split("///")) {
                todoList.add(todo);
            }
            adapterTodolist.notifyDataSetChanged();
        }
    }
    void removetodo(int p){
        todoList.remove(p);
        adapterTodolist.notifyDataSetChanged();
    }
}
