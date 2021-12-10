package final_project_HITPLANE;

import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Create Bee class that inherit FlyerObject and implement EnemyAward interface
 */
public class Bee extends FlyerObject implements EnemyAward {

    private  int xSpeed;
    private final int ySpeed;
    private final int awardType;
    private int index ;

    /**
     * the constructor of bee will initialize a bee object, set the speed and randomly generate the award type of bee
     */
    public Bee() {
        super(70, 70);
        Random rand = new Random();
        awardType = rand.nextInt(2);
        xSpeed = 1;
        ySpeed = 2;
        index=1;
    }

    /**
     * Override the move method and when the bee is close to the bounds let it move backwards
     */
    @Override
    public void move() {
        y += ySpeed;
        x += xSpeed;
        if (x <= 0 || x >= GamePanel.WIDTH - width) {
            xSpeed *= -1;
        }
    }

    /**
     * This method will return the image of Bee object
     * @return the bee image if it is alive / the bom image if it is dead / null if it is remove
     */
    @Override
    public BufferedImage getImage() {

        if (isLive()){
            return Images.bee[0];
        }else if (isDead()){
            BufferedImage img = Images.bee[index++];
            if (index == Images.bee.length){
                state = FlyerState.Remove;
            }
            return img;
        }
        return null;
    }


    /**
     * getter for the award type
     * @return  awardType
     */
    @Override
    public int getAwardType() {
        return awardType;
    }
}