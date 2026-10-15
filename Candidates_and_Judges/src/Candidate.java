import java.util.*;


public class Candidate {
    
String name;
String speech;
boolean timeOut;
double totalMarksJuniorJudge;
double totalMarksSeniorJudge;
double totalMarksCrazyJudge;
double finalScore;


Candidate(String name, String speech, ArrayList <Candidate> candidateList){


this.name = name;
this.speech = speech;
if(speech.isEmpty()){
this.timeOut = true;
}else{
    this.timeOut= false;
}
this.totalMarksJuniorJudge = 0;
this.totalMarksSeniorJudge = 0;
this.totalMarksCrazyJudge = 0;
this.finalScore = 0;
candidateList.add(this);



}










Candidate(ArrayList<Candidate> candidateList){
Scanner sc = new Scanner(System.in);




System.out.println("Enter the name of the candidate");
String userInputName = sc.nextLine();
this.name = userInputName;

System.out.println("Enter thier speech");
String userInputSpeech = sc.nextLine();

this.speech=userInputSpeech;

if(this.speech == null || this.speech.isEmpty()){
this.timeOut = true;

} else {
    this.timeOut = false;
}
this.totalMarksJuniorJudge = 0;
this.totalMarksSeniorJudge = 0;
this.totalMarksCrazyJudge = 0;
this.finalScore = 0;
candidateList.add(this);





}



@Override 
public String toString(){



    return "===================\nName: " + name + "\nSpeech: " + speech + "\nTime out: "+timeOut+"\n===================\n" ;


}







}
