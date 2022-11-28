package house;

/**
 * Creates and manages window objects.
 */
public class Window {

    private Louver louver;
    private Room room;
    private int number;

    public Window(Room room, int number) {
        this.room = room;
        this.louver = new Louver(this);
        this.number = number;

    }

    public Louver getLouver() {
        return louver;
    }

    @Override
    public String toString() {
        return "Window " + number ;
    }

    public Room getRoom() {
        return room;
    }
}
