package menu;

import data.SaveLoad;
import game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class SettingButtons extends JPanel {
    GamePanel gp;
    public ArrayList<MenuButton> settingbtns;
    //public String[] setbtnstate;
    //public int btnclicked;
    public SettingButtons(GamePanel gp) {
        this.gp = gp;

        settingbtns = new ArrayList<>();
        //setbtnstate = new String[]{"res/Menu-icons/button.png", "res/Menu-icons/buttonclicked.png"};

        MenuButton savebtn = new MenuButton("res/Menu-icons/savebutton.png",154,558);
        MenuButton musicbtn = new MenuButton("res/Menu-icons/musicbutton.png",396,558);
        MenuButton home = new MenuButton("res/Menu-icons/homebutton.png",276,585);

        settingbtns.add(savebtn);
        settingbtns.add(home);
        settingbtns.add(musicbtn);

    }

    public void drawSetBtns(Graphics2D g2){
        for(int i = 0; i < settingbtns.size(); i++) {
            MenuButton setbtns = settingbtns.get(i);
            g2.drawImage(setbtns.getImage(), setbtns.x, setbtns.y, null);
        }

    }



}
