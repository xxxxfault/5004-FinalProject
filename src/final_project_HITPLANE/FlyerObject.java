package final_project_HITPLANE;

import java.util.Random;
import java.awt.image.BufferedImage;

public abstract class FlyerObject {
    //所有对象都有三种状态：活着的，死了的，及删除的
    //这里之所以选择用常量表示状态是因为首先状态是一个不需要去修改的值
    //其次状态需要反复使用所以结合这两个特点，我选择了使用常量表示
    //state是用来表示当前状态的，每个对象都有一个实时的状态，此状态是会改变的，且初始状态都是活着的
    public static final FlyerState live = FlyerState.Live;//活着的
    public static final FlyerState dead = FlyerState.Dead;//死了的
    public static final FlyerState remove = FlyerState.Remove;//删除的
    protected FlyerState state = FlyerState.Live;//当前状态（默认状态为活着的）

    //每个对象都是一张图片，既然是图片那么就一定有宽高，其次因为每个对象都是会随时移动的 即为都有x，y坐标
    protected int width;//宽
    protected int height;//高

    protected int x;//左右移动（x坐标）
    protected int y;//上下移动（y坐标）

    /**
     * 飞行物移动（抽象）
     * 每个飞行物都是会移动的，但是移动方式不同
     * 所以这里就将共有的行为抽到了超类中
     * 但是设置成了抽象方法，实现了多态的效果
     */
    public abstract void step();

    /**
     * 获取图片（抽象）
     * 所有对象都是图片但图片不相同所以抽象化了
     */
    public abstract BufferedImage getImage();

    /**
     * 判断对象是否是活着的
     */
    public boolean isLive() {
        return state == live;
    }

    /**
     * 判断对象是否是死了的
     */
    public boolean isDead() {
        return state == dead;
    }

    /**
     * 判断对象是否删除了
     */
    public boolean isRemove() {
        return state == remove;
    }

    /**
     * 判断对象(大敌机，小敌机，小蜜蜂)是否越界
     * 当敌人越界我们就需要删除它否则程序越执行越卡，会出现内存泄露的问题，此方法就是为后续删除越界对象做铺垫的
     *
     * @return
     */
    public boolean isOutOfBounds() {
        return y >= GamePanel.HEIGHT;
    }

    /**
     * 给小/大敌机，小蜜蜂提供的
     * 因为三种飞行物的宽，高不同所以不能写死。
     * 若三种飞行物的宽，高相同，那么就可以将宽，高写死
     */
    public FlyerObject(int width, int height) {
        Random rand = new Random();
        this.width = width;
        this.height = height;
        x = rand.nextInt(GamePanel.WIDTH - width);//x:0到负的width长度的之间的随机数
        y = -height;//y：负的height高度
    }

    /**
     * 给天空，子弹，英雄机提供的
     * 因为英雄机，子弹，天空的宽，高，x，y都是不同的，所以数据不能写死，需要传参
     */
    public FlyerObject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    /**
     * 检测碰撞
     * this:敌人（小敌机/小蜜蜂/大敌机）
     * other:子弹/英雄机
     *
     * @return
     */
    public boolean isHit(FlyerObject other) {
        int x1 = this.x - other.width;//x1：敌人的x-英雄机/子弹的宽
        int x2 = this.x + this.width;//x2：敌人的x加上敌人的宽
        int y1 = this.y - other.height;//y1：敌人的y-英雄机/子弹的高
        int y2 = this.y + this.height;//y2：敌人的y加上敌人的高
        int x = other.x;//x：英雄机/子弹的x
        int y = other.y;//y：英雄机/子弹的y
 /*
 x在x1与x2之间 并且 y在y1与y2之间，即为撞上了
 */
        return x > x1 && x <= x2 && y >= y1 && y <= y2;
    }

    /**
     * 飞行物死亡
     */
    public void goDead() {
        state = dead;//将当前状态修改为死了的
    }
}