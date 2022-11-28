package creature;


import events.Notification;
import events.NotificationType;

/**
 * Creates anf manages animals.
 */
public class Animal extends Creature{

    public Animal(String name,Role role) {
        super(name,role);
    }

    /**
     * Controls animal's occupation. Starts new round for an animal.
     */
    @Override
    public void newRound() {
        makeSound();
    }

    /**
     * Creates notification {@link Notification} about where animal made a sound.
     */
    public void makeSound() {
        newNotification(new Notification(NotificationType.ANIMAL_SOUND, getFloor(), actualRoom,this,  this));
    }

    @Override
    public String toString() {
        return "Animal "+ name;
    }

}
