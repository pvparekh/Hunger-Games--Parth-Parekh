package games;

/**
 * This class defines a Person in the Hunger Games
 * Contains birth month, first name, last name, id, Tessera eligibility, and effectiveness value.
 * @author Pranay Roni
 */

public class Person implements Comparable<Person> {
    private String  firstName;
    private String  lastName;
    private int     birthMonth;
    private int     age;
    private int     districtID;      // the district the person belongs to
    private boolean tessera;         // if true this person has volunteered to participage in the games
    private int     effectiveness;   // the higher the value the higher the chances of winning a duel

    /**
     * Default Constructor for creating a Person.
     * 
     * @param birthMonth is defined to be as male or female.
     * @throws IllegalArgumentException birthMonth is defined is a value that is NOT 0 or 1
     * @param firstName     String representing Person's First Name
     * @param lastName      String representing Person's Last Name
     * @param age           integer representing Person's age
     * @param districtID    integer representing Person's corresponding district
     * @param effectiveness integer representing Person's effectiveness when dueling
     * 
     */
    public Person(int birthMonth, String firstName, String lastName, int age, int districtID, int effectiveness)
            throws IllegalArgumentException {
        if (birthMonth <= 0 ||birthMonth > 12)
            throw new IllegalArgumentException(
                    "Birth month must be a valid month. \n"
                            + "Got instead: " + birthMonth);
        this.birthMonth = birthMonth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.districtID = districtID;
        this.effectiveness = effectiveness;
    }

    /**
     * Gets birthMonth
     * 
     * @return this Person's birthMonth
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /**
     * Gets First Name
     * 
     * @return this Person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets Last Name
     * 
     * @return this Person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets Age
     * 
     * @return this Person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the District # of this person
     * 
     * @return this Person's ID of the district this Person belongs to
     */
    public int getDistrictID() {
        return districtID;
    }

    /**
     * Gets the effectiveness of this person in combat when calling fight(person).
     * 
     * @return effectiveness of this Person
     */
    public int getEffectiveness() {
        return effectiveness;
    }

    /**
     * Allows the abililty to set this Person's Tessera
     * 
     * @param tessera will define this object's Tessera as true or false
     */
    public void setTessera(boolean tessera) {
        this.tessera = tessera;
    }

    /**
     * Returns this Person's Tessera
     * 
     * @return Tessera of this Person
     */
    public boolean getTessera() {
        return tessera;
    }

    /**
     * Grows this person by incrementing age by 1
     */
    public void grow() {
        age++;
    }


    /**
     * Gets this Person's Full Name
     * 
     * @return first name and last name as String
     */
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * A scalar between 0 and 2.0 is applied on both Persons' EFFECIVENESS,
     * highest resulting rating will win the encounter.
     * 
     * @param person2 will be compared to current Person for this encounter
     * @return person such that person has the greater Effectiveness
     *         after luck is applied than the other person i.e Winner of Fight
     **/
    public Person duel(Person person2) {

        double luckPerson1 = StdRandom.uniform(0, 2.0);
        double luckPerson2 = StdRandom.uniform(0, 2.0);

        double person1Effectiveness = luckPerson1 * this.effectiveness;
        double person2Effectiveness = luckPerson2 * person2.getEffectiveness();

        if ( person1Effectiveness > person2Effectiveness ) {
            return this;
        } else if ( person1Effectiveness < person2Effectiveness ) {
            return person2;
        } else {
            // oops, both are set to win the.
            // Let us use a 50% chance: <0.5 Person1 wins, >=0.5 Person2 wins
            double chance = StdRandom.uniform(0, 1.0);
            return ( chance < 0.50) ? this : person2;
        }
    }

    /**
     * Allows for Person to be compared to with another Person by
     * comparing districtID's and full names
     * 
     * Useful for using Collections.sort on a list of this type.
     * 
     * @param person2
     * @return Integer value representing natural ordering of Person with Person 2.
     */
    public int compareTo(Person person2) {
        Integer person1ID = districtID;
        Integer person2ID = person2.getDistrictID();

        return (person1ID.compareTo(person2ID) != 0 ? person1ID.compareTo(person2ID)
                : toString().compareTo(person2.toString()));
    }

}
