package menu;

import entity.Pet;

public class WardrobeMBtn extends MenuButton{
    public WardrobeMBtn(String img, int x, int y) {
        super(img, x,y);
    }

    @Override
    public void action(){
        Pet.getInstance().wardrobe();
    }
}
