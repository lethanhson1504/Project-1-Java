import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;  
import java.io.IOException;

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
        System.out.println("Chon chuc nang: ");          
        System.out.println("1. Tim kiem theo slang word.");
        System.out.println("2. Tim kiem theo definition, hien thi ra cac slang words trong definition co chua keyword go vao.");
        System.out.println("3. Danh sach cac tu da tim kiem.");
        System.out.println("4. Add slang word.");
        System.out.println("5. Edit slang word.");
        System.out.println("6. Delete slang word.");
        System.out.println("7. Reset ve danh sach goc.");
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
                System.out.print("Nhap slang word can tim: ");
                String s = d.findSlang(d);
                if(s != null)
                    d.SaveHistory(s);
                break;
            case 2:
              // code block
              break;
            case 3:
                System.out.println("Danh sach cac tu da tim kiem: ");
                for (int i = 0; i < d.history.size(); i++){
                    System.out.println(d.history.get(i));
                }
              break;
            case 4:
              // code block
              break;
            case 5:
                d.editSlang();
              break;
            case 6:
                d.deleteSlang();
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
        Scanner input = new Scanner(System.in);
        String slang = input.nextLine();
        String meaning = d.dict_1.get(slang);
        if (meaning != null){
            System.out.println(meaning);
            return slang;
        }
        else System.out.println("Khong co slang word nay");
        return null;
    }

    public void SaveHistory(String s){
        if (!this.history.contains(s))
            this.history.add(s);
    }


    public void editSlang(){
        System.out.print("Nhap slang word can edit: ");
        String slang = this.findSlang(this);
        if (slang != null){

            int option;
            Scanner input = new Scanner(System.in);
            System.out.println("Ban muon edit slang, nghia, hay ca hai ?(0: slang, 1: nghia, 2: ca hai): ");
            do {
                System.out.print("Nhap lua chon: "); 
                option = input.nextInt();
            } while(option < 0 || option > 2);

            input.nextLine();

            switch(option) {
                case 0:
                    System.out.print("Nhap slang word moi: "); 

                    String new_Slang = input.nextLine();

                    this.dict_1.put(new_Slang, this.dict_1.get(slang));

                    this.dict_1.remove(slang);

                    this.updateFileSlang();

                    System.out.println("Da cap nhat danh sach slang word!");
                    break;
                case 1:
                    System.out.print("Nhap nghia moi: "); 
                    String new_meaning = input.nextLine();

                    this.dict_1.put(slang, new_meaning);

                    this.updateFileSlang();
                    System.out.println("Da cap nhat danh sach slang word!");
                    break;
                case 2:
                    System.out.print("Nhap slang word moi: "); 
                    new_Slang = input.nextLine();

                    System.out.print("Nhap nghia moi: "); 
                    new_meaning = input.nextLine();

                    this.dict_1.put(new_Slang, new_meaning);

                    this.dict_1.remove(slang);

                    this.updateFileSlang();

                    System.out.println("Da cap nhat danh sach slang word!");
                    break;
            }
        }
    }

    public void updateFileSlang(){
        try {
            FileWriter myWriter = new FileWriter("slang.txt");

            for (String i : this.dict_1.keySet()) {
                myWriter.write(i + "`" + this.dict_1.get(i) + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void deleteSlang(){
        System.out.print("Nhap slang word can xoa: ");
        String slang = this.findSlang(this);

        if (slang != null){
            System.out.print("Ban co chac muon xoa slang word nay? (0: Khong, 1: Co): ");
            
            int option;
            Scanner input = new Scanner(System.in);

            do {
                System.out.print("Nhap lua chon: "); 
                option = input.nextInt();
            } while(option < 0 || option > 1);

            input.nextLine();
            if (option == 1) {
                this.dict_1.remove(slang);
                System.out.println("Da xoa"); 
                this.updateFileSlang();
            }
            else System.out.println("Da huy"); 
        }
    }

}
