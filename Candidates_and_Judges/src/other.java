import java.util.*;

public class other {
    


public static void addCandidate(ArrayList<Candidate> candidateList){
Scanner sc = new Scanner(System.in);
boolean running = true;
while(running == true){

System.out.println("1.Add candidate\n2.Back to main menu");
int userInput = sc.nextInt();
if(userInput==1){
    new Candidate(candidateList);
}else if(userInput==2){
running= false;
System.out.println("returning back to main menu");
return;
}
}
}



public static void displayCandidateList(ArrayList<Candidate> candidatesList){

if(candidatesList.isEmpty()){System.out.println("you have no candidates");}
else{
    for(int i = 0; i<candidatesList.size();i++){

System.out.println(candidatesList.get(i));

    }
}





}















}