package com.example.mafia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class RolePage extends Fragment {
    public ArrayList<String> players;
    public CheckBox mafiaBoss, mafia, negotiator;
    public CheckBox citizen, doctor, detective, reporter, sniper, armored;
    public Button nextPage, previousPage;
    public TextView mafiaInformation, citizenInformation;
    public ArrayList<String> citizenRoles;
    public ArrayList<String> mafiaRoles;
    public ArrayList<String> roles;
    public int citizenNumber, mafiaNumber;
    public PlayerAddition playerAddition;
    GiveRolePage giveRolePage;
    int n;
    boolean flag;

    public RolePage(ArrayList<String> players, PlayerAddition playerAddition) {
        this.players = players;
        this.playerAddition = playerAddition;
        mafiaNumber = (int) (players.size() / 3);
        citizenNumber = players.size() - mafiaNumber;
        roles = new ArrayList<>();
        mafiaRoles = new ArrayList<>();
        citizenRoles = new ArrayList<>();
        mafiaRoles.add("mafia");
        citizenRoles.add("citizen");
        n = 1;
        flag = false;


        giveRolePage = new GiveRolePage(this.roles, this.players);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        nextPage = view.findViewById(R.id.next_to_showing_role_page);
        final RolePage tem = this;
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> temp = new ArrayList<>();
                temp.addAll(mafiaRoles);
                temp.addAll(citizenRoles);
                for (int i = 0; i < mafiaNumber - mafiaRoles.size(); i++) {
                    temp.add("mafia");
                }
                for (int i = 0; i < citizenNumber - citizenRoles.size(); i++) {
                    temp.add("citizen");
                }

                RandomizeRoles randomizeRoles = new RandomizeRoles(temp);
                roles = randomizeRoles.getRandomizedRoles();

                giveRolePage.setRoles(roles);
                giveRolePage.setRolePage(tem);
//
//                for (String role : roles) {
//                    System.out.println(role);
//                }

                giveRolePage.flags = new boolean[mafiaNumber + citizenNumber];
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, giveRolePage);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        previousPage = view.findViewById(R.id.back_to_players_page);
        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, playerAddition);
                fragmentTransaction.commit();
            }
        });


        mafiaInformation = view.findViewById(R.id.mafia_group_details);
        citizenInformation = view.findViewById(R.id.citizen_group_details);
        setTextInformation();


        mafiaBoss = view.findViewById(R.id.mafia_boss_checkbox);
        mafiaBoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mafiaBoss.isChecked()) {
                    mafiaRoles.add("mafiaBoss");
                } else {
                    mafiaRoles.remove("mafiaBoss");
                }
                mafiaInformationChanged();
            }
        });
        mafia = view.findViewById(R.id.mafia_checkbox);
        mafia.setChecked(true);
        mafia.setClickable(false);
        negotiator = view.findViewById(R.id.negotiator_checkbox);
        if (mafiaNumber <= 2) {
            negotiator.setClickable(false);
        } else {
            negotiator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (negotiator.isChecked()) {
                        mafiaRoles.add("negotiator");
                    } else {
                        mafiaRoles.remove("negotiator");
                    }
                    mafiaInformationChanged();
                }
            });
        }


        citizen = view.findViewById(R.id.citizen_checkbox);
        citizen.setChecked(true);
        citizen.setClickable(false);
        doctor = view.findViewById(R.id.doctor_checkbox);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doctor.isChecked()) {
                    citizenRoles.add("doctor");
                    n++;
                } else {
                    citizenRoles.remove("doctor");
                    n--;
                }
                citizenInformationChanged();
            }
        });
        detective = view.findViewById(R.id.detective_checkbox);
        detective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detective.isChecked()) {
                    citizenRoles.add("detective");
                    n++;
                } else {
                    citizenRoles.remove("detective");
                    n--;
                }
                citizenInformationChanged();
            }
        });
        reporter = view.findViewById(R.id.reporter_checkbox);
        reporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reporter.isChecked()) {
                    citizenRoles.add("reporter");
                    n++;
                } else {
                    citizenRoles.remove("reporter");
                    n--;
                }
                citizenInformationChanged();
            }
        });
        sniper = view.findViewById(R.id.sniper_checkbox);
        sniper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sniper.isChecked()) {
                    citizenRoles.add("sniper");
                    n++;
                } else {
                    citizenRoles.remove("sniper");
                    n--;
                }
                citizenInformationChanged();
            }
        });
        armored = view.findViewById(R.id.armored_checkbox);
        armored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (armored.isChecked()) {
                    citizenRoles.add("armored");
                    n++;
                } else {
                    citizenRoles.remove("armored");
                    n--;
                }
                citizenInformationChanged();
            }
        });


    }


    public void checkCitizens() {
        if (n == citizenNumber && !flag) {
            if (!doctor.isChecked()) {
                doctor.setClickable(false);
            }
            if (!detective.isChecked()) {
                detective.setClickable(false);
            }
            if (!sniper.isChecked()) {
                sniper.setClickable(false);
            }
            if (!armored.isChecked()) {
                armored.setClickable(false);
            }
            if (!reporter.isChecked()) {
                reporter.setClickable(false);
            }
            flag = true;
        } else if (flag && n < citizenNumber) {
            doctor.setClickable(true);
            detective.setClickable(true);
            sniper.setClickable(true);
            reporter.setClickable(true);
            armored.setClickable(true);
            flag = false;
        }


    }


    private void setTextInformation() {
        mafiaInformationChanged();
        citizenInformationChanged();
    }

    private void mafiaInformationChanged() {
        mafiaInformation.setText("");
        int n = mafiaRoles.size();
        String mafia = String.format("مافیا(%d)", mafiaNumber - n + 1);
        mafia += ("\n");
        for (int i = 1; i < n; i++) {
            if (mafiaRoles.get(i).equals("mafiaBoss")) {
                mafia += "رئیس مافیا";
            } else if (mafiaRoles.get(i).equals("negotiator")) {
                mafia += "مذاکره کننده";
            }
            if (i != n - 1) {
                mafia += "-";
            }
        }
        mafiaInformation.setText(mafia);

    }


    private void citizenInformationChanged() {
        citizenInformation.setText("");

        int n = citizenRoles.size();
        String str = String.format("شهروند(%d)", citizenNumber - n + 1);
        str += "\n";

        for (int i = 1; i < n; i++) {
            if (citizenRoles.get(i).equals("doctor")) {
                str += "پزشک";
            } else if (citizenRoles.get(i).equals("detective")) {
                str += "کارآگاه";
            } else if (citizenRoles.get(i).equals("armored")) {
                str += "زره‌پوش";
            } else if (citizenRoles.get(i).equals("sniper")) {
                str += "تک تیرانداز";
            } else if (citizenRoles.get(i).equals("reporter")) {
                str += "خبرنگار";
            }
            if (i != n - 1) {
                str += "-";
            }

        }
        checkCitizens();
        citizenInformation.setText(str);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.role_page_fragment, container, false);
    }
}

