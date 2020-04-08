package com.example.mafia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayerAddition extends Fragment {
    public Button addPlayer;
    public EditText playerGetter;
    public RecyclerView players;
    public Button nextPage;
    public ArrayList<String> playerNames;
    public PlayerAdaptor playerAdaptor;
    public ArrayList<String> playersToPlay;
    public ArrayList<Boolean> flags;
    public boolean flag;

    public PlayerAddition() {
        playerNames = new ArrayList<>();
        this.playersToPlay = new ArrayList<>();
        this.flags = new ArrayList<>();
        flag = false;
    }


    public void savePlayers() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mafia", Context.MODE_PRIVATE);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(playerNames);
        try {
            jsonObject.put("players", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject.toString());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("json", jsonObject.toString());
        editor.apply();

    }

    public void setPlayerNames(ArrayList<String> names){
        playerNames = names;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.nextPage = view.findViewById(R.id.role_page);
        this.addPlayer = view.findViewById(R.id.playeradder);
        this.playerGetter = view.findViewById(R.id.playergetter);
        this.playerGetter.setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!flag){
                    playerGetter.getText().clear();
                    flag = true;
                }
                return false;
            }

        });
//        this.playerGetter.getText().clear();
        this.players = view.findViewById(R.id.all_players);
        this.players.setLayoutManager(new LinearLayoutManager(getActivity()));
        playerAdaptor = new PlayerAdaptor(playerNames, playersToPlay, flags);
        players.setAdapter(playerAdaptor);

        final PlayerAddition temp = this;
        this.nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playersToPlay.size() < 6){
                    Toast.makeText(getActivity(), "تعداد افراد کم است!", Toast.LENGTH_LONG).show();
                }else {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    RolePage rolePage = new RolePage(playersToPlay, temp);
                    fragmentTransaction.replace(R.id.fragment_container, rolePage);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    savePlayers();
                }
            }
        });

        this.addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = playerGetter.getText().toString();
                if (!name.equals("")) {
                    playerNames.add(name);
                    playersToPlay.add(name);
                    playerGetter.getText().clear();
                    flags.add(true);
                    players.getAdapter().notifyDataSetChanged();
                }

            }
        });


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.addplayer_fragment, container, false);
    }
}
