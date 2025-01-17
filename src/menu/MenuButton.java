package menu;

import game.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuButton {
    public int x, y, width, height;
    public BufferedImage image;

    public MenuButton(String image, int x, int y) {
        try {
            this.image = ImageIO.read(new File(image));
        }
        catch (IOException e){
            throw new RuntimeException();
        }
        this.x = x;
        this.y = y;
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public boolean collide(MouseEvent mouse) {
        return mouse.getX() > this.x && mouse.getX() < (this.x + this.width) && mouse.getY() > this.y && mouse.getY() < (this.y + this.height);
    }

    public void action() {

    }
}
