package com.example.gamielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating button variables
        Button movieButton = findViewById(R.id.movieButton);
        Button gameButton = findViewById(R.id.gameButton);

        //Setting movieButton to access MovieList screen when button is clicked
        movieButton.setOnClickListener(new View.OnClickListener() {

            @Override
            //onClick will allow me to declare what happens when the button is clicked
            //an intent is what we want our button to do
            public void onClick(View view) {
                Intent movieListScreen = new Intent(getApplicationContext(),MovieList.class);

                //startActivity allows us to pass our newly created Intent (movieListScreen)
                startActivity(movieListScreen);

            }
        });

        //Setting gameButton to access GameList screen when button is clicked
        gameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            //onClick will allow me to declare what happens when the button is clicked
            //an intent is what we want our button to do
            public void onClick(View view) {
                Intent gameListScreen = new Intent(getApplicationContext(),GameList.class);

                //startActivity allows us to pass our newly created Intent (movieListScreen)
                startActivity(gameListScreen);

            }
        });
    }
}
