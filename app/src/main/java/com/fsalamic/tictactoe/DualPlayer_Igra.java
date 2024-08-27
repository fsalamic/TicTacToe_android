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

public class DualPlayer_Igra extends AppCompatActivity {

    private TicTacToeTabla ticTacToeTabla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dual_player_igra);

        Button IgratiOpet_dugme = findViewById(R.id.Restart);
        Button Pocetna_dugme = findViewById(R.id.povratak_u_meni);
        TextView KojiIgrac_tekst = findViewById(R.id.trenutni_igrac);

        IgratiOpet_dugme.setVisibility(View.GONE);
        Pocetna_dugme.setVisibility(View.GONE);

        String [] imenaIgraca = getIntent().getStringArrayExtra("IMENA_IGRACA");

        if (imenaIgraca != null) {
            KojiIgrac_tekst.setText("Započnite igru !\nPrvi potez igra: " + imenaIgraca[0]);
        }

        ticTacToeTabla = findViewById(R.id.ticTacToeTabla);
        ticTacToeTabla.namjestiIgru(IgratiOpet_dugme, Pocetna_dugme, KojiIgrac_tekst, imenaIgraca);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void IspocetkaDugme (View view) {
        ticTacToeTabla.resetujTablu();
        ticTacToeTabla.invalidate();
    }

    public void GlaviMeniDugme (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}