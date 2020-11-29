import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class dic {
    HashMap<String, String> dict_1;
    ArrayList<String> history;
    public static void main(String[] args){
        dic dictionary = new dic();
        dictionary.readFile("slang.txt");

        boolean conti;
        do{
            int select = dictionary.menu();
            conti = dictionary.options(dictionary, select);
        } while (conti);

    }
    public dic (){
        dict_1 = new HashMap<String, String>();
        history = new ArrayList<String>();
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
    
    public int menu(){
        System.out.println("Chon chuc nang.");          
        System.out.println("1. Tim kiem theo slang word.");
        System.out.println("2. Tim kiem theo definition, hien thi ra cac slang words trong definition co chua keyword go vao.");
        System.out.println("3. Danh sach cac tu da tim kiem.");
        System.out.println("4. Add slang word.");
        int option;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Nhap lua chon: "); 
            option = input.nextInt();
        } while(option < 1 || option > 10);
        return option;
    }

    public boolean options(dic d, int select){
        switch(select) {
            case 1:
                String s = d.findSlang(d);
                if(s != null)
                    d.SaveHistory(s);
                break;
            case 2:
              // code block
              break;
            case 3:
                for (int i = 0; i < d.history.size(); i++){
                    System.out.println(d.history.get(i));
                }
              break;
            case 4:
              // code block
              break;
            case 5:
              // code block
              break;
            case 6:
              // code block
              break;
            case 7:
              // code block
              break;
            case 8:
              // code block
              break;
            case 9:
              // code block
              break;
            case 10:
              // code block
              break;
        }
        int cont;
        Scanner input = new Scanner(System.in);
        
        do {
            System.out.print("Ban co muon tiep tuc khong(1: Co, 0: Khong): ");
            cont = input.nextInt();
        } while(cont != 0 && cont != 1);
        
        if (cont == 1)
            return true;
        else return false;
    }

    public String findSlang(dic d){
        System.out.print("Nhap slang word can tim: ");
        Scanner input = new Scanner(System.in);
        String slang = input.nextLine();
        String meaning = d.dict_1.get(slang);
        if (meaning != null){
            System.out.println(meaning);
            return slang + ": " + meaning;
        }
        else System.out.println("Khong co slang word nay");
        return null;
    }

    public void SaveHistory(String s){
        this.history.add(s);
    }


}
