package com.simona.slotmachine1;

import java.util.Random;

public class GameManagement {

    private GenerateSlots generateSlots;
    private boolean allSlotsEquals;
    private boolean amountLost;
    private int numberOfSpins;
    int availableAmount;

    public GameManagement(int numberOfSlots) {
        generateSlots = new GenerateSlots(numberOfSlots);
    }

    public void generateRandomNumbers(int playMoney, int numberOfSlots) {
        availableAmount = playMoney;
        if (availableAmount > 0){
            numberOfSpins++;
            for (int i = 0; i < numberOfSlots; i++) {
                int randomValue = new Random().nextInt(4);
                generateSlots.slotAtIndex(i).setValue(randomValue);
            }
            updateMoney(numberOfSlots);
        }
    }

    private void updateMoney(int nrSlots){
        allSlotsEquals = true;
        for (int i = 0; i < nrSlots - 1; i++) {
            if (generateSlots.slotAtIndex(i).getValue() != generateSlots.slotAtIndex(i+1).getValue()){
                allSlotsEquals = false;
                break;
            }
        }
        if (allSlotsEquals){
            availableAmount *= 2;
        } else {
            availableAmount -= 5;
            if (availableAmount <= 0){
                amountLost = true;
                availableAmount = 0;
            }
        }
    }

    public boolean isAllEquals() {
        return allSlotsEquals;
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
