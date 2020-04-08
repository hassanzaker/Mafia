package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> playerNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayerAddition playerAddition = new PlayerAddition();

//        loadPlayers();
//        playerAddition.setPlayerNames(playerNames);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,playerAddition).addToBackStack(null).commit();
    }

//
//    private void loadPlayers() {
//        SharedPreferences sharedPreferences = getSharedPreferences("mafia", Context.MODE_PRIVATE);
//        String jsonText = sharedPreferences.getString("json", "");
//        System.out.println("dasda");
//        try {
//            JSONObject jsonObject = new JSONObject(jsonText);
//            JSONArray jsonArray = jsonObject.getJSONArray("players");
//            for (int i=0 ; i< jsonArray.length() ; i++){
//                playerNames.add(jsonArray.get(i).toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
