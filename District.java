package games;

import java.util.ArrayList;

/**
 * This class implements the Districts in Hunger Games.
 * 
 * Contains two ArrayLists of Odd Population and Even Population based on birth date.
 * 
 * District ID must be defined at initialization.
 * 
 * @author Pranay Roni
 */
public class District {
    private ArrayList<Person> oddPopulation;
    private ArrayList<Person> evenPopulation;
    private int districtID;

    /**
     * Initializes District with empty odd and even population.
     * 
     * @param id represents this District's ID.
     */
    public District(int id) {
        oddPopulation = new ArrayList<>();
        evenPopulation = new ArrayList<>();
        districtID = id;

    }

    /**
     * Adds a odd to the corresponding ArrayList.
     * @param person to be added to this District's oddPopulation list
     */
    public void addOddPerson(Person person) {
        oddPopulation.add(person);
    }

    /**
     * Adds a even to the corresponding ArrayList.
     * @param person to be added to this District's evenPopulation ArrayList
     */
    public void addEvenPerson(Person person) {
        evenPopulation.add(person);
    }

    /**
     * Gets this District's Odd Population as an ArrayList.
     * 
     * @return the list of odds in the district.
     */
    public ArrayList<Person> getOddPopulation() {
        return oddPopulation;
    }

    /**
     * Gets this District's Even Population as an ArrayList.
     * 
     * @return the list of evens in the district.
     */
    public ArrayList<Person> getEvenPopulation() {
        return evenPopulation;
    }

    /**
     * Replaces odd population with argument
     * 
     * @param op replaces oddPopulation
     */
    public void setOddPopulation(ArrayList<Person> op) {
        oddPopulation = op;
    }

    /**
     * Replaces even population with argument
     * 
     * @param ep replaces evenPopulation
     */
    public void setEvenPopulation(ArrayList<Person> ep) {
        evenPopulation = ep;
    }

    /**
     * Gets total size of people within this district
     * 
     * @return Sum of odd and even populations
     */
    public int size() {
        return oddPopulation.size() + evenPopulation.size();
    }

    /**
     * Gets this District #
     * 
     * @return district ID
     */
    public int getDistrictID() {
        return districtID;
    }

    /**
     * Outputs this district as a String.
     * @return oddPopulation ArrayList evenPopulation ArrayList as String
     */
    public String toString() {
        return "ID: " + districtID + ", Odd Population: " + oddPopulation + ", "
                + "Even Population: " + evenPopulation;
    }
}
