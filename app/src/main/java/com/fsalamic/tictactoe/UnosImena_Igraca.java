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


    private EditText Br1;
    private EditText Br2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.unosi_imena_igraca);

        Br1 = findViewById(R.id.PrviIgrac);
        Br2 = findViewById(R.id.DrugiIgrac);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void SubmitDugmeKlik(View view) {
        String PrviIgrac = Br1.getText().toString();
        String DrugiIgrac = Br2.getText().toString();

        Intent intent = new Intent(this, Glavna_Igra.class);
        intent.putExtra("IMENA_IGRACA", new String[] {PrviIgrac, DrugiIgrac});
        startActivity(intent);
    }



}