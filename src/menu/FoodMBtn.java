package menu;

import entity.Pet;

public class FoodMBtn extends MenuButton{

    public FoodMBtn(String img, int x, int y) {
        super( img, x,y);
    }

    @Override
    public void action(){
        if (!Pet.instance.sleeping && !Pet.instance.playing && !Pet.instance.drinking) {
            Pet.getInstance().feeding();
            Pet.getInstance().eating = true;
        }


    }
}
