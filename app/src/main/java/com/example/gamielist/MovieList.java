package com.example.gamielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MovieList extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    MyRecyclerViewAdapter adapter;

    ArrayList<String> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list2);

        //variables
        final EditText movieTitleEdit = findViewById(R.id.movieTitleEditText);
        final RecyclerView recyclerView = findViewById(R.id.movieListView);


        //Save EditText Content to arrayList
        movieTitleEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;

                if(i == EditorInfo.IME_ACTION_GO){
                    movieList.add(movieTitleEdit.getText().toString().trim());//User cannot enter empty space before or after text
                    handled = true;
                }

                //This will remove any entry that has no characters in the name
                if(movieList.contains("")){
                    movieList.remove(movieList.get(movieList.size() -1));
                }
                //This will close the keyboard after the user has pushed the GO button on the soft keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(movieTitleEdit.getWindowToken(), 0);

                //Clears text when user pushes go on editText
                movieTitleEdit.getText().clear();

                return handled;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this,movieList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        //This will adjust the list when the keyboard appears so the list won't push off screen
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }//OnCreate

    /*Allows me to add a google search to each item of the list to search the text placed in that
    item allowing the user to quickly look up content on that specific element*/
    public void onItemClick(View view, int position) {
        String googleSearch = "https://www.rottentomatoes.com/search/?search=" + adapter.getItem(position);
        Uri webAddress = Uri.parse(googleSearch);

        //Checks to see if any browser can handle this request and if so go ahead and open that browser
        Intent googleIntent = new Intent(Intent.ACTION_VIEW, webAddress);
        if(googleIntent.resolveActivity(getPackageManager()) != null){
            startActivity(googleIntent);
        }
    }
}//class
