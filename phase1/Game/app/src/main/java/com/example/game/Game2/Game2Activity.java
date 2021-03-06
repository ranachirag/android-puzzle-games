package com.example.game.Game2;

//image used (free license):
//https://pixabay.com/illustrations/road-sign-attention-right-of-way-808733/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.Account;
import com.example.game.BaseActivity;
import com.example.game.R;

public class Game2Activity extends BaseActivity {
  Account account;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game2_title);

    account = (Account) getIntent().getSerializableExtra("ac");

    if (account.getCustomization()[0] == 1) {
      getWindow().getDecorView().setBackgroundResource(R.color.background1);
    }
  }

  /** Called when the user taps the "START Riddles Game" button */
  public void nextRiddle1(View view) {
    Intent intent = new Intent(this, Riddle1.class);
    intent.putExtra("ac", account);
    startActivity(intent);
  }
}
