package games;

import java.util.ArrayList;

/**
 * This class contains methods to represent the Hunger Games using BSTs.
 * Moves people from input files to districts, eliminates people from the game,
 * and determines a possible winner.
 * 
 * @author Pranay Roni
 * @author Maksims Kurjanovics Kravcenko
 * @author Kal Pandit
 */
public class HungerGames {

    private ArrayList<District> districts;  // all districts in Panem.
    private TreeNode            game;       // root of the BST. The BST contains districts that are still in the game.

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * Default constructor, initializes a list of districts.
     */
    public HungerGames() {
        districts = new ArrayList<>();
        game = null;
        StdRandom.setSeed(2023);
    }

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * Sets up Panem, the universe in which the Hunger Games takes place.
     * Reads districts and people from the input file.
     * 
     * @param filename will be provided by client to read from using StdIn
     */
    public void setupPanem(String filename) { 
        StdIn.setFile(filename);  // open the file - happens only once here
        setupDistricts(filename); 
        setupPeople(filename);
    }

    /**
     * Reads the following from input file:
     * - Number of districts
     * - District ID's (insert in order of insertion)
     * Insert districts into the districts ArrayList in order of appearance.
     * 
     * @param filename will be provided by client to read from using StdIn
     */
    public void setupDistricts (String filename) {
        districts = new ArrayList<District>();
        int numofdistricts=StdIn.readInt();
        int id=StdIn.readInt();
        District district = null; 
        int count = 1;
        while(count<=numofdistricts) { 
            district = new District(id);
            districts.add(district);
            if(count!= numofdistricts) { 
                id = StdIn.readInt();

            }
            count++;
        }


    }
    
    /**
     * Reads the following from input file (continues to read from the SAME input file as setupDistricts()):
     * Number of people
     * Space-separated: first name, last name, birth month (1-12), age, district id, effectiveness
     * Districts will be initialized to the instance variable districts
     * 
     * Persons will be added to corresponding district in districts defined by districtID
     * 
     * @param filename will be provided by client to read from using StdIn
     */
    public void setupPeople (String filename) {
    int numofpeople = StdIn.readInt();
    for (int j = 0; j<numofpeople; j++) { 
        String firstName= StdIn.readString();
        String lastName = StdIn.readString();
        int birthMonth= StdIn.readInt();
        int age = StdIn.readInt();
        int districtID = StdIn.readInt();
        int effectiveness = StdIn.readInt();
        Person personObj = new Person(birthMonth, firstName, lastName, age, districtID, effectiveness);
     // next, test for volunteers 
        
    if (age >=12 && age<18 ) { 
        personObj.setTessera(true);   
    }
    for (District district : districts) {
        if (district.getDistrictID() ==districtID) {  
        if (birthMonth % 2 == 0) { //test for even
                district.addEvenPerson(personObj);  
            }else {
                district.addOddPerson(personObj);  
            }
           break;
        }
    }
}
}
    /**
     * Adds a district to the game BST.
     * If the district is already added, do nothing
     * 
     * @param root        the TreeNode root which we access all the added districts
     * @param newDistrict the district we wish to add
     */
    public void addDistrictToGame(TreeNode root, District newDistrict) {

        // WRITE YOUR CODE HERE
        TreeNode ptr= game;
        TreeNode prev=null;
        TreeNode newNode= new TreeNode(newDistrict, null, null);
        
        
        if(game==null){
            game=newNode;
            
            for(int i=0; i<districts.size();i++){
                if(districts.get(i).equals(newDistrict)){
                    districts.remove(i);
                }
            }
            return;
        }

        while(ptr!=null){
            if( newDistrict.getDistrictID()<ptr.getDistrict().getDistrictID()){
                prev=ptr;
                ptr=ptr.getLeft();
                if(ptr==null){
                    prev.setLeft(newNode);
                    for(int i=0; i<districts.size();i++){
                        if(districts.get(i).equals(newDistrict)){
                        districts.remove(i);
                        }
                    }
                    return;
                }
            }
            if( newDistrict.getDistrictID()>ptr.getDistrict().getDistrictID()){
                prev=ptr;
                ptr=ptr.getRight();
                if(ptr==null){
                    prev.setRight(newNode);
                    for(int i=0; i<districts.size();i++){
                        if(districts.get(i).equals(newDistrict)){
                        districts.remove(i);
                        }
                    }
                    return;
                }
            }
        }
    }
    
    
    
    


