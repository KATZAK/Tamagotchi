package entity;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    GamePanel gp;
    public static Pet instance = null;
    public String name = "Malina";

    public int x, y;
    ArrayList<String[]> petState = new ArrayList<>();
    String [] chooseState;
    String[] petFile = {"pet1", "pet2", "pet3", "pet4", "pet5"};
    String[] petsleepingFile = {"sleeping1", "sleeping2", "sleeping3", "sleeping4", "sleeping5"};
    String[] peteatingFile = {"eating1", "eating2", "eating3", "eating4", "eating5", "eating6", "eating7", "eating8", "eating9", "eating10", "eating11", "eating12", "eating13"};
    String[] petdrinkingFile = {"drinking1", "drinking2", "drinking3", "drinking4", "drinking5", "drinking6", "drinking7", "drinking8", "drinking9", "drinking10", "drinking11", "drinking12", "drinking13"};
    String[] outfitFile = {"outfit0", "outfit1", "outfit2"};
    int petFileInt = 0;
    public int outfitFileInt = 0;
    public boolean escape, sleeping, eating, drinking, playing;
    public int maxStats, hungerlvl, thirstlvl, happinesslvl, tirednesslvl;
    Timer petanimation, timer;
    TimerTask petanimationtask, timertask;

    public Pet(GamePanel gp) {
        this.gp = gp;
        this.maxStats = 100;
        this.x = 240;
        this.y = 293;

        if(instance == null){
            instance = this;
        }

        defaultAnimation();
        petState.add(petFile);
        petState.add(petsleepingFile);
        petState.add(peteatingFile);
        petState.add(petdrinkingFile);

    }

    public static Pet getInstance() {
        return instance;
    }

    // ANIMACJA
    public void defaultAnimation() {
        petanimation = new Timer();
        petanimationtask = new TimerTask() {
            @Override
            public void run() {
                if(eating || drinking){
                    if(petFileInt < 12){
                        petFileInt ++;
                    }
                    else{
                        eating = false;
                        drinking = false;
                        petFileInt = 0;
                    }
                }
                else{
                    if (petFileInt < 4) {
                        petFileInt ++;
                    }
                    else {
                        petFileInt = 0;
                    }
                }

            }
        };
        petanimation.schedule(petanimationtask,2,250);
    }

    // STAN ZWIERZAKA PO ROZPOCZECIU NOWEJ GRY
    public void setDefaultValues() {
        hungerlvl = 70;
        thirstlvl = 50;
        tirednesslvl = 40;
        outfitFileInt = 0;
        eating = false;
        drinking = false;
        sleeping = false;
        escape = false;
    }

    // ZMIANA ANIMACJI ZE WZGLĘDU NA WYKONYWANĄ CZYNNOŚĆ
    public void update() {
        if(sleeping){
            chooseState = petState.get(1);
        }
        else if(eating){
            chooseState = petState.get(2);
        }
        else if(drinking){
            chooseState = petState.get(3);
        }
        else {
            chooseState = petState.get(0);
        }

    }


    // CZYNNOŚCI
    public void pethunger(){
        if (hungerlvl != maxStats) {
            hungerlvl += 1;
        }
    }

    public void feeding(){
        hungerlvl -= 20;

        if(hungerlvl < 0){
            hungerlvl = 0;
        }
    }

    public void petthirst() {
        if(thirstlvl != maxStats){
            thirstlvl += 1;
        }
    }

    public void petminigame(){
        if(tirednesslvl < maxStats){
            tirednesslvl += 20;
        }
        if(tirednesslvl > 80 || tirednesslvl > maxStats){
            tirednesslvl = maxStats;
        }

    }

    public void drink(){
        thirstlvl -= 15;
        if(thirstlvl < 0){
            thirstlvl = 0;
        }
    }

    public void pettired() {
        if(tirednesslvl < maxStats) {
            tirednesslvl ++;
        }
    }

    public void sleep() {
        //sleeping = true;
        tirednesslvl = tirednesslvl -4;
        if(tirednesslvl <= 0){
            tirednesslvl = 0;
            sleeping = false;
        }
    }

    public void escape(){
        if(hungerlvl == maxStats && thirstlvl == maxStats){
            escape = true;
            gp.gameState = gp.gameOverState;
        }
    }

    // WYKONYWANIE CZYNNOŚCI W CZASIE RZECZYWISTYM
    public void petclock() {

        timer = new Timer();

        timertask = new TimerTask() {
            int i=0, j=0;
            @Override
            public void run() {
                if(gp.gameState == gp.titleState){
                    timer.cancel();
                    timer.purge();
                }
                if(gp.gameState == gp.playState){
                    escape();
                    if (escape){
                        timer.cancel();
                    }
                    else {
                        if(!eating)    {
                                pethunger();
                        }
                        if(!drinking)    {
                                petthirst();

                        }
                        if(sleeping){
                            sleep();
                        }
                        else {
                            pettired();
                        }
                    }

                }
            }
        };

        // URUCHOMIENIE TIMERA
        timer.schedule(timertask,0,1000);

    }

    // ZMIANA UBRANIA
    public void wardrobe(){
        if(outfitFileInt < 2) {
            outfitFileInt ++;
        }
        else {
            outfitFileInt = 0;
        }
    }

    // RYSOWANIE ZWIERZAKA
    public void draw(Graphics2D g2) {
        if (!escape){
            try{
                g2.drawImage(ImageIO.read(new File("res/Pet/" + outfitFile[outfitFileInt] + "/" + chooseState[petFileInt] + ".png")),x,y,null);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
