package com.example.mafia;

import java.util.ArrayList;
import java.util.Random;

public class RandomizeRoles {
    ArrayList<String> roles;

    public RandomizeRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public ArrayList<String> getRandomizedRoles(){
        ArrayList<String> temp = new ArrayList<>();
        Random r = new Random();
        int n = roles.size();
        for (int i=0 ; i<n ; i++){
            int k = r.nextInt(roles.size());
            temp.add(roles.get(k));
            roles.remove(k);
        }
        return temp;
    }
}
