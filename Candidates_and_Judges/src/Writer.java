import java.util.*;
import java.io.*;

public class Writer {
    

public static void writeFiles(ArrayList<Candidate> candidateList){
try{
PrintWriter nameWriter = new PrintWriter("Data/Name.txt");
PrintWriter speechWriter = new PrintWriter("Data/Speech.txt");


for(Candidate c: candidateList){

nameWriter.println(c.name);
speechWriter.println(c.speech);


}

nameWriter.close();
speechWriter.close();


} catch(FileNotFoundException e){

System.out.println("File not found");


}



}


}
