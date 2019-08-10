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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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

        final Switch gameGoogleSwitch = findViewById(R.id.movieGoogleSwitch);

        Uri webAddress;

        if(gameGoogleSwitch.isChecked() == true){
            String googleSearch = "https://www.google.com/search?source=hp&ei=thVLXfqFIMOp8gLo9LegBA&q=" + adapter.getItem(position) + "&oq=" + adapter.getItem(position) + "&gs_l=psy-ab.3..35i39j0i67l2j0i131i67j0i67l6.2443.3002..3212...1.0..0.103.450.5j1......0....1..gws-wiz.....10..0i131j0.F1g2pw8PiUc&ved=0ahUKEwj6uMGgr_HjAhXDlFwKHWj6DUQQ4dUDCAU&uact=5";
            webAddress = Uri.parse(googleSearch);
        }else{
            String metaCriticSearch = "https://www.metacritic.com/search/game/" +adapter.getItem(position) + "/results";
            webAddress = Uri.parse(metaCriticSearch);
        }

        //Checks to see if any browser can handle this request and if so go ahead and open that browser
        Intent googleIntent = new Intent(Intent.ACTION_VIEW, webAddress);
        if(googleIntent.resolveActivity(getPackageManager()) != null){
            startActivity(googleIntent);
        }
    }
}//Class


