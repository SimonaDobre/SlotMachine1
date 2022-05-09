package com.simona.slotmachine1;

import java.util.ArrayList;

public class GenerateSlots {

    private ArrayList<Slot> slotArray;

        public GenerateSlots(int numberOfSlots) {
                slotArray = new ArrayList<>();
                for (int i = 0; i < numberOfSlots; i++){
                        slotArray.add(new Slot(-1));
                }
        }

        public ArrayList<Slot> getSlotArray() {
                return slotArray;
        }

        public Slot slotAtIndex(int x){
                return slotArray.get(x);
        }

}
