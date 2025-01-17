package menu;

import game.GamePanel;

import java.util.Random;

public class MiniGameBtn extends MenuButton{
    GamePanel gp;
    public static int runda = 0, playerscore, petscore, petaction;
    public MiniGameBtn(String img, int x, int y, GamePanel gp) {
        super(img, x,y);
        this.gp = gp;
    }

    public static void defaultValues(){
        runda = 0;
        playerscore = 0;
        petscore = 0;
    }

    public void petactiondo() {
        Random rand = new Random();
        petaction = rand.nextInt(3); // 0 - papier, 1 - kamien, 2 - nozyce
    }

    @Override
    public void action(){
        petactiondo();
        gp.ui.endMG = false;

        if(playerscore < 3 && petscore < 3){
            if(petaction == 0 && GamePanel.action == 1 || petaction == 1 && GamePanel.action == 2 || petaction == 2 && GamePanel.action == 3){
            }
            else if(petaction == 0 && GamePanel.action == 3 || petaction == 1 && GamePanel.action == 1 || petaction == 2 && GamePanel.action == 2){

                playerscore ++;
            }
            else if(petaction == 0 && GamePanel.action == 2 || petaction == 1 && GamePanel.action == 3 || petaction == 2 && GamePanel.action == 1){

                petscore ++;
            }
            runda ++;
        }

    }
}
