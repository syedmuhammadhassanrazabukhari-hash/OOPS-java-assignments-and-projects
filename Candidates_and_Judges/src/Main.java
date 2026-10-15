import  java.util.*;

public class Main{
public static void main(String[] args){

Scanner sc = new Scanner(System.in);    

ArrayList<Candidate> candidatesList = new ArrayList<>(); 
Reader.readFiles(candidatesList);
if(candidatesList.isEmpty()){
System.out.println("You have no candidate logged in !");
}

boolean running = true;

while (running == true){
System.out.println("Main Menu\n1.add candidate\n2.view candidate list\n3.start the show\n4.exit:( ");
int userInput = sc.nextInt();
while(userInput>4||userInput<1){
System.out.println("Enter a valid option: ");
userInput= sc.nextInt();
}

switch (userInput) {
    case 1:
        other.addCandidate(candidatesList);
        break;

    case 2:

    other.displayCandidateList(candidatesList);
        break;

    case 3:
      Judge.judge(candidatesList);
        break;
   
    case 4:
    Writer.writeFiles(candidatesList);
    System.exit(0);
        break;
}















}

























}


}