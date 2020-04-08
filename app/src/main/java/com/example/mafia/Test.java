package com.example.mafia;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;

import androidx.fragment.app.FragmentTransaction;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test extends BaseAdapter {
    ArrayList<String> roles;
    ArrayList<String> players;
    Button button;
    FragmentTransaction fragmentTransaction;
    GiveRolePage giveRolePage;
    ShowRole[] showRoles;
    public Test(ArrayList<String> roles, ArrayList<String> players, FragmentTransaction fragmentTransaction, GiveRolePage giveRolePage) {
        this.roles = roles;
        this.players = players;
        this.fragmentTransaction = fragmentTransaction;
        showRoles = new ShowRole[roles.size()];
        this.giveRolePage = giveRolePage;
        for (int i = 0; i < showRoles.length; i++) {
            showRoles[i] = new ShowRole(players.get(i), roles.get(i), giveRolePage);
        }
    }

    public void setGiveRolePage(GiveRolePage giveRolePage) {
        this.giveRolePage = giveRolePage;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_adaptor, parent, false);
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.button = convertView.findViewById(R.id.player_name_button);

        viewHolder.button.setText(players.get(position));
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.button.setBackgroundColor(Color.BLUE);
                fragmentTransaction.replace(R.id.fragment_container, showRoles[position]);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return convertView;
    }

    public class ViewHolder{
        Button button;

    }

}
