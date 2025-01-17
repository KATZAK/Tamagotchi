package game;

import background.Background;
import data.SaveLoad;
import entity.Pet;
import menu.Menubtns;
import menu.SettingButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GamePanel extends JPanel implements Runnable{
    // USTAWIENIA GŁÓWNEGO PANELU
    final int DEFAULT_WIDTH = 600;
    final int DEFAULT_HEIGHT = 720;

    // FPS
    int FPS = 60;

    Background bg = new Background(this);
    Menubtns menu = new Menubtns();
    public UI ui = new UI(this);
    public SaveLoad saveLoad = new SaveLoad(this);
    public SettingButtons settingButtons = new SettingButtons(this);
    public static int action = 0;
    Sound sound = new Sound();
    Thread gameThread;
    public Pet pet = new Pet(this);

    // STAN GRY
    public static int gameState;
    public final int titleState = 0;
    public static final int playState = 1;
    public final int gameOverState = 2;
    public static final int minigameState = 3;
    public boolean saveScreen = false, musicplaying;

    public GamePanel(){
        // USTAWIENIA PANELU
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);


        // USTAWIENIA MYSZY
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // PRZYCISKI OPCJI (NA RAMCE)
                if(gameState!=titleState){
                    if(settingButtons.settingbtns.get(0).collide(e)){
                        saveLoad.save();
                        ui.popup = true;
                        ui.savepopuptimer();
                    }
                    if(settingButtons.settingbtns.get(1).collide(e)){
                        gameState = titleState;
                    }
                }
                if(settingButtons.settingbtns.get(2).collide(e)){
                    if(musicplaying){
                        stopMusic();
                        musicplaying = false;
                    }
                    else{
                        playMusic(0);
                        musicplaying = true;
                    }
                }

                // PRZYCISKI MENU
                for(int i = 0; i < menu.buttons.size(); i++) {
                    if(!Pet.getInstance().eating && !Pet.getInstance().drinking) {
                        if (menu.buttons.get(i).collide(e)) {
                            menu.buttons.get(i).action();
                        }
                    }
                }

                // PRZYCISKI W MINIGRZE
                if(gameState == minigameState){
                    for(int i = 0; i < ui.minigamebtns.size(); i++) {
                            if (ui.minigamebtns.get(i).collide(e)) {
                                if(i == 0){
                                    action = 1;
                                }
                                if(i == 1){
                                    action = 2;
                                }
                                if(i == 2){
                                    action = 3;
                                }
                                ui.minigamebtns.get(i).action();
                            }
                    }

                }

                // EKRAN STARTOWY
                if (gameState == titleState){
                    if (ui.titlebtns.get(0).txtcollide(e)) {
                        gameState = playState;
                        Pet.getInstance().setDefaultValues();
                        pet.petclock();
                    }
                    else if (ui.titlebtns.get(1).txtcollide(e)) {
                        saveLoad.load();
                        gameState = playState;
                        pet.petclock();
                    }
                    else if(ui.titlebtns.get(2).txtcollide(e)) {
                        System.exit(0);
                    }
                    ui.commandNum = -1;
                }

                // KONIEC GRY
                if (gameState == gameOverState){
                    if (ui.gameoverbtns.get(0).txtcollide(e)) {
                        gameState = titleState;
                    }
                    else if (ui.gameoverbtns.get(1).txtcollide(e)) {
                        System.exit(0);
                    }
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // EKRAN STARTOWY
                if (gameState == titleState){
                    if (ui.titlebtns.get(0).txtcollide(e)) {

                        ui.commandNum = 0;
                    }
                    else if (ui.titlebtns.get(1).txtcollide(e)) {
                        ui.commandNum = 1;
                    }
                    else if(ui.titlebtns.get(2).txtcollide(e)) {
                        ui.commandNum = 2;
                    }
                    else {
                        ui.commandNum = -1;
                    }
                }

                // KONIEC GRY
                if (gameState == gameOverState){
                    if (ui.gameoverbtns.get(0).txtcollide(e)) {
                        ui.commandNum = 0;
                    }
                    else if (ui.gameoverbtns.get(1).txtcollide(e)) {
                        ui.commandNum = 1;
                    }
                    else {
                        ui.commandNum = -1;
                    }
                }
            }
        });
    }

    // USTAWIENIA PO URUCHOMIENIU PROGRAMU
    public void setupGame() {
        gameState = titleState;
        playMusic(0);
        musicplaying = true;
    }

    // UTWORZENIE I ROZPOCZECIE WATKU
    public void start(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        // USTAWIENIA ŻEBY GRA DZIAŁAŁA W 60 KLATKACH NA SEKUNDE
        double drawInterval = 1000/FPS;
        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        // PETLA GRY
        while(gameThread != null) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                // Aktualizacja potrzeb zwierzatka
                update();
                // Rysowanie na ekranie zaktualizowanych informacji
                repaint();
                delta --;
            }
        }

    }

    public void update() {
        pet.update();
        ui.savepopup();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        bg.drawframe(g2);
        settingButtons.drawSetBtns(g2);


        // EKRAN POCZĄTKOWY
        if (gameState == titleState) {
            ui.draw(g2);
        }
        else {
            bg.drawbg(g2);
            pet.draw(g2);
            ui.draw(g2);
            menu.drawMenu(g2);
        }


        // EKRAN MINIGIERKI
        if(gameState == minigameState){
            ui.draw(g2);
            if(action != 0){
                ui.drawpetchoice();
            }
        }

        // POPUP ZAPISU GRY
        if(saveScreen){
            ui.draw(g2);
        }

        // KONIEC GRY
        if(gameState == gameOverState){
            ui.draw(g2);
        }

        g2.dispose();
    }

    // MUZYKA
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }
}
