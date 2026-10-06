import java.util.*;

public class dicegame {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
//roll method
    static void roll() {
               

        boolean running = true;
      while(running == true){  System.out.println("press r to roll or e to return to the main menu ");
        char userinput = scanner.next().charAt(0);

       

        while(userinput != 'r' && userinput != 'R'&& userinput != 'e' && userinput != 'E'){
        System.out.println("Enter a valid input");
        userinput =  scanner.next().charAt(0);
        }
      

        if(userinput == 'r'||userinput=='R'){
             int dice = random.nextInt(6) +1;
                int dice2 = random.nextInt(6) +1;

            System.out.println("You rolled: \n:: "+dice + " :: " + dice2);



        } else if(userinput =='e' || userinput == 'E'){

            return ;
        }

    }



    }

    public static void main(String[] args) {

System.out.println(" _____ _______ ______ _______      _______ _______ _______ _______ \r\n" + //
        "|     \\_     _|      |    ___|    |     __|   _   |   |   |    ___|\r\n" + //
        "|  --  ||   |_|   ---|    ___|    |    |  |       |       |    ___|\r\n" + //
        "|_____/_______|______|_______|    |_______|___|___|__|_|__|_______|\r\n" + //
        "                                                                   ");
boolean running = true;

        while(running == true){System.out.println("MENU: \n1.Play \n2.Exit");
        int userInput = scanner.nextInt();
while(userInput > 2 || userInput< 1){
System.out.println("Enter a valid option");
userInput = scanner.nextInt();

}




switch(userInput){
case 1: roll();
    
    break;

case 2:
System.exit(0);
    break;

}


        }






    }

}
