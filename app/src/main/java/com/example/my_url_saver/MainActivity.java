package com.example.my_url_saver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDb;
EditText folder,url,id;
Button save;
Button view;
Button delete;
String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb =new DatabaseHelper(this);
        folder =(EditText)findViewById(R.id.editText);
        url=(EditText)findViewById(R.id.editText2);
        save =(Button)findViewById(R.id.button);
        view = (Button)findViewById(R.id.button2);
        delete =(Button)findViewById(R.id.button3);
        id =(EditText)findViewById(R.id.editText3);
        AddData();
        viewAll();
        DeleteData();
    }
    public void DeleteData(){
        delete.setOnClickListener(

                new Button.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDb.deleteData(id.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }


        );




    }
    public void AddData(){

        save.setOnClickListener(

                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        s1=folder.getText().toString();
                        if(s1.equals("")){
                            s1="default";
                        }
                        boolean isInserted=myDb.insertData(s1,url.getText().toString());

                        if(isInserted=true)
                            Toast.makeText(MainActivity.this,"URL Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"URL not Inserted",Toast.LENGTH_LONG).show();


                    }
                }


        );






        }
    public void viewAll() {
        view.setOnClickListener(
                new Button.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Cursor res =myDb.getAllData();
                        if(res.getCount() ==0){
showMessage("OOP's","Nothing found");
                            return;

                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){

                            buffer.append("PRIMAR_ID :"+ res.getString(0)+"\n");

                            buffer.append("FOLDER :"+ res.getString(1)+"\n");

                            buffer.append("URL :"+ res.getString(2)+"\n\n");
                        }


                        showMessage("Data",buffer.toString());
                    }
                }


        );

    }
    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
