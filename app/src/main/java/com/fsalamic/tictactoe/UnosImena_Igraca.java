package com.fsalamic.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UnosImena_Igraca extends AppCompatActivity {

    // Deklaracija EditText polja za unos imena igrača
    private EditText Br1;
    private EditText Br2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Omogućavanje EdgeToEdge prikaza
        setContentView(R.layout.unosi_imena_igraca);

        // Povezivanje EditText polja sa odgovarajućim ID-jevima iz layout-a
        Br1 = findViewById(R.id.PrviIgrac);
        Br2 = findViewById(R.id.DrugiIgrac);

        // Postavljanje padding-a za glavni View kako bi se prilagodio sistemskim barovima
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metoda koja se poziva kada se klikne na dugme za potvrdu
    public void SubmitDugmeKlik(View view) {
        // Dobijanje unijetih imena igrača iz EditText polja
        String PrviIgrac = Br1.getText().toString();
        String DrugiIgrac = Br2.getText().toString();

        // Kreiranje novog Intenta za prelazak na glavnu igru
        Intent intent = new Intent(this, Glavna_Igra.class);
        // Dodavanje imena igrača kao dodatak Intentu
        intent.putExtra("IMENA_IGRACA", new String[] {PrviIgrac, DrugiIgrac});
        // Pokretanje nove aktivnosti
        startActivity(intent);
    }



}