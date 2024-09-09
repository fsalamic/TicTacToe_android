package com.fsalamic.tictactoe;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class LogikaIgrice {

    private int[][] tabla; // Matrica koja predstavlja tablu igre

    //1 element - red, 2 element - kolona, 3 element - tip linije
    private int[] tipPobjede = {-1, -1, -1}; // Informacije o tipu pobjede
    private Button Pocetna_dugme; // Dugme za početak igre


    private Button IgratiOpet_dugme; // Dugme za ponovno igranje

    private String[] ImenaIgraca = {"Igrac 1", "Igrac 2"}; // Imena igrača
    private TextView KojiIgrac_tekst; // Tekst koji prikazuje trenutnog igrača


    private int igrac = 1;
   /*
     uvijek je igrac 1 prvi koji igra
    */


    // Konstruktor koji inicijalizuje tablu
    LogikaIgrice() {
        tabla = new int[3][3];
        for (int r=0;r<3;r++) {
            for (int c=0;c<3;c++) {
                tabla[r][c] = 0;
            }
        }
    }

    // Metoda za ažuriranje table
    public boolean azurirajTablu (int row, int col ) {
        if (tabla[row-1][col-1] == 0 ) {
            tabla[row-1][col-1] = igrac; // Postavlja potez trenutnog igrača

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
            return false; // Polje je već zauzeto
        }
    }

    // Metoda za provjeru pobjednika
    public boolean provjeriPobjednika() {
        //provjerava da li imamo pobjednika
        boolean pobjednik = false;

        // Provjera redova (po horizontali)
        for (int r=0;r<3;r++) {
            if(tabla[r][0] == tabla[r][1] && tabla[r][0] == tabla[r][2] && tabla[r][0] != 0) {
                pobjednik = true;
                // r = red, 0 = kolona, 1 = tip linije
                tipPobjede = new int[] {r, 0, 1}; // Tip pobjede: red (1)

            }
        }
        // Provjera kolona (po vertikali)
        for (int c=0;c<3;c++) {
            if(tabla[0][c] == tabla[1][c] && tabla[0][c] == tabla[2][c] && tabla[0][c] != 0) {
                tipPobjede = new int[] {0, c, 2};  // Tip pobjede: kolona (2)
                pobjednik = true;
            }
        }

        // Provjera dijagonala
        if(tabla[0][0] == tabla[1][1] && tabla[0][0] == tabla[2][2] && tabla[0][0] != 0) {
            tipPobjede = new int[] {0, 2, 3}; // Tip pobjede: negativna dijagonala (3)
                pobjednik = true;
            }
        if(tabla[2][0] == tabla[1][1] && tabla[2][0] == tabla[0][2] && tabla[2][0] != 0) {
            tipPobjede = new int[] {2, 2, 4};  // Tip pobjede: pozitivna dijagonala (4)
                pobjednik = true;
            }

        int tablaPopunjena = 0;

        //provjerava da li je tabla popunjena
        for (int r=0;r<3;r++) {
            for (int c=0;c<3;c++) {
                if(tabla[r][c] != 0) {
                    tablaPopunjena += 1; // Broji popunjena polja
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
            return false; // Nema pobjednika i tabla nije popunjena
        }


    }

    // Metoda za resetovanje table
    public void resetujTablu () {
        for (int r=0;r<3;r++) {
            for (int c=0;c<3;c++) {
                tabla[r][c] = 0;  // Resetuje sva polja na 0
            }
        }

        tipPobjede = new int[] {-1, -1, -1};


        igrac = BiracIgraca.generateRandomInteger(1, 2);  // Nasumično bira igrača koji počinje putem BiracIgraca klase
        Pocetna_dugme.setVisibility(TextView.GONE);
        IgratiOpet_dugme.setVisibility(TextView.GONE);
        KojiIgrac_tekst.setText("Prvi potez igra: " + ImenaIgraca[igrac-1]);

        KojiIgrac_tekst.setTextColor(Color.WHITE);
    }


    // Set metode za postavljanje dugmadi i teksta
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


    // Metode za povlačenje trenutnog stanja table i igrača
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
