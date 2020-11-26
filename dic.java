import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class dic {
    HashMap<String, String> dict_1;
    public static void main(String[] args){
        dic dictionary = new dic();
        dictionary.readFile("slang.txt");
        //System.out.println(dictionary.dict_1);
        System.out.println(dictionary.dict_1.get("^W^"));
    }
    public dic (){
        dict_1 = new HashMap<String, String>();
    }
    public void readFile(String fileName){
        try{
            File myfile = new File(fileName);
            Scanner myReader = new Scanner(myfile);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] temp = data.split("`", 2);
                this.dict_1.put(temp[0], temp[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }
}
