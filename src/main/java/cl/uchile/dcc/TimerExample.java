package cl.uchile.dcc;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.common.Thief;
import cl.uchile.dcc.finalreality.model.object.weapon.Weapon;
import cl.uchile.dcc.finalreality.model.object.weapon.WeaponType;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that is meant for testing.
 *
 * @author <a href="https://www.github.com/vijo30">V30</a>
 * @author ~José Videla~
 */
public class TimerExample {
  /**
   * Testing.
   */

  public static void main(String[] args)
      throws InterruptedException, InvalidStatValueException {
    BlockingQueue<GameCharacter> queue = new LinkedBlockingQueue<>();
    Random rng = new Random();
    for (int i = 0; i < 10; i++) {
      // Gives a random speed to each character to generate different waiting times
      var weapon = new Weapon("", 0, rng.nextInt(50), WeaponType.KNIFE);
      var character = new Thief(Integer.toString(i), 10, 10, queue);
      character.equip(weapon);
      character.waitTurn();
    }
    // Waits for 6 seconds to ensure that all characters have finished waiting
    Thread.sleep(6000);
    while (!queue.isEmpty()) {
      // Pops and prints the names of the characters of the queue to illustrate the turns
      // order
      System.out.println(queue.poll().toString());
    }
  }
}
