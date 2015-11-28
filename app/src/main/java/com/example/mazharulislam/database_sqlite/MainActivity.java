package com.example.mazharulislam.database_sqlite;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     DatabaseHelper mydb;
     EditText tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);

        tv1=(EditText)findViewById(R.id.editText);
        tv2=(EditText)findViewById(R.id.editText2);
        tv3=(EditText)findViewById(R.id.editText3);
        tv4=(EditText)findViewById(R.id.editText4);

    }

    public void insert(View view)
    {
        boolean isinserted= mydb.insertData(tv1.getText().toString(),tv2.getText().toString(),tv3.getText().toString());
        if(isinserted==true)
        {
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Insertion Failed",Toast.LENGTH_LONG).show();
        }
    }

    public void ViewAll(View view)
    {

        Cursor res= mydb.getAllData();
        if(res.getCount() ==0) {
            showMessage("Error", "Nothing found");
            //show message
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Surname: " + res.getString(2) + "\n");
            buffer.append("Marks: " + res.getString(3) + "\n");
        }
        showMessage("Your Enlisted Items",buffer.toString());
    }
    public void showMessage(String title, String Message)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(View v)
    {
    Boolean isupdate=mydb.updateData(tv4.getText().toString(),tv1.getText().toString(),tv2.getText().toString(),tv3.getText().toString());

        if(isupdate==true)
        {
            Toast.makeText(MainActivity.this,"Data Updated successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Update Failed",Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View view)
    {
        int deletedRows= mydb.deleteData(tv4.getText().toString());
        if(deletedRows>0)
        {
            Toast.makeText(MainActivity.this,"Data has been deleted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Delete Failed",Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
