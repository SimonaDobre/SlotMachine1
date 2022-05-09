package com.simona.slotmachine1;

import java.util.Random;

public class GameManagement {

    private GenerateSlots generateSlots;
    private boolean allEquals;
    private boolean amountLost;
    private int numberOfSpins;
    int availableAmount;

    public GameManagement(int numberOfSlots) {
        generateSlots = new GenerateSlots(numberOfSlots);
    }


    public void genereazaRandomNumbers(int sumaPrimitaDinMain, int numarSloturi) {
        availableAmount = sumaPrimitaDinMain;
        if (availableAmount > 0){
            numberOfSpins++;
            for (int i = 0; i < numarSloturi; i++) {
                int randomValue = new Random().nextInt(4);
                generateSlots.slotAtIndex(i).setValue(randomValue);
            }
            allEquals = true;
            for (int i = 0; i < numarSloturi - 1; i++) {
                if (generateSlots.slotAtIndex(i).getValue() != generateSlots.slotAtIndex(i+1).getValue()){
                    allEquals = false;
                    break;
                }
            }
            if (allEquals){
                availableAmount *= 2;
            } else {
                availableAmount -= 5;
                if (availableAmount <= 0){
                    amountLost = true;
                    availableAmount = 0;
                }
            }
        }
    }


    public boolean isToateEgale() {
        return allEquals;
    }

    public boolean isAmountLost() {
        return amountLost;
    }

    public int getSumaActuala() {
        return availableAmount;
    }

    public int getNumarSpins() {
        return numberOfSpins;
    }

    public GenerateSlots getGenerateSlots() {
        return generateSlots;
    }


}
