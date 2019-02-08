import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
/**
 * Simulates the classic shut the box game
 * 
 * @author (Patrick Barber) 
 * @version (1/24/18)
 */
public class ShutTheBox
{
    private int[] numbers;
    private ArrayList<Integer> values;
    private ArrayList<Integer> flippedNums;
    /**
     * Constructor for objects of class ShutTheBox
     */
    public ShutTheBox()
    {
        // initialise instance variables
        numbers = new int[] {1,2,3,4,5,6,7,8,9};
        values = new ArrayList<>();
        for (int i=0; i<numbers.length; i++) //fills an array list with the values from the array
        {
            values.add(numbers[i]);
        }
        flippedNums = new ArrayList<>();
    }

    public boolean gameOver(int diceRoll, ArrayList<Integer> values)
    //this method checks if the game is over, takes the remaining numbers and the combined dice roll as input
    {
        int length = values.size(); //determines the number of unflipped numbers
        int sum = 0; //sum of some number of unflipped numbers
        boolean isGameOver = false; //the result
        double possibilities = 0; //number of possibile sums
        int count = 0; //counter
        int temp = 0; // temp variable

        possibilities = Math.pow(2,length); //determines the number of possible sums based on the remaining unflipped numbers

        for (int i=1; i<possibilities && !isGameOver; i++)
        {
            sum = 0;
            count = 0;
            temp = i;
            while(temp>0)
            {
                if(temp%2==1)
                {
                    sum+= values.get(count);
                }
                temp = temp/2;
                count++;
            }
            isGameOver = (sum==diceRoll); //determine the result 
        }
        return isGameOver;
    }

    public void runSimulation()
    {
        Random ran = new Random();
        //boolean temp = false;
        while(values.size()>0) //continue the game while there are still unflipped numbers
        {
            int diceRoll = (ran.nextInt(6)+1 + ran.nextInt(6)+1);

            System.out.print("Numbers Remaining: ");
            System.out.println(values);
            System.out.println("Your dice roll: "+diceRoll);

            if(!gameOver(diceRoll,values)) //checks if there is a possible combination fromt the dice roll
            {
                int score = 0;
                for (int j=0; j<values.size(); j++) //add up all remaining unflipped numbers to get your score
                {
                    score += values.get(j);
                }
                System.out.println("Game over your score is: " + score);
                return;
            }

            System.out.println("Type the numbers you want to flip: ");
            System.out.print("For example, type 1 5 6 for a roll of 12 => ");

            //create a scanner and take in a string with the number(s) to be flipped
            Scanner scan = new Scanner(System.in);
            String line  = scan.nextLine();
            Scanner lineScan = new Scanner(line);

            int total = 0;
            //boolean temp = false;

            while (lineScan.hasNextInt()) { //this finds each number in the input and adds it up 
                int numflip = lineScan.nextInt();
                total += numflip;
                for (int j=0; j<flippedNums.size(); j++) //loop through the remaining numbers 
                {
                    if (numflip == flippedNums.get(j))
                    {
                        System.out.println("Invalid Input, try again. ");
                        System.out.println();
                        runSimulation2(diceRoll);

                    }
                }
            }

            if (total != (diceRoll)) //if the inputted number(s) do not equal the dice roll then game ends
            {
                System.out.println("Those numbers do not add up to the dice roll! ");
                return;
            }
            else{ 
                Scanner lineScan2 = new Scanner(line);
                while (lineScan2.hasNextInt()) { //scan the input line again
                    int numFlip = lineScan2.nextInt(); //save input to numFlip
                    for (int j=0; j<values.size(); j++) //loop through the remaining numbers 
                    {
                        if(numFlip == values.get(j)) //if the input equals a number that is remaining
                        {
                            values.remove(j); //remove the number from the list or "flip" it
                            flippedNums.add(numFlip);
                        }
                    }
                }
            }
            System.out.println();
        }
        if (values.size()==0) //if all numbers have been flipped
        {
            System.out.println("Congrats you've won!");
        }
    }

    public void runSimulation2(int diceRollIn)
    {
        Random ran = new Random();
        //boolean temp = false;
        while(values.size()>0) //continue the game while there are still unflipped numbers
        {
            int diceRoll = diceRollIn;

            System.out.print("Numbers Remaining: ");
            System.out.println(values);
            System.out.println("Your dice roll: "+diceRoll);

            if(!gameOver(diceRoll,values)) //checks if there is a possible combination fromt the dice roll
            {
                int score = 0;
                for (int j=0; j<values.size(); j++) //add up all remaining unflipped numbers to get your score
                {
                    score += values.get(j);
                }
                System.out.println("Game over your score is: " + score);
                return;
            }

            System.out.println("Type the numbers you want to flip: ");
            System.out.print("For example, type 1 5 6 for a roll of 12 => ");

            //create a scanner and take in a string with the number(s) to be flipped
            Scanner scan = new Scanner(System.in);
            String line  = scan.nextLine();
            Scanner lineScan = new Scanner(line);

            int total = 0;
            //boolean temp = false;

            while (lineScan.hasNextInt()) { //this finds each number in the input and adds it up 
                int numflip = lineScan.nextInt();
                total += numflip;
                for (int j=0; j<flippedNums.size(); j++) //loop through the remaining numbers 
                {
                    if (numflip == flippedNums.get(j))
                    {
                        System.out.println("Invalid Input, Try again. ");
                        System.out.println();
                        runSimulation2(diceRoll);

                    }
                }
            }

            if (total != (diceRoll)) //if the inputted number(s) do not equal the dice roll then game ends
            {
                System.out.println("Those numbers do not add up to the dice roll! ");
                return;
            }
            else{ 
                Scanner lineScan2 = new Scanner(line);
                while (lineScan2.hasNextInt()) { //scan the input line again
                    int numFlip = lineScan2.nextInt(); //save input to numFlip
                    for (int j=0; j<values.size(); j++) //loop through the remaining numbers 
                    {
                        if(numFlip == values.get(j)) //if the input equals a number that is remaining
                        {
                            values.remove(j); //remove the number from the list or "flip" it
                            flippedNums.add(numFlip);
                        }
                    }
                }
            }
            System.out.println();
        }
        if (values.size()==0) //if all numbers have been flipped
        {
            System.out.println("Congrats you've shut the box!!");
            System.out.println("Your score is 0 points");
        }
    }
}
