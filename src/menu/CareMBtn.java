package menu;

import entity.Pet;

public class CareMBtn extends MenuButton{
    public CareMBtn(String img, int x, int y) {
        super(img, x,y);
    }

    @Override
    public void action(){
        if(!Pet.getInstance().playing){
            if(Pet.instance.sleeping){
                Pet.getInstance().sleeping = false;
            }
            else{
                Pet.getInstance().sleep();
                Pet.getInstance().sleeping = true;
            }
        }

    }

}
