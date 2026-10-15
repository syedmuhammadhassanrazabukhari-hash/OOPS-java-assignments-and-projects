import java.util.ArrayList;
import java.util.Random;


public class Judge {
    
public static void judge(ArrayList <Candidate> candidatesList){

juniorJudge(candidatesList);
seniorJudge(candidatesList);
crazyJudge(candidatesList);
average(candidatesList);

}






public static void juniorJudge(ArrayList<Candidate> candidatesList){

String[] vocab = {
    "code", "program", "algorithm", "static", "variable",
    "class", "object", "method", "loop", "array",
    "database", "software", "compiler", "debug", "syntax",
    "java", "exception", "interface", "inheritance", "logic",
    "developer", "framework", "reference", "child", "application"
};


for(int i = 0; i<candidatesList.size(); i++){
Candidate c = candidatesList.get(i);
String lower =  c.speech.toLowerCase();

 for(int y = 0; y < vocab.length; y++){

   String v = vocab[y];

    
    if(lower.contains(v)){
        c.totalMarksJuniorJudge += 0.5;
        System.out.println("junior judges has given "+c.name+" "+ c.totalMarksJuniorJudge+" marks" );
    } else {


        c.totalMarksJuniorJudge += 0;
    }


        
}


System.out.println("junior judge has given " + c.name +" "+ c.totalMarksJuniorJudge );

    }

    


}
public static void seniorJudge(ArrayList<Candidate> candidatesList){

    String[] vocab2 = {"instance", "override","heap","stack"};
    

    for(int i = 0; i<candidatesList.size(); i++){

        Candidate c = candidatesList.get(i);
        String lower = c.speech.toLowerCase();
        String[] words = c.speech.split(" ");
        c.totalMarksSeniorJudge += 2; //marks for participation
        int limitMax = 200;
        int limitMin = 30;

        for(int y = 0; y<vocab2.length;y++){

            String v = vocab2[y];
            if(lower.contains(v)){

                c.totalMarksSeniorJudge += 1;



            }


        }


 if(words.length<=limitMax){

            c.totalMarksSeniorJudge += 2;



        }


if(words.length>limitMin){
    c.totalMarksSeniorJudge += 2;
}






System.out.println("senior judge has given " + c.name +" "+ c.totalMarksSeniorJudge );

    }



}


public static void crazyJudge(ArrayList<Candidate> candidatesList){
Random random = new Random();

for(int i = 0; i<candidatesList.size();i++){

Candidate c = candidatesList.get(i);

double marks = random.nextDouble(2);
c.totalMarksCrazyJudge = marks;
System.out.println("carzy judge has given " + c.name +" "+ c.totalMarksCrazyJudge );


}



}
public static void average(ArrayList <Candidate>candidatesList){

    for(int i = 0; i<candidatesList.size(); i++){

        Candidate c = candidatesList.get(i);

        c.finalScore = (c.totalMarksCrazyJudge + c.totalMarksJuniorJudge + c.totalMarksSeniorJudge) / 3;


    }



candidatesList.sort((a, b) -> Double.compare(b.finalScore, a.finalScore));

    int top = Math.min(3, candidatesList.size());

    for (int i = 0; i < top; i++) {
        Candidate c = candidatesList.get(i);
        System.out.println("Rank " + (i + 1) + ": " + c.name + " with " + c.finalScore + " marks");
    }
}






}
