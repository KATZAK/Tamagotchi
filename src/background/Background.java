package background;

import entity.Pet;
import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    GamePanel gp;
    public static BufferedImage bgframe, bg, bgmenu, bgicons;

    public Background(GamePanel gp) {
        this.gp = gp;
        getImage();
    }

    // WCZYTANIE OBRAZKÓW TŁA
    public void getImage() {
        try {
            bgframe = ImageIO.read(new File("res/Background/bgframe.png"));
            bg = ImageIO.read(new File("res/Background/bg1.png"));
            bgmenu = ImageIO.read(new File("res/Background/menubg.png"));
            bgicons = ImageIO.read(new File("res/Background/iconbg.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // RYSOWANIE RAMKI
    public void drawframe(Graphics2D g2) {
        g2.drawImage(bgframe,0,0,null);
    }

    // RYSOWANIE TŁA I MENU
    public void drawbg(Graphics2D g2) {
        g2.drawImage(bg,155,227,null);
        g2.drawImage(bgmenu,155,227,null);
        g2.drawImage(bgicons,155,227,null);
    }

}
