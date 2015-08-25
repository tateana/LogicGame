package com.home.tateana.logicgame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class StoryTextActivity extends Activity {

    public static final String TAG_TEXT_ID = "TAG_TEXT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_text);

        int textId = getIntent().getIntExtra(TAG_TEXT_ID, R.string.text1);

        TextView textView = (TextView)findViewById(R.id.text);
        textView.setText(textId);

        Button closeButton = (Button) findViewById(R.id.close);
        closeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              overridePendingTransition(R.anim.slide_up_info,R.anim.abc_slide_in_bottom);
                                              finish();
                                          }
                                      }
        );

    }
}
