package final_project_HITPLANE;

import java.awt.image.BufferedImage;

/**
 * The hero plane class is a kind of object in this game , thus inherited from flyer object
 */
public class HeroPlane extends FlyerObject {

    private int life;// number of life
    private int fire;// fire strength
    private Bullet[] bs;


    /**
     * the constructor to initialize the hero plane
     */
    public HeroPlane() {
        super(150, 150, 140, 400);
        fire = 0;
        life = 3;
    }

    /**
     * The hero plane will move with the mouse,
     * so there are no specific implementation
     */
    @Override
    public void move() {
    }


    /**
     * get image of hero plane, and it will change between two same image every 10ms
     */
    @Override
    public BufferedImage getImage() {
        return Images.hero;

    }

    /**
     * create bullet objects for hero plane
     */
    public Bullet[] shoot() {
        // relative position of bullet
        int xStep = this.width / 4;
        int yStep = 5;

        if (fire > 0) {
            bs = new Bullet[3];
            bs[0] = new Bullet(this.x +  xStep, this.y - yStep);
            bs[1] = new Bullet(this.x + 2 * xStep, this.y - yStep);
            bs[2] = new Bullet(this.x + 3 * xStep, this.y - yStep);
            fire -= 2;
        } else {
            bs = new Bullet[1];
            bs[0] = new Bullet(this.x + 2 * xStep, this.y - yStep);
        }
        return bs;
    }

    /**
     * let hero plane's center move with the mouse
     *
     * @param x the mouse's x position
     * @param y the mouse's y position
     */
    public void moveTo(int x, int y) {
        this.x = x - this.width / 2;
        this.y = y - this.height / 2;
    }

    /**
     * add hero plane's life with 1
     */
    public void addLife() {
        life++;
    }

    /**
     * get hero plane's current life
     *
     * @return hero plane's current life
     */
    public int getLife() {
        return life;
    }

    /**
     * reduce hero plane's life with 1
     */
    public void subtractLife() {
        life--;
    }

    /**
     * add hero plane's fire strength with 10
     */
    public void addFire() {
        fire += 10;
    }

    /**
     * clear all the fire strength and set it to 0
     */
    public void clearFire() {
        fire = 0;
    }

    /**
     * get hero plane's current fire strength
     *
     * @return hero plane's current fire strength
     */
    public int getFire() {
        return fire;
    }


}
