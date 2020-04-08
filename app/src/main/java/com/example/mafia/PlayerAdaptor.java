package com.example.mafia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAdaptor extends RecyclerView.Adapter<PlayerAdaptor.ViewHolder> {
    List<String> players;
    ArrayList<String> playersToPlay;
    ArrayList<Boolean> flags;

    public PlayerAdaptor(List<String> players, ArrayList<String> playersToPlay, ArrayList<Boolean> flags) {

        this.players = players;
        this.playersToPlay = playersToPlay;
        this.flags = flags;
    }


    public void addPlayer(String name) {
        this.players.add(name);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_adaptor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playersToPlay.remove(players.get(position));
                flags.set(position+1, false);
                players.remove(position);

                notifyDataSetChanged();
            }
        });

        if (flags.get(position))
            holder.checkBox.setChecked(true);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()){
                    playersToPlay.add(players.get(position));
                }else {
                    playersToPlay.remove(players.get(position));
                }
            }
        });


        holder.name.setText(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button remove;
        public CheckBox checkBox;
        public LinearLayoutCompat constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkBox = itemView.findViewById(R.id.add);
            this.name = itemView.findViewById(R.id.name);
            this.remove = itemView.findViewById(R.id.delete);
            constraintLayout = itemView.findViewById(R.id.container_of_recycle);
        }
    }
}
