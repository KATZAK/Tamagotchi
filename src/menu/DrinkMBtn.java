package menu;

import entity.Pet;

public class DrinkMBtn extends MenuButton{
    public DrinkMBtn(String img, int x, int y) {
        super(img, x,y);
    }

    @Override
    public void action(){
        if(!Pet.getInstance().sleeping && !Pet.instance.playing && !Pet.instance.eating){
            Pet.getInstance().drink();
            Pet.getInstance().drinking = true;
        }
    }
}
