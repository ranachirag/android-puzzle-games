package com.example.game.viewLevel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.game.BaseActivity;
import com.example.game.R;
import com.example.game.models.Interfaces.ScoreboardDataRepositoryInterface;
import com.example.game.models.ScoreboardHolder;

public class ScoreboardActivity extends BaseActivity {

    /**
     * Code to execute when the Activity is created.
     *
     * @param savedInstanceState A Bundle containing possibly previous states of this Activity.
     */
    private ListView listView;
    private ScoreboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //Customizes the Activity based on User preference.
        getWindow().getDecorView().setBackgroundResource(BaseActivity.account.getBackground());

        /**
         * Text displaying player stats
         */
        ScoreboardDataRepositoryInterface scoreboardDataRepositoryInterface = new ScoreboardDataRepository();
        ScoreboardHolder scoreboardHolder = new ScoreboardHolder(this.getFilesDir(), scoreboardDataRepositoryInterface);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        if(scoreboardHolder.addScore(account.getAccount(), account.getCurrentScore(), this.getFilesDir())){
            //create alertdialog saying score was added to the scoreboard
            builder.setTitle("Congratulations!");
            builder.setMessage("Your score was high enough to make it on the scoreboard!");
        }else{
            //create alertdialog to try again, as the score was not high enough to enter the scoreboard
            builder.setTitle("D'oh!");
            builder.setMessage("Your score wasn't high enough to make it on the scoreboard... Try again!");
        }

        listView = findViewById(R.id.list);
        adapter = new ScoreboardAdapter(this, scoreboardHolder.getScoreboardList());
        listView.setAdapter(adapter);
        //All done, so notify the adapter to populate the list using the Items Array

        adapter.notifyDataSetChanged();

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    /**
     * Called when the user taps the "To Main Menu" button. Takes User to the main menu of the App.
     *
     * @param view The View of the Activity.
     */
    public void toMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        //Passes the account into Intent so it can be used accessed in MainActivity.
        BaseActivity.account.resetValues(getApplicationContext().getFilesDir());
        startActivity(intent);
    }
}