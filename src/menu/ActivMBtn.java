package menu;

import entity.Pet;
import game.GamePanel;
import game.UI;

public class ActivMBtn extends MenuButton{
    public ActivMBtn(String img, int x, int y) {
        super(img, x,y);
    }

    @Override
    public void action(){
        if(!Pet.instance.sleeping){
            if(Pet.getInstance().playing){
                Pet.getInstance().playing = false;
                GamePanel.gameState = GamePanel.playState;
                UI.endMG = false;
            }
            else{
                GamePanel.gameState = GamePanel.minigameState;
                MiniGameBtn.defaultValues();
                GamePanel.action = 0;
                Pet.getInstance().playing = true;
            }
            if(GamePanel.action != 0 ){
                Pet.getInstance().petminigame();
            }
        }
    }
}
