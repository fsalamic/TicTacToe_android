package com.fsalamic.tictactoe;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class LogikaIgrice {

    private int[][] tabla;

    //1 element - red, 2 element - kolona, 3 element - tip linije
    private int[] tipPobjede = {-1, -1, -1};
    private Button Pocetna_dugme;

    private Button IgratiOpet_dugme;

    private String[] ImenaIgraca = {"Igrac 1", "Igrac 2"};
    private TextView KojiIgrac_tekst;


    private int igrac = 1;
   /*
     uvijek je igrac 1 prvi koji igra
     public int igrac = BiracIgraca.generateRandomInteger(1, 2); - kod za nasumican izbor igraca
    */

    LogikaIgrice() {
        tabla = new int[3][3];
        for (int r=0;r<3;r++) {
            for (int c=0;c<3;c++) {
                tabla[r][c] = 0;
            }
        }
    }


    public boolean azurirajTablu (int row, int col ) {
        if (tabla[row-1][col-1] == 0 ) {
            tabla[row-1][col-1] = igrac;

            if(igrac == 1) {
                //prikaze koji igrac trenutno igra
                KojiIgrac_tekst.setText("Trenutni igrač: " + ImenaIgraca[1] + " [O]" );
                //oboji tekst u boju igraca
                KojiIgrac_tekst.setTextColor(TicTacToeTabla.bojaIgraca2);
            } else {
                //prikaze koji igrac trenutno igra
                KojiIgrac_tekst.setText("Trenutni igrač: " + ImenaIgraca[0] + " [X]");
                //oboji tekst u boju igraca
                KojiIgrac_tekst.setTextColor(TicTacToeTabla.bojaIgraca1);
            }

            return true;
        } else {
            return false;
        }
    }


    public boolean provjeriPobjednika() {
        //provjerava da li imamo pobjednika
        boolean pobjednik = false;
        //provjerava redove (po horizontali) - tip pobjede = 1
        for (int r=0;r<3;r++) {
            //provjerava da li je pobjednik igrac 1
            if(tabla[r][0] == tabla[r][1] && tabla[r][0] == tabla[r][2] && tabla[r][0] != 0) {
                pobjednik = true;
                // r = red, 0 = kolona, 1 = tip linije
                tipPobjede = new int[] {r, 0, 1};

            }
        }
        //provjerava kolone (po vertikali) - tip pobjede = 2
        for (int c=0;c<3;c++) {
            if(tabla[0][c] == tabla[1][c] && tabla[0][c] == tabla[2][c] && tabla[0][c] != 0) {
                tipPobjede = new int[] {0, c, 2};
                pobjednik = true;
            }
        }
        //provjerava negativne dijagonale - tip pobjede = 3
        if(tabla[0][0] == tabla[1][1] && tabla[0][0] == tabla[2][2] && tabla[0][0] != 0) {
            tipPobjede = new int[] {0, 2, 3};
                pobjednik = true;
            }
        // provjerava pozitivne dijagonale - tip pobjede = 4
        if(tabla[2][0] == tabla[1][1] && tabla[2][0] == tabla[0][2] && tabla[2][0] != 0) {
            tipPobjede = new int[] {2, 2, 4};
                pobjednik = true;
            }

        int tablaPopunjena = 0;

        //provjerava da li je tabla popunjena
        for (int r=0;r<3;r++) {
            for (int c=0;c<3;c++) {
                if(tabla[r][c] != 0) {
                    tablaPopunjena += 1;
                }
            }
        }

        if(pobjednik) {
                Pocetna_dugme.setVisibility(TextView.VISIBLE);
                IgratiOpet_dugme.setVisibility(TextView.VISIBLE);
                KojiIgrac_tekst.setText("Pobednik je: " + ImenaIgraca[igrac-1]);
            if(igrac == 1) {
                //oboji tekst u boju igraca
                KojiIgrac_tekst.setTextColor(TicTacToeTabla.bojaIgraca1);
            } else {
                //oboji tekst u boju igraca
                KojiIgrac_tekst.setTextColor(TicTacToeTabla.bojaIgraca2);
            }
                return true;
        }
        else if(tablaPopunjena == 9) {
                Pocetna_dugme.setVisibility(TextView.VISIBLE);
                IgratiOpet_dugme.setVisibility(TextView.VISIBLE);
                KojiIgrac_tekst.setText("Nerješeno je!");
                return true;
        }
        else {
            return false;
        }


    }

    public void resetujTablu () {
        for (int r=0;r<3;r++) {
            for (int c=0;c<3;c++) {
                tabla[r][c] = 0;
            }
        }

        tipPobjede = new int[] {-1, -1, -1};


        igrac = BiracIgraca.generateRandomInteger(1, 2);
        Pocetna_dugme.setVisibility(TextView.GONE);
        IgratiOpet_dugme.setVisibility(TextView.GONE);
        KojiIgrac_tekst.setText("Prvi potez igra: " + ImenaIgraca[igrac-1]);

        KojiIgrac_tekst.setTextColor(Color.WHITE);
    }


    public void SetPocetna_dugme(Button Pocetna_dugme1) {
        this.Pocetna_dugme = Pocetna_dugme1;
    }

    public void SetImenaIgraca (String [] imenaIgraca2) {
        this.ImenaIgraca = imenaIgraca2;
    }
    public void SetIgratiOpet(Button IgratiOpet_dugme1) {
        this.IgratiOpet_dugme = IgratiOpet_dugme1;
    }

    public void SetKojiIgrac_dugme(TextView KojiIgrac_tekst2) {
        this.KojiIgrac_tekst = KojiIgrac_tekst2;
    }

   public int[][] povuciTablu() {
        return tabla;
   }

   public void namjestiIgraca(int igrac) {
        this.igrac = igrac;
   }

   public int kojiIgrac() {
        return igrac;
   }

   public int[] tipPobjede() {
        return tipPobjede;

   }
}
