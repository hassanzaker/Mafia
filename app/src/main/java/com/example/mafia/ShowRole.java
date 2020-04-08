package com.example.mafia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ShowRole extends Fragment {
    String name;
    String role;
    TextView playerName, playerRole, information;
    ImageView imageView;
    Button ok;
    GiveRolePage giveRolePage;

    public ShowRole(String name, String role, GiveRolePage giveRolePage) {
        this.name = name;
        this.role = role;
        this.giveRolePage = giveRolePage;

    }



    public void setGiveRolePage(GiveRolePage giveRolePage) {
        this.giveRolePage = giveRolePage;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playerName = view.findViewById(R.id.player_neme_textview);
        playerName.setText(name);

        playerRole = view.findViewById(R.id.role_textview);
        information = view.findViewById(R.id.role_information_textview);
        imageView = view.findViewById(R.id.role_image);

        setInformation();
        ok = view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, giveRolePage);
                fragmentTransaction.commit();

            }
        });


    }

    private void setInformation(){
        switch (role){
            case "mafia":
                imageView.setImageResource(R.drawable.mafia);
                playerRole.setText("مافیا \n گروه مافیا");
                break;
            case "citizen":
                imageView.setImageResource(R.drawable.citizen);
                playerRole.setText("شهروند\n گروه شهروندان");
                break;
            case "doctor":
                imageView.setImageResource(R.drawable.doctor);
                playerRole.setText("پزشک \n گروه شهروندان");
                break;
            case "detective":
                imageView.setImageResource(R.drawable.kargah);
                playerRole.setText("کارآگاه \n گروه شهروندان");
                break;
            case "sniper":
                imageView.setImageResource(R.drawable.sniper);
                playerRole.setText("تک تیرانداز\n گروه شهروندان");
                break;
            case "armored":
                imageView.setImageResource(R.drawable.armored);
                playerRole.setText("زره‌پوش\n گروه شهروندان");
                break;
            case "reporter":
                imageView.setImageResource(R.drawable.reporter);
                playerRole.setText("خبرنگار \n گروه شهروندان");
                break;
            case "mafiaBoss":
                imageView.setImageResource(R.drawable.mafia_boss);
                playerRole.setText("رئیس مافیا\n گروه مافیا");
                break;
            case "negotiator":
                imageView.setImageResource(R.drawable.negotiator);
                playerRole.setText("مذاکره کننده \n گروه مافیا");
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_role_fragment, container, false);
    }
}
