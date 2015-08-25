package com.home.tateana.logicgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;


public class SelectLevelActivity extends Activity {

    private GridView mLevelsGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        mLevelsGridView = (GridView) findViewById(R.id.levelsGrid);
        ListAdapter adapter = Game.getGame().getLevelAdapter(this);
        mLevelsGridView.setAdapter(adapter);
        mLevelsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectLevelActivity.this, "" + position + " " + id,
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
