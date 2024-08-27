package com.fsalamic.tictactoe;

import java.util.Random;

//ovaj java fajl je potreban kako bi nasumicno bio izabran igrac koji ce prvi da igra (X ili O)
public class BiracIgraca {

    public static int generateRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
