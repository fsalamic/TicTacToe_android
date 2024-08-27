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

public class UnosImena_Jedan_Igrac extends AppCompatActivity {


    private EditText Br1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.unos_imena_jedan_igrac);

        Br1 = findViewById(R.id.PrviIgrac);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void SubmitDugmeKlik(View view) {
        String PrviIgrac = Br1.getText().toString();

        Intent intent = new Intent(this, SinglePlayer_Igra.class);
        intent.putExtra("IME_IGRACA", new String[] {PrviIgrac});
        startActivity(intent);
    }

}