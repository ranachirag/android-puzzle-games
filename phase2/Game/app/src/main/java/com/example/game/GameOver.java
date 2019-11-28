package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over2);

        account = (Account) getIntent().getSerializableExtra("ac");

        getWindow().getDecorView().setBackgroundResource(account.getBackground());
    }

    public void toMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        account.resetValues(getApplicationContext());
        intent.putExtra("ac", account);
        startActivity(intent);
    }
}
