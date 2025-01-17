package menu;

import game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menubtns extends JPanel {

    int spacebetweenicons = 58;
    public ArrayList<MenuButton> buttons;

    public Menubtns() {
        buttons = new ArrayList<>();

        int x = 160;
        int y = 228;
        FoodMBtn foodbutton = new FoodMBtn( "res/Menu-icons/food-icon.png", x, y);
        x += spacebetweenicons;
        DrinkMBtn drinkbutton = new DrinkMBtn( "res/Menu-icons/drink-icon.png", x, y);
        x += spacebetweenicons;
        ActivMBtn activitiesbutton = new ActivMBtn("res/Menu-icons/activities-icon.png", x, y);
        x += spacebetweenicons;
        CareMBtn carebutton = new CareMBtn("res/Menu-icons/care-icon.png", x, y);
        x += spacebetweenicons;
        WardrobeMBtn wadrobebutton = new WardrobeMBtn("res/Menu-icons/wardrobe-icon.png", x, y);

        buttons.add(foodbutton);
        buttons.add(drinkbutton);
        buttons.add(activitiesbutton);
        buttons.add(carebutton);
        buttons.add(wadrobebutton);

    }


    public void drawMenu(Graphics2D g2) {
        for(int i = 0; i < buttons.size(); i++) {
            MenuButton menubutton = buttons.get(i);
            g2.drawImage(menubutton.getImage(), menubutton.x, menubutton.y, null);
        }
    }
}
