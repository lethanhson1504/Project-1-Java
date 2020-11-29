import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class dic {
    HashMap<String, String> dict_1;
    public static void main(String[] args){
        dic dictionary = new dic();
        dictionary.readFile("slang.txt");
        int cont = 1;
        do{
            System.out.println("Chon chuc nang: ");          
            System.out.println("1. Tim kiem theo slang word: ");
            System.out.println("2. Tim kiem theo definition, hien thi ra cac slang words trong definition co chua keyword go vao: ");
            System.out.println("3. Danh sach cac tu da tim kiem: ");
            System.out.println("4. Add slang word. ");
            Scanner input = new Scanner(System.in);
            int select = input.nextInt();
            dictionary.findSlang(dictionary);
            System.out.println("Ban co muon tiep tuc khong: ");
            cont = input.nextInt();
        } while (cont == 1);
        
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

    public void findSlang(dic d){
        System.out.print("Nhap slang word can tim: ");
        Scanner input = new Scanner(System.in);
        String slang = input.nextLine();
        String meaning = d.dict_1.get(slang);
        if (meaning != null)
            System.out.println(meaning);
        else System.out.println("Khong co slang word nay");
    }
}
