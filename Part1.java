import edu.duke.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String  FindStopCodon(String dna ,int startindex , String endindex){
       startindex = dna.indexOf("ATG");
       if (startindex == -1){
           return "";
        }
       int currindex = dna.indexOf(endindex ,startindex + 3);
       
       while (currindex != -1){
           if ( (currindex - startindex) % 3 == 0){
               return dna.substring(startindex , currindex + 2);
           }
           
           else{
               currindex = dna.indexOf(endindex , currindex + 1);
           }
       }
        
       return "";
}


public String findGene(String dna , int where){
    int StartIndex = dna.indexOf("ATG" , where);
        if (StartIndex == -1){
            return "";
        }
    int tagIndex = FindStopCodon( dna , StartIndex , "TAG").length();
    //System.out.println("Tagindex :" + tagIndex);
    int taaIndex = FindStopCodon( dna , StartIndex , "TAA").length();
    //System.out.println("Taaindex :" + taaIndex);
    int tgaIndex = FindStopCodon( dna , StartIndex , "TGA").length();
    //System.out.println("Tgaindex :" + tgaIndex);
    
    int minindex = 0;
    
    if(taaIndex == 0 || (tgaIndex != 0 && tgaIndex < taaIndex)){
        minindex = tgaIndex;
    }
    else{
        minindex = taaIndex;
    }
    
    if(minindex == 0 || (tagIndex != 0 && tagIndex < minindex)){
        minindex = tagIndex;
    }
    
    if (minindex == 0){
        return "";
    }
    //System.out.print("Minindex :" + minindex);
    //int lastindex = minindex + 3;
    //System.out.print("Substring :" + dna.substring(StartIndex , minindex + 3));
    return dna.substring(StartIndex , minindex + 3);
    
}

public double cgratio (String dna){
    char[] chars = dna.toCharArray();
    double count = 0.0;
    for (char ch : chars){
        if (ch == 'C' || ch == 'G'){
            count = count + 1;
        }
    }
    
    
    return count;
}

public StorageResource findallgenes(String dna){
    int StartIndex = 0;
    StorageResource GeneDir = new StorageResource();
    while (true){
        String Gene = findGene(dna,StartIndex);
        if (Gene.isEmpty()){
            break;
        }
        GeneDir.add(Gene);
        
        StartIndex = dna.indexOf(Gene , StartIndex) + Gene.length();
        //System.out.println("StartIndex :" + StartIndex);
    }
    
    return GeneDir;
}
public void findallgenes___test(String dna){
    StorageResource sr = findallgenes(dna);
    
    for(String g : sr.data()){
        
        System.out.println("Gene :" + g);
    }
}
 public void TestGeneAlgorithm(){
     FileResource fr = new FileResource();
     String S = fr.asString();
     String D = S.toUpperCase();
     
     findallgenes___test(D);
        
        
        
        
        
        
        //findallgenes___test("ATTAATGTAGTGTGTGTTAAGT");
        
        
        
        //findallgenes___test("ATATGTATGTATGTGTATGGTAGTAAGTETGFERTYHJHGD");
        
        
    }

}


