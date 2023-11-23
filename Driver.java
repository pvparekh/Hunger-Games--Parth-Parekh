package games;

import java.util.ArrayList;

/**
 * This class tests each method in the Hunger Games class to interactively
 * display outputs.
 *
 * @author Kal Pandit
 */
public class Driver {
    public static void main(String[] args) {
        String[] methods = { "setupPanem", "addDistrictToGame", "findDistrict", "selectDuelers",
                "eliminateDistrict", "eliminateDueler" };
        String[] options = { "Test new file", "Test new method on the same file", "Quit" };
        int repeatChoice = 0;
        do {
            System.err.print("Enter an input text file name => ");
            String input = StdIn.readLine();
            System.err.println();
            HungerGames studentHungerGames = null;
            DuelPair pair = null;

            do {
                System.err.println("What method would you like to test? Later methods rely on previous methods.");

                for (int i = 0; i < methods.length; i++) {
                    if (i == 0 && studentHungerGames != null) {
                        System.out.println("1. setupPanem (START OVER)");
                    } else {
                        System.err.printf("%d. %s\n", i + 1, methods[i]);
                    }
                }

                System.err.print("Enter a number => ");
                int choice = StdIn.readInt();
                StdIn.readLine();
                System.err.println();
                switch (choice) {
                    case 1:
                        studentHungerGames = testSetupPanem(input);
                        break;
                    case 2:
                        testAddDistrictToGame(studentHungerGames, input);
                        break;
                    case 3:
                        testFindDistrict(studentHungerGames, input);
                        break;
                    case 4:
                        pair = testSelectDuelers(studentHungerGames, input);
                        break;
                    case 5:
                        testEliminateDistrict(studentHungerGames, input);
                        break;
                    case 6:
                        testEliminateDueler(studentHungerGames, pair);
                        pair = null;
                        break;
                    default:
                        System.err.println("Not a valid method to test!");
                }

                StdIn.resync();

                System.err.println("\nWhat would you like to do now?");
                for (int i = 0; i < 3; i++) {
                    System.err.printf("%d. %s\n", i + 1, options[i]);
                }

                System.err.print("Enter a number => ");
                repeatChoice = StdIn.readInt();
                StdIn.readLine();
                System.err.println();

            } while (repeatChoice == 2);
        } while (repeatChoice == 1);
    }

    private static HungerGames testSetupPanem(String input) {
        HungerGames hg = new HungerGames();
        hg.setupPanem(input);
        StdOut.println("Districts read in order of insertion:");
        for (District d : hg.getDistricts()) {
            StdOut.println(d.toString());
        }
        return hg;
    }

    private static void testAddDistrictToGame(HungerGames studentHungerGames, String input) {
        if (studentHungerGames.getDistricts().size() == 0) {
            StdOut.println("No districts available!");
            return;
        }
        StdOut.println("Select a district:");
        int i = 0;
        for (District d : studentHungerGames.getDistricts()) {
            StdOut.println(i + ": " + d.toString());
            i++;
        }
        StdOut.println(i + ": Add ALL districts");
        System.err.print("Enter a district index or " + i + " to add all => ");
        int choice = StdIn.readInt();
        StdIn.readLine();
        System.err.println();
        if (choice == i) {
            // Make a deep copy of ArrayList, prevents comodification
            ArrayList<District> districtCopy = new ArrayList<>();
            for (District d : studentHungerGames.getDistricts()) {
                districtCopy.add(d);
            }
            // Then add the districts from deep copy
            for (District d : districtCopy) {
                studentHungerGames.addDistrictToGame(studentHungerGames.getRoot(), d);
            }
        } else {
            studentHungerGames.addDistrictToGame(studentHungerGames.getRoot(),
                    studentHungerGames.getDistricts().get(choice));
        }

        StdOut.println("District Tree: ");
        printTree(studentHungerGames.getRoot());
    }

    private static void testFindDistrict(HungerGames studentHungerGames, String input) {
        System.err.print("Enter a district ID  => ");
        int choice = StdIn.readInt();
        StdIn.readLine();
        System.err.println();
        District res = studentHungerGames.findDistrict(choice);
        if (res != null) {
            StdOut.println("District successfully found:");
            StdOut.println(res.toString());
        } else {
            StdOut.println("District not found.");
        }
    }

    private static void testEliminateDistrict(HungerGames studentHungerGames, String input) {
        System.err.print("Enter a district ID to eliminate  => ");
        int choice = StdIn.readInt();
        StdIn.readLine();
        System.err.println();
        studentHungerGames.eliminateDistrict(choice);
        StdOut.println("New Tree:\n");
        printTree(studentHungerGames.getRoot());
    }

    private static DuelPair testSelectDuelers(HungerGames studentHungerGames, String input) {
        StdRandom.setSeed(2023);
        DuelPair people = studentHungerGames.selectDuelers();
        Person one = people.getPerson1();
        Person two = people.getPerson2();
        StdOut.print("PERSON 1 => ");
        if (one == null) {
            StdOut.print("EMPTY");
        } else {
            StdOut.println(one.toString());
        }
        StdOut.println();
        StdOut.print("PERSON 2 => ");
        if (two == null) {
            StdOut.print("EMPTY");
        } else {
            StdOut.println(two.toString());
        }
        StdOut.println();
        return people;
    }

    private static void testEliminateDueler(HungerGames studentHungerGames, DuelPair pair) {
        if (pair == null) {
            StdOut.println("Pair is empty, test selectDuelers first!");
            return;
        }
        StdOut.println();
        studentHungerGames.eliminateDueler(pair);
        StdOut.println("New Tree:\n");
        printTree(studentHungerGames.getRoot());
    }

    private static void printTree(TreeNode root) {
        printTree(root, "", false, true);
    }

    private static void printTree(TreeNode n, String indent, boolean isRight, boolean isRoot) {
        StdOut.print(indent);

        // Print out either a right connection or a left connection
        if (!isRoot)
            StdOut.print(isRight ? "|-R- " : "|-L- ");

        else
            StdOut.print("+--- ");

        if (n == null) {
            StdOut.println("null");
            return;
        }

        if (n.getDistrict() != null)
            StdOut.print("[" + n.getDistrict().getDistrictID() + " -> "
                    + n.getDistrict().getOddPopulation().size() + ", "
                    + n.getDistrict().getEvenPopulation().size() + "]");
        StdOut.println();

        // If no more children we're done
        if (n.getLeft() == null && n.getRight() == null)
            return;

        // Add to the indent based on whether we're branching left or right
        indent += isRight ? "|    " : "     ";

        printTree(n.getLeft(), indent, false, false);
        printTree(n.getRight(), indent, true, false);
    }
}
