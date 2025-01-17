package game;

import background.Background;
import entity.Pet;
import menu.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font Ret;
    public BufferedImage titlebg;
    public int commandNum = -1;
    public ArrayList<TitleBtns> titlebtns, gameoverbtns;
    public static ArrayList<MenuButton> minigamebtns;
    public String[] petactionchoice = new String[]{"paper", "rock", "scissors"};
    Timer popuptimer, endMGtimer;
    TimerTask popupTimerTask, endMGtask;
    public boolean popup;
    public static boolean endMG;


    public UI(GamePanel gp){
        this.gp = gp;
        titlebtns = new ArrayList<>();
        gameoverbtns = new ArrayList<>();
        minigamebtns = new ArrayList<>();


        try {
            InputStream is = getClass().getResourceAsStream("/Font/retganon.ttf");
            Ret = Font.createFont(Font.TRUETYPE_FONT,is);
            titlebg = ImageIO.read(new File("res/Background/bg2.png"));
        }
        catch (FontFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int x = 195;
        int y = 415;
        MiniGameBtn paperbtn = new MiniGameBtn( "res/MG-icons/paper.png", x, y, gp);
        MiniGameBtn rockbtn = new MiniGameBtn( "res/MG-icons/rock.png", x+80, y, gp);
        MiniGameBtn scissorsbtn = new MiniGameBtn( "res/MG-icons/scissors.png", x+160, y, gp);

        minigamebtns.add(paperbtn);
        minigamebtns.add(rockbtn);
        minigamebtns.add(scissorsbtn);

    }



    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(Ret);
        g2.setColor(Color.lightGray);

        if(gp.pet.sleeping){
            lightturnoff();
        }

        // POTRZEBY ZWIERZAKA
        if (gp.gameState == gp.playState || gp.gameState == gp.gameOverState || gp.saveScreen == true) {
            drawPetStats();
        }

        // GRA ZAPISANA
        if(gp.saveScreen){
            drawSaveScreen();
        }

        // EKRAN POCZATKOWY
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // MINIGIERKA
        if(gp.gameState == gp.minigameState){
            drawMiniGameScreen();
        }

        // KONIEC GRY
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }

    public void drawPetStats() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));
        int textX = 167;
        int vtextX = textX + 100;
        int textY = 488;
        final int lineHeight = 22;

        g2.drawString("Glod: ",textX, textY);
        g2.drawString(Integer.toString(Pet.getInstance().hungerlvl),vtextX,textY);
        textY += lineHeight;
        g2.drawString("Pragnienie: ",textX, textY);
        g2.drawString(Integer.toString(Pet.getInstance().thirstlvl),vtextX,textY);
        textY -= lineHeight;
        textX += 145;
        vtextX += 145;
        g2.drawString("Zmeczenie: ",textX, textY);
        g2.drawString(Integer.toString(Pet.getInstance().tirednesslvl),vtextX,textY);
    }

    public void drawTitleScreen() {
        // TLO
        g2.drawImage(titlebg,155,227,null);
        g2.setColor(new Color(40, 7, 24, 123));
        g2.fillRect(155,227,titlebg.getWidth(),titlebg.getHeight());


        // TYTUL GRY
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String text = "TAMAGOCZI";
        int x = getXforCenteredText(text);
        int y = 307;

        // CIEN
        g2.setColor(new Color(40, 7, 24, 183));
        g2.drawString(text,x+3,y+3);

        // GLOWNY KOLOR
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //MENU TLO
        y += 20;
        g2.setColor(new Color(40, 7, 24, 224));
        g2.fillRect(198,y,205,150);

        // MENU
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
        text = "NOWA GRA";
        x = 230;
        y += 50;
        //g2.drawString(text,x,y);
        TitleBtns newgame = new TitleBtns(text, x, y);
        titlebtns.add(newgame);
        if(commandNum == 0){
            g2.drawString(">",x-20,y-2);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
        text = "WCZYTAJ GRE";
        y += 40;
        TitleBtns loadgame = new TitleBtns(text, x, y);
        titlebtns.add(loadgame);
        if(commandNum == 1){
            g2.drawString(">",x-20,y-2);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
        text = "WYJDZ";
        y += 40;
        TitleBtns exitgame = new TitleBtns(text, x, y);
        titlebtns.add(exitgame);
        if(commandNum == 2){
            g2.drawString(">",x-20,y-2);
        }



        for(int i = 0; i < titlebtns.size(); i++) {
            TitleBtns titlebutton = titlebtns.get(i);
            g2.drawString(titlebutton.getstr(), titlebutton.x, titlebutton.y);
        }
    }

    public void drawGameOverScreen() {
        // TLO
        //g2.drawImage(titlebg,155,227,null);
        g2.setColor(new Color(0, 0, 0, 136));
        g2.fillRect(155,227,titlebg.getWidth(),titlebg.getHeight());
        //
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(186,295,226,155);

        // ZWIERZAK UCIEKŁ
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        String text = "ZWIERZAK UCIEKL :(";
        int x = getXforCenteredText(text);
        int y = 337;

        // CIEN
        g2.setColor(Color.black);
        g2.drawString(text,x+3,y+3);

        // GLOWNY KOLOR
        g2.setColor(Color.white);
        g2.drawString(text,x,y);


        // RESTART
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
        text = "RESTART";
        x = 230;
        y += 50;
        TitleBtns restartgame = new TitleBtns(text, x, y);
        gameoverbtns.add(restartgame);
        if(commandNum == 0){
            g2.drawString(">",x-20,y-2);
        }

        // WYJDZ
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));
        text = "WYJDZ";
        y += 40;
        TitleBtns exitgame = new TitleBtns(text, x, y);
        gameoverbtns.add(exitgame);
        if(commandNum == 1){
            g2.drawString(">",x-20,y-2);
        }

        for(int i = 0; i < gameoverbtns.size(); i++) {
            TitleBtns gobutton = gameoverbtns.get(i);
            g2.drawString(gobutton.getstr(), gobutton.x, gobutton.y);
        }

    }

    public void drawSaveScreen(){
        int x = 155;
        int y = 329;
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(x,y,290,105);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
        String text = "Stan gry zostal zapisany!";
        g2.drawString(text, getXforCenteredText(text), y+60);
    }

    public void savepopuptimer(){
        popuptimer = new Timer();
        popupTimerTask = new TimerTask() {
            //int i=0;
            @Override
            public void run() {
                if(gp.saveScreen){
                    //i++;
                    //if (i>1){
                        popup = false;
                        //i = 0;
                        popuptimer.cancel();
                        popuptimer.purge();
                    //}
                }
            }
        };
        popuptimer.schedule(popupTimerTask,0,1500);

    }

    public void savepopup(){
        if(popup){
            gp.saveScreen = true;
        }
        else {
            gp.saveScreen = false;
        }
    }

    public void drawMiniGameScreen(){
        g2.setColor(new Color(97, 41, 47, 142));
        g2.fillRect(155,415,290,50);

        int x = 195;
        int y = 415;


        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));
        String text = "";
        y += 37;
        if (MiniGameBtn.playerscore == 3){
            text = "Wygrales!";
            g2.drawString(text,getXforCenteredText(text), y);
            endMG = true;
        }
        else if(MiniGameBtn.petscore == 3){
            text = "Zwierzatko wygralo!";
            g2.drawString(text,getXforCenteredText(text), y);
            endMG = true;
        }
        else{
            for(int i = 0; i < minigamebtns.size(); i++) {
                MenuButton menubutton = minigamebtns.get(i);
                g2.drawImage(menubutton.getImage(), menubutton.x, menubutton.y, null);
            }
        }


        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));
        x = 167;
        y = 488;

        g2.drawString("Gracz ",x, y);
        g2.drawString(Pet.getInstance().name,getXforMiniGMText(Pet.getInstance().name), y);
        g2.setFont(g2.getFont().deriveFont(30F));
        x += 100;
        y += 15;
        g2.drawString(Integer.toString(MiniGameBtn.playerscore),x,y);
        g2.drawString(":",x+30,y-3);
        g2.drawString(Integer.toString(MiniGameBtn.petscore),x+60,y);

        endMGtimer = new Timer();
        endMGtask = new TimerTask() {
            @Override
            public void run() {
                if(endMG){
                    endMG = false;
                    gp.gameState = gp.playState;
                    gp.pet.playing = false;
                    MiniGameBtn.defaultValues();
                    GamePanel.action = 0;
                    endMGtimer.cancel();
                    endMGtimer.purge();
                }
            }
        };
        //endMGtimer.schedule(endMGtask,10000,2500);

    }

    public void drawpetchoice(){
        int x = 340;
        int y = 310;

        try{
            g2.drawImage(ImageIO.read(new File("res/MG-icons/dymek.png")), x, y, null);
            x += 5;
            y += 10;
            if(MiniGameBtn.petaction==1){
                x += 3;
            }
            g2.drawImage(ImageIO.read(new File("res/MG-icons/" + petactionchoice[MiniGameBtn.petaction] + ".png")),x,y,null);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void lightturnoff(){
            g2.setColor(new Color(9, 6, 37, 180));
            g2.fillRect(155,227,290,290);
            g2.drawImage(Background.bgmenu,155,227,290,290,null);
            g2.drawImage(Background.bgicons,155,227,290,290,null);
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.getWidth()/2 - length/2;
        return x;
    }

    public int getXforMiniGMText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = (gp.getWidth() - Background.bg.getWidth())/2 + Background.bg.getWidth() - length - 8;
        return x;
    }
}
