package com.example.matt.gymlog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;


public class FinishWorkoutActivity extends ActionBarActivity {

    private static final int PADDING = 16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> exercises = (ArrayList<String>)getIntent().getExtras().getSerializable(WorkoutActivity.HISTORY);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(PADDING, PADDING, PADDING, PADDING);

        TextView congrats = (TextView)getLayoutInflater().inflate(R.layout.congrats_message, null);
        layout.addView(congrats);

        ScrollView scrollView = DisplayHelper.displayExercise(layout, exercises, this);

        LinearLayout outerLayout = new LinearLayout(this);
        outerLayout.setOrientation(LinearLayout.VERTICAL);

        Button historyButton = new Button(this);
        historyButton.setText("History");
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(getApplicationContext(), HistoryButtonsActivity.class);
                startActivity(history);
            }
        });
        historyButton.setBackgroundResource(R.drawable.button);

        Button homeButton = new Button(this);
        homeButton.setText("Home");
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        homeButton.setBackgroundResource(R.drawable.button);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        buttonParams.setMargins(4,4,4,4);
        historyButton.setLayoutParams(buttonParams);
        homeButton.setLayoutParams(buttonParams);

        //layout for "home" and "history"
        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.addView(historyButton);
        buttonLayout.addView(homeButton);

        outerLayout.addView(scrollView);
        outerLayout.addView(buttonLayout);

        setContentView(outerLayout);
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
        if (id == R.id.action_delete_database) {
            DatabaseHelper.resetDatabaseDialog(this);
            return true;
        }
        else if(id == R.id.about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.help){
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
