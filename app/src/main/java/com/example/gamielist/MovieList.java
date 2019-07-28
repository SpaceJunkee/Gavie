package com.example.gamielist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {

    ArrayList<String> movieList = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list2);

        //variables
        final EditText movieTitleEdit = findViewById(R.id.movieTitleEditText);
        final ListView movieLV = findViewById(R.id.movieListView);

        movieTitleEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;

                if(i == EditorInfo.IME_ACTION_GO){
                    movieList.add(movieTitleEdit.getText().toString());
                    handled = true;
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((getApplicationContext()),
                        android.R.layout.simple_list_item_1, movieList);

                movieLV.setAdapter(arrayAdapter);

                return handled;
            }
        });


    }
}
