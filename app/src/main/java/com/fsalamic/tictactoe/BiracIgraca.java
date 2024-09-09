package com.fsalamic.tictactoe;

import java.util.Random;

//Ovaj Java je potreban kako bi nasumično bio izabran igrac koji će prvi da igra (X ili O) u drugoj rundi
public class BiracIgraca {

    public static int generateRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
