package com.example.android.basketballmatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add3PointToA(View view) {
        scoreTeamA += 3;
        teamAScore(scoreTeamA);
    }

    public void add2PointToA(View view) {
        scoreTeamA += 2;
        teamAScore(scoreTeamA);
    }

    public void add1PointToA(View view) {
        scoreTeamA += 1;
        teamAScore(scoreTeamA);
    }

    public void teamAScore(int number) {
        TextView TeamA = (TextView) findViewById(R.id.team_A_text_view);
        TeamA.setText("" + number);

    }

    public void add3PointToB(View view) {
        scoreTeamB += 3;
        teamBScore(scoreTeamB);
    }

    public void add2PointToB(View view) {
        scoreTeamB += 2;
        teamBScore(scoreTeamB);
    }

    public void add1PointToB(View view) {
        scoreTeamB += 1;
        teamBScore(scoreTeamB);
    }

    public void teamBScore(int number) {
        TextView TeamB = (TextView) findViewById(R.id.team_B_text_view);
        TeamB.setText("" + number);

    }
    public void reset(View v)
    {
        scoreTeamA=0;
        scoreTeamB=0;
        teamAScore(scoreTeamA);
        teamBScore(scoreTeamB);
    }
}
