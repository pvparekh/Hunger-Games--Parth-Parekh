package games;

/**
 * This class contains a pair person 1 and person 2 that will
 * duel in the Hunger Games
 * 
 * @author Pranay Roni
 */
public class DuelPair {
    private Person person1;
    private Person person2;

    /**
     * Construct a Person Pair by passing 2 Persons as arguments
     * 
     * @param person1
     * @param person2
     */
    public DuelPair(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    /**
     * Construct a Person Pair with null Persons
     */
    public DuelPair() {
        this(null, null);
    }

    /**
     * Gets Person 1
     * 
     * @return Person 1
     */
    public Person getPerson1() {
        return person1;
    }

    /**
     * Gets Person 2
     * 
     * @return Person 2
     */
    public Person getPerson2() {
        return person2;
    }

    /**
     * Sets Person 1
     * 
     * @param person1 updates current person 1
     */
    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    /**
     * Sets {@link #person2}
     * 
     * @param person2 updates current person 2
     */
    public void setPerson2(Person person2) {
        this.person2 = person2;
    }
}
