package com.example.mafia;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class GiveRolePage extends Fragment {
    public ArrayList<String> roles;
    public ArrayList<String> players;
    public GridView gridView;
    public Button nextPage;
    public Button previousPage;
    public RolePage rolePage;
    public RoleAdaptor roleAdaptor;
    public boolean[] flags;

    public GiveRolePage(ArrayList<String> roles, ArrayList<String> players) {
        this.roles = roles;
        this.players = players;
        flags = new boolean[players.size()];
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public void setRolePage(RolePage rolePage) {
        this.rolePage = rolePage;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.give_role_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        nextPage = view.findViewById(R.id.nextto_timer);
        final TimeHandling timeHandling = new TimeHandling(this);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isAllTrue()) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, timeHandling);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }

            }
        });
        gridView = view.findViewById(R.id.gridview);
        final GiveRolePage temp = this;
        roleAdaptor = new RoleAdaptor(roles, players, getActivity().getSupportFragmentManager().beginTransaction(), temp, flags);

        gridView.setAdapter(roleAdaptor);


        previousPage = view.findViewById(R.id.backto_tole_page);
        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, rolePage);
                fragmentTransaction.commit();
            }
        });


        for (String role : roles) {
            System.out.println(role);
        }
        for (String player : players) {
            System.out.println(player);
        }
    }


    private boolean isAllTrue() {
        for (int i = 0; i < flags.length; i++) {
            if (!flags[i])
                return false;
        }
        return true;
    }


}
