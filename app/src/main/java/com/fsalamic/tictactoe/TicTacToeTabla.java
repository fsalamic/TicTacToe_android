package com.fsalamic.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeTabla extends View {

    // Boje za različite elemente table i igrača
    private final int bojaTable;
    public static int bojaIgraca1;
    public static int bojaIgraca2;
    private final int bojaPobjednika;
    private boolean pobjednicki_tekst = false;
    private final Paint boja = new Paint();
    private final LogikaIgrice igrica;
    private int VelicinaKocke = getWidth()/3;



    // Konstruktor klase
    public TicTacToeTabla(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        igrica = new LogikaIgrice();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToeTabla, 0, 0);
        try {
            bojaTable = a.getInteger(R.styleable.TicTacToeTabla_bojaTable, 0);
            bojaIgraca1 = a.getInteger(R.styleable.TicTacToeTabla_bojaIgraca1, 0);
            bojaIgraca2 = a.getInteger(R.styleable.TicTacToeTabla_bojaIgraca2, 0);
            bojaPobjednika = a.getInteger(R.styleable.TicTacToeTabla_bojaPobjednika, 0);
        } finally {
            a.recycle();
        }

    }


    // Postavljanje dimenzija table
    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width,height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        VelicinaKocke = dimension/3;

        setMeasuredDimension(dimension, dimension);
    }

    // Crtanje table i markera
    @Override
    protected void onDraw(Canvas tabla) {
        boja.setStyle(Paint.Style.STROKE);
        boja.setAntiAlias(true);

        nacrtajTablu(tabla);
        nacrtajMarkere(tabla);


        if (pobjednicki_tekst) {
            boja.setColor(bojaPobjednika);
            nacrtajLinijuPobjednika(tabla);
        }
    }

    // Obrada dodira na ekranu
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/VelicinaKocke);
            int col = (int) Math.ceil(x/VelicinaKocke);

            if (!pobjednicki_tekst) {
                if(igrica.azurirajTablu(row,col)){
                    invalidate();

                    if(igrica.provjeriPobjednika()) {
                        pobjednicki_tekst = true;
                        invalidate();
                    }
                    // Ažuriranje koji igrač trenutno igra
                    if(igrica.kojiIgrac() % 2 == 0) {
                        igrica.namjestiIgraca(igrica.kojiIgrac()-1);
                    }
                    else {
                        igrica.namjestiIgraca(igrica.kojiIgrac()+1);
                    }
                }
            } else {

            }

            invalidate();

            return true;
        }

        return false;
    }

    // Crtanje linija table
    private void nacrtajTablu(Canvas canvas) {
        boja.setColor(bojaTable);
        boja.setStrokeWidth(16);

        for (int c = 1; c<3; c++) {
        canvas.drawLine(VelicinaKocke*c,0,VelicinaKocke*c, canvas.getWidth(), boja);
        }
        for (int r = 1; r<3; r++) {
            canvas.drawLine(0,VelicinaKocke*r,canvas.getHeight(),VelicinaKocke*r, boja);
        }
    }

    // Crtanje markera (X i O)
    private void nacrtajMarkere(Canvas canvas){
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                if (igrica.povuciTablu() [r][c] != 0) {
                    if (igrica.povuciTablu() [r][c] == 1) {
                        nacrtajX(canvas, r, c);
                    } else {
                        nacrtajO(canvas,r,c);
                    }
                }
            }
        }
    }

    // Crtanje X markera
    private void nacrtajX(Canvas canvasX, int row, int col) {
        boja.setColor(bojaIgraca1);

        canvasX.drawLine(((col+1)*VelicinaKocke - VelicinaKocke*0.2f),
                        (row*VelicinaKocke + VelicinaKocke*0.2f),
                        (col*VelicinaKocke + VelicinaKocke*0.2f),
                        ((row+1)*VelicinaKocke - VelicinaKocke*0.2f),
                boja);

        canvasX.drawLine((col*VelicinaKocke + VelicinaKocke*0.2f),
                        (row*VelicinaKocke + VelicinaKocke*0.2f),
                        ((col+1)*VelicinaKocke - VelicinaKocke*0.2f),
                          ((row+1)*VelicinaKocke - VelicinaKocke*0.2f),
                boja);
    }

    // Crtanje O markera
    private void nacrtajO(Canvas canvasO, int row, int col) {
        boja.setColor(bojaIgraca2);
        canvasO.drawOval( (col*VelicinaKocke + VelicinaKocke*0.2f),
                         (row*VelicinaKocke + VelicinaKocke*0.2f),
                         ((col*VelicinaKocke + VelicinaKocke) - VelicinaKocke*0.2f),
                         ((row*VelicinaKocke + VelicinaKocke) - VelicinaKocke*0.2f),
                boja);

    }


    // Crtanje Horizontalne linije pobjednika
    private void nacrtajHorizontalnuliniju(Canvas canvas_horiz, int row, int col) {
        canvas_horiz.drawLine(col,
                row*VelicinaKocke + VelicinaKocke/2,
                VelicinaKocke*3,
                row*VelicinaKocke + VelicinaKocke/2,
                boja);

    }

    // Crtanje Vertikalne linije pobjednika
    private void nacrtajVertikalnuliniju(Canvas canvas_vert, int row, int col) {
        canvas_vert.drawLine(col*VelicinaKocke + VelicinaKocke/2,
                row,
                col*VelicinaKocke + VelicinaKocke/2,
                VelicinaKocke*3,
                boja);

    }

    // Crtanje Pozitivne dijagonalne linije pobjednika
    private void nacrtajDijagonaluPozitivnu(Canvas canvas_pozitivna) {
        canvas_pozitivna.drawLine(0,
                0,
                VelicinaKocke*3,
                VelicinaKocke*3,
                boja);

    }

    // Crtanje Negativne dijagonalne linije pobjednika
    private void nacrtajDijagonaluNegativnu(Canvas canvas_negativna) {
        canvas_negativna.drawLine(0,

                VelicinaKocke*3,
                VelicinaKocke*3,
                0,
                boja);

    }

    // Crtanje linije pobjednika zavisno od tipa pobjede
    private void nacrtajLinijuPobjednika(Canvas canvas) {
        int row = igrica.tipPobjede()[0];
        int col = igrica.tipPobjede()[1];

        switch(igrica.tipPobjede()[2]) {
            case 1:
                nacrtajHorizontalnuliniju(canvas, row, col);
                break;
            case 2:
                nacrtajVertikalnuliniju(canvas, row, col);
                break;
            case 3:
                nacrtajDijagonaluPozitivnu(canvas);
                break;
            case 4:
                nacrtajDijagonaluNegativnu(canvas);
                break;
            default:
                break;
        }


    }


    // Postavljanje igre
    public void namjestiIgru (Button igrajOpet, Button pocetna, TextView kojiIgrac, String[] imena) {
        igrica.SetIgratiOpet(igrajOpet);
        igrica.SetKojiIgrac_dugme(kojiIgrac);
        igrica.SetPocetna_dugme(pocetna);
        igrica.SetImenaIgraca(imena);

    }

    // Resetovanje table
    public void resetujTablu() {
        igrica.resetujTablu();
        pobjednicki_tekst = false;
    }
}
