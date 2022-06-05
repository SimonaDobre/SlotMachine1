package com.simona.slotmachine1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText amountED, numberOfSlotsED;
    Button goBtn, spinBtn, newGameBtn;
    TextView gameOverTV, infoSpinsTV, infoAmountTV;

    GameManagement game;

    RecyclerView slotsRV;
    Adapter myAdapter;

    int slotsNumber, availableAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        ArrayList<String> s = new ArrayList<>();

    }


    void initViews() {
        amountED = findViewById(R.id.amountEditText);
        amountED.requestFocus();
        numberOfSlotsED = findViewById(R.id.numberOfSlotsEditText);
        goBtn = findViewById(R.id.goButton);
        goBtn.setOnClickListener(this::onClick);
        spinBtn = findViewById(R.id.spinButton);
        spinBtn.setOnClickListener(this);
        spinBtn.setVisibility(View.INVISIBLE);
        newGameBtn = findViewById(R.id.newGameButton);
        newGameBtn.setOnClickListener(this::onClick);
        newGameBtn.setVisibility(View.INVISIBLE);
        gameOverTV = findViewById(R.id.gameOverTextView);
        gameOverTV.setVisibility(View.INVISIBLE);
        infoSpinsTV = findViewById(R.id.infoSpinsTextView);
        infoSpinsTV.setVisibility(View.INVISIBLE);
        infoAmountTV = findViewById(R.id.infoAmountTextView);
        infoAmountTV.setVisibility(View.INVISIBLE);
        slotsRV = findViewById(R.id.slotRV);
        slotsRV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        int clickedID = view.getId();
        switch (clickedID) {
            case R.id.goButton:
                slotsNumber = Integer.parseInt(numberOfSlotsED.getText().toString().trim());
                if (slotsNumber < 2){
                    Toast.makeText(this, "numarul trebuie sa fie mai mare decat 2", Toast.LENGTH_SHORT).show();
                    numberOfSlotsED.setText(null);
                    numberOfSlotsED.requestFocus();
                    return;
                }
                slotsRV.setLayoutManager(new GridLayoutManager(this, slotsNumber));
                game = new GameManagement(slotsNumber);
                myAdapter = new Adapter(game.getGenerateSlots().getSlotArray());
                slotsRV.setAdapter(myAdapter);

                amountED.setVisibility(View.INVISIBLE);
                numberOfSlotsED.setVisibility(View.INVISIBLE);
                goBtn.setVisibility(View.INVISIBLE);
                slotsRV.setVisibility(View.VISIBLE);
                spinBtn.setVisibility(View.VISIBLE);
                infoAmountTV.setVisibility(View.VISIBLE);
                infoSpinsTV.setVisibility(View.VISIBLE);

                availableAmount = Integer.parseInt(amountED.getText().toString().trim());
                infoAmountTV.setText(" suma disponibila: " + availableAmount);
                infoSpinsTV.setText(" numar spins: " + game.getNumarSpins());
                break;
            case R.id.spinButton:
                game.generateRandomNumbers(availableAmount, slotsNumber);
                myAdapter.setSlotsArray(game.getGenerateSlots().getSlotArray());
                myAdapter.notifyDataSetChanged();
                slotsRV.setAdapter(myAdapter);
                infoSpinsTV.setText(game.getNumarSpins() + " incercari");
                infoAmountTV.setText(game.getSumaActuala() + " lei disponibili");
                availableAmount = game.getSumaActuala();
                if (game.isAmountLost()) {
                    spinBtn.setVisibility(View.INVISIBLE);
                    gameOverTV.setVisibility(View.VISIBLE);
                    gameOverTV.setText("Din pacate suma s-a terminat!");
                    newGameBtn.setVisibility(View.VISIBLE);
                }
                if (game.isAllEquals()) {
                    infoAmountTV.setText("Am dublat suma! Sunt " + game.getSumaActuala() + " lei disponibili.");
                }
                break;
            case R.id.newGameButton:
                newGame();
                newGameBtn.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    void newGame() {
        amountED.setText(null);
        amountED.setVisibility(View.VISIBLE);
        amountED.requestFocus();
        numberOfSlotsED.setVisibility(View.VISIBLE);
        numberOfSlotsED.setText(null);
        goBtn.setVisibility(View.VISIBLE);
        spinBtn.setVisibility(View.INVISIBLE);
        infoAmountTV.setVisibility(View.INVISIBLE);
        infoSpinsTV.setVisibility(View.INVISIBLE);
        gameOverTV.setText(null);
        gameOverTV.setVisibility(View.INVISIBLE);
        game = new GameManagement(0);
        myAdapter.setSlotsArray(game.getGenerateSlots().getSlotArray());
        slotsRV.setAdapter(myAdapter);
    }

}