    /**
     * Searches for a district inside of the BST given the district id.
     * 
     * @param id the district to search
     * @return the district if found, null if not found
     */
    public District findDistrict(int id) { //public method that calls private recursive method
        //System.out.println("Searching for district with ID: " + id);
        return findDistrictRecursive(game, id);
    }
    private District findDistrictRecursive(TreeNode current,int id) {
        
        if (current == null) {
            //System.out.println("Reached a null node, district not found");
            return null; 
        }
       // System.out.println("Checking node with district ID: " + current.getDistrict().getDistrictID());
        int currentID = current.getDistrict().getDistrictID();
        if (currentID == id ) {
            //System.out.println("Found district with ID: " + id);
            return current.getDistrict();
        }
        else if(currentID>id) {
            //System.out.println("Going left"); // Debug statement
            return findDistrictRecursive(current.getLeft(), id); 
        }
        else {
           // System.out.println("Going right"); // Debug statement
            return findDistrictRecursive(current.getRight(), id);
        
    }
       
    }

    /**
     * Selects two duelers from the tree, following these rules:
     * - One odd person and one even person should be in the pair.
     * - Dueler with Tessera (age 12-18, use tessera instance variable) must be
     * retrieved first.
     * - Find the first odd person and even person (separately) with Tessera if they
     * exist.
     * - If you can't find a person, use StdRandom.uniform(x) where x is the respective 
     * population size to obtain a dueler.
     * - Add odd person dueler to person1 of new DuelerPair and even person dueler to
     * person2.
     * - People from the same district cannot fight against each other.
     * 
     * @return the pair of dueler retrieved from this method.
     */
    public DuelPair selectDuelers() {
        // WRITE YOUR CODE HERE
        ArrayList <District> allDistricts= new ArrayList<>();
        Person person1 = null;
        Person person2 = null;
       if (game ==null)
           return null;
       preOrderTraverse (game, allDistricts);
      
      for (District cur: allDistricts) {
           if(cur.size() ==0 ) {
               continue;
           }
           ArrayList<Person> evenPopulation = cur.getEvenPopulation();
           ArrayList<Person> oddPopulation = cur.getOddPopulation();
           ArrayList<Person> all = new ArrayList<>();
           for (Person y : evenPopulation) {
               all.add(y);
           }
           for (Person n : oddPopulation) {
               all.add(n);
           }
           for (Person i : all) {  //prioritize even or odd person with tessera: true
               if(i.getTessera()== true && i.getBirthMonth()%2 == 0 && person1 == null) {
                   person1 = i;
                   findDistrict(person1.getDistrictID()).getEvenPopulation().remove(person1);
                   break;
           }
               if (i.getTessera()==true && i.getBirthMonth()%2 != 0 && person2 == null) {
                   person2 = i;
                   findDistrict(person2.getDistrictID()).getOddPopulation().remove(person2);
                   break;
               }


           }
   }
           if(person1 == null) {  //person with tessera was not found
               for (District x: allDistricts) {
                   if(x.size()!=0 && !x.getEvenPopulation().isEmpty()) {
                       ArrayList<Person> evenPopulation = x.getEvenPopulation();
                       int random = StdRandom.uniform(evenPopulation.size());
                       person1 = evenPopulation.get(random);
                       findDistrict(person1.getDistrictID()).getEvenPopulation().remove(person1);
                       //next, check to make sure they are not in the same district
                       if (person1 != null && person1.getDistrictID()== person2.getDistrictID()) {
                           continue;
                       }
                       break;
                   }


               }


           }
           if(person2 ==null ) {
               for (District y: allDistricts) {
                   if(y.size() !=0 && !y.getOddPopulation().isEmpty()) {
                   ArrayList<Person> oddPopulation = y.getOddPopulation();
                   int random = StdRandom.uniform(oddPopulation.size());
                   person2 = oddPopulation.get(random);
                   findDistrict(person2.getDistrictID()).getEvenPopulation().remove(person2);
                   //make sure duel pair is not in the same district
                   if(person2 !=null && person2.getDistrictID()==person1.getDistrictID()) {
                       continue;
                   }
              
                       break;
                   }


                  
               }


               }
               DuelPair duo = new DuelPair(person2, person1);
               return duo;
   }






      


