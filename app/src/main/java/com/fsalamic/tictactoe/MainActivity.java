package com.fsalamic.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Omogućavanje Edge-to-Edge prikaza
        setContentView(R.layout.activity_main); // Postavljanje glavnog layout-a za aktivnost

        // Postavljanje padding-a za glavni view kako bi se prilagodio sistemskim barovima
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    // Metoda koja se poziva kada se klikne na dugme za početak igre
    public void startDugmeKlik(View view) {
        // Kreiranje novog Intenta za prelazak na aktivnost UnosImena_Igraca

        Intent intent = new Intent (this, UnosImena_Igraca.class);
        startActivity(intent);
    }
}