package com.example.ninatran.calorieconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.String;


public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private EditText num;
    private TextView textout;
    private TextView txtPushup;
    private TextView txtSitup;
    private TextView txtJump;
    private TextView txtJogging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.select_activity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void convert(View v) {
        Button buttonConvert = (Button) v;

        // Get input value
        num = (EditText) findViewById(R.id.num);

        // Parse Integer
        Integer numInput = Integer.parseInt(num.getText().toString());

        // Declare variables:
        Integer calorie = 0;
        int pushupNum = 0;
        int situpNum = 0;
        int jumpNum = 0;
        int joggingNum = 0;

        // Get the type of activity from Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        String type = spinner.getSelectedItem().toString();

        // Get selected item from RadioGroup
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        buttonConvert = (Button) findViewById(R.id.buttonConvert);
        int selectedID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedID);

        // Check the right metric for each activity
        if (type.equals("Pushup") || type.equals("Situp")) {
            if (radioButton.getText().toString().equals("Minutes")) {
                Toast.makeText(this, "Please choose the right metric...", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (type.equals("Jumping Jacks") || type.equals("Jogging")) {
            if (radioButton.getText().toString().equals("Reps")) {
                Toast.makeText(this, "Please choose the right metric...", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Convert each activity to calorie
        switch (type) {
            case "Pushup": calorie = (numInput*100)/350;
                            pushupNum = numInput;
                            break;
            case "Situp": calorie = (numInput*100)/200;
                            situpNum = numInput;
                            break;
            case "Jumping Jacks": calorie = (numInput * 100)/10;
                            jumpNum = numInput;
                            break;
            case "Jogging": calorie = (numInput*100)/12;
                            joggingNum = numInput;
                            break;
            case "Select an activity": Toast.makeText(this, "Please choose an activity...", Toast.LENGTH_LONG).show();
                            break;
            default: break;
        }

        // Output the burned calorie
        textout = (TextView) findViewById(R.id.txtOut);
        textout.setText(calorie + " cal");

        // Output the equivalent number of pushup
        txtPushup = (TextView) findViewById(R.id.txtPushup);
        if (pushupNum == 0) {
            txtPushup.setText(calorie*350/100 + " Reps");
        } else if (pushupNum != 0) {
            txtPushup.setText(pushupNum + " Reps");
        }

        // Output the equivalent number of situp
        txtSitup = (TextView) findViewById(R.id.txtSitup);
        if (situpNum == 0) {
            txtSitup.setText(calorie*200/100 + " Reps");
        } else if (situpNum != 0) {
            txtSitup.setText(situpNum + " Reps");
        }

        // // Output the equivalent number of jumping jacks
        txtJump = (TextView) findViewById(R.id.txtJump);
        if (jumpNum == 0) {
            txtJump.setText(calorie*10/100 + " Minutes");
        } else if (jumpNum != 0) {
            txtJump.setText(jumpNum + " Minutes");
        }

        // Output the equivalent number of jogging
        txtJogging = (TextView) findViewById(R.id.txtJogging);
        if (joggingNum == 0) {
            txtJogging.setText(calorie * 12 / 100 + " Minutes");
        } else if (joggingNum != 0) {
            txtJogging.setText(joggingNum + " Minutes");
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
