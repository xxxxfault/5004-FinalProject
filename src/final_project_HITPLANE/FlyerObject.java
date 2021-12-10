package final_project_HITPLANE;

import java.util.Random;
import java.awt.image.BufferedImage;

/**
 * Create a abstract FlyerObject class that will be inherited by different kind of flyer object
 */
public abstract class FlyerObject {
    // initial state of every object will be live
    protected FlyerState state = FlyerState.Live;

    protected int width;
    protected int height;

    protected int x;
    protected int y;

    /**
     * The constructor for FlyerObject flying from upside to downside
     */
    public FlyerObject(int width, int height) {
        Random rand = new Random();
        this.width = width;
        this.height = height;
        x = rand.nextInt(GamePanel.WIDTH - width);
        y = -height;
    }
    /**
     * The constructor for FlyerObject flying from downside to upside
     */
    public FlyerObject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    /**
     * flyer need to move
     */
    public abstract void move();

    /**
     * get the image for the flyer object
     */
    public abstract BufferedImage getImage();

    /**
     * determine if the flyer still alive
     */
    public boolean isLive() {
        return state == FlyerState.Live;
    }

    /**
     * determine if the flyer dead
     */
    public boolean isDead() {
        return state == FlyerState.Dead;
    }

    /**
     * determine if the flyer is removed
     */
    public boolean isRemove() {
        return state == FlyerState.Remove;
    }

    /**
     * determine if the flyer is out of bound of the game panel
     * @return true if the flyer is out of bound of the game panel otherwise false
     */
    public boolean isOutOfBounds() {
        return y >= GamePanel.HEIGHT;
    }



    /**
     * determine whether the two object hit
     * this:enemy flyer object: Bee, EnemyPlane, BigEnemyPlane
     * other:HeroPlane, Bullet
     *
     * @return true if the two FlyerObject hit
     */
    public boolean isHit(FlyerObject other) {

        int x = other.x;
        int y = other.y;
     return x > this.x - other.width && x <= this.x + this.width &&
             y >= this.y - other.height && y <= this.y + this.height;
    }

    /**
     * set the flyer state of the object dead
     */
    public void goDead() {
        state = FlyerState.Dead;
    }
}