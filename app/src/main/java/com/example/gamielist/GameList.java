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

public class GameList extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    MyRecyclerViewAdapter adapter;

    ArrayList<String> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        //variables
        final EditText gameTitleEdit = findViewById(R.id.gameTitleEditText);
        final RecyclerView recyclerView = findViewById(R.id.gameListView);



        //Save EditText Content to arrayList
        gameTitleEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;

                if(i == EditorInfo.IME_ACTION_GO){
                    gameList.add(gameTitleEdit.getText().toString().trim());
                    handled = true;
                }

                //This will remove any entry that has no characters in the name
                if(gameList.contains("")){
                    gameList.remove(gameList.get(gameList.size() -1));
                }
                //This will close the keyboard after the user has pushed the GO button on the soft keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(gameTitleEdit.getWindowToken(), 0);

                //Clears text when user pushes go on editText
                gameTitleEdit.getText().clear();

                return handled;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this,gameList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        //This will adjust the list when the keyboard appears so the list won't push off screen
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }//OnCreate


    /*Allows me to add a google search to each item of the list to search the text placed in that
    item allowing the user to quickly look up content on that specific element*/
    public void onItemClick(View view, int position) {
        String googleSearch = "https://www.metacritic.com/search/game/" + adapter.getItem(position) + "/results";
        Uri webAddress = Uri.parse(googleSearch);

        //Checks to see if any browser can handle this request and if so go ahead and open that browser
        Intent googleIntent = new Intent(Intent.ACTION_VIEW, webAddress);
        if(googleIntent.resolveActivity(getPackageManager()) != null){
            startActivity(googleIntent);
        }
    }
}//Class

