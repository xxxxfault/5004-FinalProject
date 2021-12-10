package final_project_HITPLANE;


/**
 * The interface is used for the award that the hero plane will get when it hit the specific type of enemy
 */
public interface EnemyAward {
 //awardType:Fire
 int FIRE = 0;
 //awardType:Life
 int LIFE = 1;
 /**
 * get the type of award
 * @return award type
 */
 int getAwardType();
}
