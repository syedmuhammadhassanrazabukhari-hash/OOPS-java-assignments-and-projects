import  java.io.*;
import java.util.*;

public class Reader {






public static void readFiles(ArrayList<Candidate> candidateList){

    try{
Scanner nameReader = new Scanner(new File("Data/Name.txt"));
Scanner speechReader = new Scanner(new File("Data/Speech.txt"));

while(nameReader.hasNextLine() && speechReader.hasNextLine()){





String name = nameReader.nextLine();
String speech = speechReader.nextLine();
new Candidate(name,speech,candidateList);



}


nameReader.close();
speechReader.close();
} catch (FileNotFoundException e){


System.out.println("file not found");






}




    
}
}