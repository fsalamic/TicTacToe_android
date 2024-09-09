package com.fsalamic.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Glavna_Igra extends AppCompatActivity {

    private TicTacToeTabla ticTacToeTabla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Omogućava prikaz preko ivica ekrana
        setContentView(R.layout.glavna_igra); // Postavlja izgled aktivnosti

        Button IgratiOpet_dugme = findViewById(R.id.Restart); // Dugme za ponovno igranje
        Button Pocetna_dugme = findViewById(R.id.povratak_u_meni); // Dugme za povratak u meni
        TextView KojiIgrac_tekst = findViewById(R.id.trenutni_igrac); // Tekst koji prikazuje trenutnog igrača

        IgratiOpet_dugme.setVisibility(View.GONE); // Sakriva dugme za ponovno igranje
        Pocetna_dugme.setVisibility(View.GONE); // Sakriva dugme za povratak u meni

        String [] imenaIgraca = getIntent().getStringArrayExtra("IMENA_IGRACA"); // Dobija imena igrača iz Intent-a

        if (imenaIgraca != null) {
            KojiIgrac_tekst.setText("Započnite igru !\nPrvi potez igra: " + imenaIgraca[0]); // Postavlja tekst za početak igre
        }

        ticTacToeTabla = findViewById(R.id.ticTacToeTabla); // Inicijalizuje tablu za igru
        ticTacToeTabla.namjestiIgru(IgratiOpet_dugme, Pocetna_dugme, KojiIgrac_tekst, imenaIgraca); // Postavlja igru


        // Postavlja padding za sistemske trake
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void IspocetkaDugme (View view) {
        ticTacToeTabla.resetujTablu(); // Resetuje tablu za igru
        ticTacToeTabla.invalidate(); // Osvježava prikaz table
    }

    public void GlaviMeniDugme (View view) {
        Intent intent = new Intent(this, MainActivity.class); // Kreira Intent za povratak u glavni meni
        startActivity(intent); // Pokreće aktivnost glavnog menija
    }
}