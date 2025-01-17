package data;

import game.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    // ZAPISYWANIE STANU ZWIERZAKA DO PLIKU
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();
            ds.name = gp.pet.name;
            ds.outfit = gp.pet.outfitFileInt;
            ds.hungerlvl = gp.pet.hungerlvl;
            ds.thirstlvl = gp.pet.thirstlvl;
            ds.tirednesslvl = gp.pet.tirednesslvl;
            ds.eating = gp.pet.eating;
            ds.drinking = gp.pet.drinking;
            ds.sleeping = gp.pet.sleeping;
            ds.escape = gp.pet.escape;

            // ZAPIS DANYCH DO PLIKU
            oos.writeObject(ds);
        }
        catch (Exception e) {
            System.out.println("Błąd zapisu pliku!");
        }
    }

    // WCZYTANIE STANU ZWIERZAKA Z PLIKU
    public void load() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // ODCZYT PLIKU
            DataStorage ds = (DataStorage)ois.readObject();
            gp.pet.name = ds.name;
            gp.pet.outfitFileInt = ds.outfit;
            gp.pet.hungerlvl = ds.hungerlvl;
            gp.pet.thirstlvl = ds.thirstlvl;
            gp.pet.tirednesslvl = ds.tirednesslvl;
            gp.pet.eating = ds.eating;
            gp.pet.drinking = ds.drinking;
            gp.pet.sleeping = ds.sleeping;
            gp.pet.escape = ds.escape;

        }
        catch (Exception e) {
            System.out.println("Bład wczytywania pliku!");
        }
    }
}