       private void preOrderTraverse(TreeNode current, ArrayList<District> alldistricts) {
           if (current ==null) return;
               alldistricts.add(current.getDistrict());
               preOrderTraverse(current.getLeft(), alldistricts);
               preOrderTraverse(current.getRight(), alldistricts);
        
}



        

         
    




    /**
     * Deletes a district from the BST when they are eliminated from the game.
     * Districts are identified by id's.
     * If district does not exist, do nothing.
     * 
     * This is similar to the BST delete we have seen in class.
     * 
     * @param id the ID of the district to eliminate
     */

     
    
     public void eliminateDistrict(int id) {
        game = deleteNode(game, id);
    }
    
    private TreeNode deleteNode(TreeNode root, int id) {
        if (root == null) {
            return null;
        }
    
        // Find the node to be deleted
        if (id < root.getDistrict().getDistrictID()) {
            root.setLeft(deleteNode(root.getLeft(), id));
        } else if (id > root.getDistrict().getDistrictID()) {
            root.setRight(deleteNode(root.getRight(), id));
        } else {
            // Node with only one child or no child
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
    
            // Node with two children: Get the inorder successor (smallest in the right subtree)
            TreeNode successor = findSmallest(root.getRight());
    
            // Delete the inorder successor and get the updated right subtree
            TreeNode updatedRightSubtree = deleteNode(root.getRight(), successor.getDistrict().getDistrictID());
    
            // Place the successor at the root of this subtree
            successor.setLeft(root.getLeft());
            successor.setRight(updatedRightSubtree);
    
            // Return the successor as the new root of this subtree
            return successor;
        }
    
        return root;
    }
    
    
    private TreeNode findSmallest(TreeNode root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }
    
    
    
    
    
    
    
    


    
    

    /**
     * Eliminates a dueler from a pair of duelers.
     * - Both duelers in the DuelPair argument given will duel
     * - Winner gets returned to their District
     * - Eliminate a District if it only contains a odd person population or even
     * person population
     * 
     * @param pair of persons to fight each other.
     */
    public void eliminateDueler(DuelPair pair) {
       
        if (pair.getPerson1() ==null ||pair.getPerson2()==null) {
            // Incomplete duel pairs should not be processed
            return;
        }
        // Remove both duelers from their districts before dueling
        removeDuelerFromDistrict(pair.getPerson1());
        removeDuelerFromDistrict(pair.getPerson2());
   
        // Conduct the duel
        Person winner =pair.getPerson1().duel(pair.getPerson2());
        Person loser= (winner ==pair.getPerson1()) ? pair.getPerson2() : pair.getPerson1();
   
        // Add the winner back to their district's population
        District winnerDistrict =findDistrict(winner.getDistrictID());
        if (winnerDistrict != null) {
            if (winner.getBirthMonth() % 2 == 0) {
                winnerDistrict.getEvenPopulation().add(winner);
            } else {
                winnerDistrict.getOddPopulation().add(winner);
            }
        }
   
        // Check loser's district population and eliminate the district if either population has reached zero
        District loserDistrict = findDistrict(loser.getDistrictID());
        if (loserDistrict != null) {
            if (loserDistrict.getEvenPopulation().isEmpty() || loserDistrict.getOddPopulation().isEmpty()) {
                eliminateDistrict(loser.getDistrictID());
            }
        }
        // If the winner and loser are from the same district, check the winner's district again
        if (winnerDistrict != null && (winnerDistrict.getEvenPopulation().isEmpty() || winnerDistrict.getOddPopulation().isEmpty())) {
            eliminateDistrict(winner.getDistrictID());
        }
    }
    private void removeDuelerFromDistrict(Person dueler) {
      District district = findDistrict(dueler.getDistrictID());
      if (district != null) {
         if (dueler.getBirthMonth() % 2 == 0) {
            district.getEvenPopulation().removeIf(p -> p.equals(dueler));
            } else {
                district.getOddPopulation().removeIf(p -> p.equals(dueler));
            }
        }
    }


    
    
    
    
    
    
    
   
    

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * 
     * Obtains the list of districts for the Driver.
     * 
     * @return the ArrayList of districts for selection
     */
    public ArrayList<District> getDistricts() {
        return this.districts;
    }

    /**
     * ***** DO NOT REMOVE OR UPDATE this method *********
     * 
     * Returns the root of the BST
     */
    public TreeNode getRoot() {
        return game;
    }

}