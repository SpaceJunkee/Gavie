package com.example.gamielist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameList extends AppCompatActivity {

    ArrayList<String> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        //variables
        final EditText gameTitleEdit = findViewById(R.id.gameTitleEditText);
        final ListView gameLV = findViewById(R.id.gameListView);

        gameTitleEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;

                if(i == EditorInfo.IME_ACTION_GO){
                    gameList.add(gameTitleEdit.getText().toString());
                    handled = true;
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((getApplicationContext()),
                        android.R.layout.simple_list_item_1, gameList);

                gameLV.setAdapter(arrayAdapter);

                return handled;
            }
        });
    }
}


