package data;

import entity.Pet;

import java.io.Serializable;

public class DataStorage implements Serializable {
    // STAN ZWIERZAKA
    String name;
    int hungerlvl;
    int thirstlvl;
    int tirednesslvl;
    boolean eating;
    boolean drinking;
    boolean sleeping;
    int outfit;
    boolean escape;
}
