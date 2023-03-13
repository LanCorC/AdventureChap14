import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //Challenge: allow full words or phrases, then move
    //e.g. Go West, run South, East, N should all be acceptable


    private static Map<Integer, Location> locations =
            new HashMap<Integer, Location>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if (loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

//            my implementation below:
//            Limitations: trust that the user will appropriately enter correct words, else..
//            No, I wanna stay -> read as “N” north if exit is valid
//            String[] direction = scanner.nextLine().
//                    toUpperCase().split(" ");
//            int size = direction.length;
//            for (String input : direction){
//                size--;
//                input = input.substring(0, 1);
//                if (exits.containsKey(input)) {
//                    loc = exits.get(input);
//                    System.out.println("Valid exit selected: " + input);
//                } else if(size == 0) {
//                    System.out.println("You cannot go in that direction");
//                }
//            }

            //His implementation:
            Map<String, String> vocabulary = new HashMap<>();
            vocabulary.put("NORTH", "N");
            vocabulary.put("WEST", "W");
            vocabulary.put("SOUTH", "S");
            vocabulary.put("EAST", "E");


            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                    }
                }
            }

            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("Invalid direction.");
            }
        }
        //Limitations:
        //trusts users use strictly sole input, N, Q, S etc or
        //trusts users with phrases strictly uses word e.g. “please go north” not “please go n”
    }
}
