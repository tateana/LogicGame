package com.home.tateana.logicgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.home.tateana.logicgame.gui.SoundPlayer;


public class SelectLevelActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        GridView levelsGridView = (GridView) findViewById(R.id.levelsGrid);
        ListAdapter adapter = Game.getGame().getLevelAdapter(this);
        levelsGridView.setAdapter(adapter);

        final SoundPlayer soundPlayer = Game.getGame().getSoundPlayer(this);

        levelsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                soundPlayer.playButtonClick();

                int level = (int) id;
                Intent startLevelIntent = new Intent(SelectLevelActivity.this, Game.getGame().getClass(level));
                startLevelIntent.putExtra(Game.STATE_LEVEL, level);
                startActivity(startLevelIntent);

                finish();
            }
        });

        ImageButton closeButton = (ImageButton) findViewById(R.id.close);
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                soundPlayer.playButtonClick();
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        Game.getGame().unbind(this);
        super.onStop();
    }

    @Override
    protected void onStart() {
        Game.getGame().bind(this);
        super.onStart();
    }

}
