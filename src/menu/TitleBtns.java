package menu;

import java.awt.event.MouseEvent;

public class TitleBtns {
    public int x, y;
    String text;

    public TitleBtns(String text, int x, int y){
        this.text = text;
        this.x = x;
        this.y = y;

    }

    public String getstr(){
        return text;
    }

    public boolean txtcollide(MouseEvent mouse) {
        return mouse.getX() > (this.x) && mouse.getX() < (this.x + 160) && mouse.getY() > (this.y - 35) && mouse.getY() < (this.y);
    }
}
