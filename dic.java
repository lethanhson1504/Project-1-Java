import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;  
import java.io.*;
import java.util.*; 

public class dic {
    HashMap<String, String> dict_1; // tu dien su dung hashmap
    ArrayList<String> history; //array list luu lich su slang word da tim kiem
    public static void main(String[] args){
        dic dictionary = new dic();
        dictionary.readData("slang.txt");

        boolean conti;

        dictionary.clearScreen();

        do{
            System.out.println("-----SLANG WORD-----");
            int select = dictionary.menu();
            conti = dictionary.options(dictionary, select);
            dictionary.clearScreen();
        } while (conti);

    }

    //constructor
    public dic (){
        dict_1 = new HashMap<String, String>();
        history = new ArrayList<String>();
    }

    //khoi tao du lieu tu dien ban dau
    public void readData(String fileName){
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
    

    //lua chon chuc nang
    public int menu(){
        System.out.println("Chon chuc nang: ");          
        System.out.println("1. Tim kiem theo slang word.");
        System.out.println("2. Tim kiem theo definition.");
        System.out.println("3. Danh sach cac tu da tim kiem.");
        System.out.println("4. Add slang word.");
        System.out.println("5. Edit slang word.");
        System.out.println("6. Delete slang word.");
        System.out.println("7. Reset ve danh sach goc.");
        System.out.println("8. Random 1 slang word.");
        System.out.println("9. Do vui theo slang word.");
        System.out.println("10. Do vui theo meaning.");
        int option;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Nhap lua chon: "); 
            option = input.nextInt();
        } while(option < 1 || option > 10);
        return option;
    }

    // goi cac ham xu li chuc nang
    public boolean options(dic d, int select){
        switch(select) {
            case 1:
                d.clearScreen();
                System.out.print("Nhap slang word can tim: ");
                String s = d.findSlang(d);
                if(s != null)
                    d.SaveHistory(s);
                else System.out.println("Khong co slang word nay");
                break;
            case 2:
                d.clearScreen();
                d.search_Definition();
                break;
            case 3:
                d.clearScreen();
                System.out.println("Danh sach cac tu da tim kiem: ");
                for (int i = 0; i < d.history.size(); i++){
                    System.out.println(d.history.get(i));
                }
                break;
            case 4:
                d.clearScreen();
                d.addNewSlang();
                break;
            case 5:
                d.clearScreen();
                d.editSlang();
                break;
            case 6:
                d.clearScreen();
                d.deleteSlang();
                break;
            case 7:
                d.clearScreen();
                this.resetSlang();
                break;
            case 8:
                d.clearScreen();
                d.randomSlang();
                break;
            case 9:
                d.clearScreen();
                d.challenge_1(9);
                break;
            case 10:
                d.clearScreen();
                d.challenge_1(10);
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


    //1. tim nghia slang word theo slang
    public String findSlang(dic d){
        Scanner input = new Scanner(System.in);
        String slang = input.nextLine().toLowerCase();
        String meaning = d.dict_1.get(slang);
        if (meaning != null){
            System.out.println(meaning);
            return slang;
        }
        
        return null;
    }


    //3. lich su cua slang da tim kiem
    public void SaveHistory(String s){
        if (!this.history.contains(s))
            this.history.add(s.toUpperCase());
    }


    //5. chinh sua slang word
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


    //update lai du lieu file txt sau khi them/xoa/sua

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


    //xoa 1 slang word trong tu dien
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


    //7. reset slang word ve danh sach goc
    public void resetSlang(){
        this.dict_1.clear();

        this.readData("slang_original.txt");

        this.updateFileSlang();

        System.out.println("Da reset ve danh sach ban dau!");
    }


    //8. random slang word
    public void randomSlang(){
        Random generator = new Random();

        ArrayList<String> random_List = new ArrayList<String>();

        for (String i : this.dict_1.keySet()) {
            random_List.add(i + ": " + this.dict_1.get(i));
        }

        String randomValue = random_List.get(generator.nextInt(random_List.size()));

        System.out.println(randomValue.toUpperCase());
    }

    //9. do vui theo slang/ theo meaning (n = 9: cau 9; n = 10: cau 10)
    public void challenge_1(int n){
        Random generator = new Random();

        ArrayList<String> random_List = new ArrayList<String>();

        for (String i : this.dict_1.keySet()) {
            random_List.add(i + ": " + this.dict_1.get(i));
        }

        Object randomValue = random_List.get(generator.nextInt(random_List.size()));

        String[] slang = randomValue.toString().split(": ", 2);

        ArrayList<String> answer_List = new ArrayList<String>();

        String key = slang[0].toUpperCase();
        String value = slang[1].toUpperCase();        

        if(n == 10){
            n = 8;
            String temp = key;
            key = value;
            value = temp;
        }
        answer_List.add(value);

        for(int i = 1; i < 4; i++){
            do{
                randomValue = random_List.get(generator.nextInt(random_List.size()));
                slang = randomValue.toString().split(": ", 2);
            }
            while(answer_List.contains(slang[n - 8]));
            answer_List.add(slang[n - 8]);
        }

        Collections.shuffle(answer_List);  

        if (n == 8)
            System.out.println("Meaning: " + key);
        else System.out.println("Slang: " + key);

        for(int i = 0; i < answer_List.size(); i++)
            System.out.println(i + 1 + ". " + answer_List.get(i).toUpperCase());

        System.out.print("Nhap dap an: ");
        
        int option;
        Scanner input = new Scanner(System.in);
        do {
            option = input.nextInt();
        } while(option < 1 || option > 4);
        
        if (answer_List.get(option - 1) == value)
            System.out.println("Chuc mung. Ban qua gioi! :D");
        else System.out.println("Thu lai nhe :((. Dap an la: " + value);
    }

    //2. Tim theo nghia cua slang, in ra tat ca slang co key word nhap vao
    public void search_Definition(){

        Scanner input = new Scanner(System.in);
        System.out.print("Nhap meaning can tim: "); 
        String meaning = input.nextLine().toLowerCase();

        boolean exist = false;

        System.out.println("Cac slang word meaning co chua " + meaning +": "); 

        for (Map.Entry mapElement : this.dict_1.entrySet()) { 
            String key = (String)mapElement.getKey(); 
            String value = (String)mapElement.getValue(); 

            if(value.contains(meaning)) {
                exist = true;
                System.out.println(key.toUpperCase()); 
            }
        }

        if (exist == false){
            System.out.println("Khong co slang word nao!"); 
        }

    }

    //xoa man hinh cho ung dung de quan sat
    //phan code tham khao
    public void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
    //phan code tham khao

    //4. Them tu cho tu dien, neu tu da ton tai thi cho phep ghi de hoac duplicate.
    public void addNewSlang(){
        Scanner input = new Scanner(System.in);

        System.out.print("Nhap slang can them: "); 
        String slang = input.nextLine().toLowerCase();

        System.out.print("Nhap meaning cho slang: "); 
        String meaning = input.nextLine().toLowerCase();

        if(this.dict_1.get(slang) == null){

            this.dict_1.put(slang, meaning);
            System.out.print("Da them slang thanh cong! ");
            
            //phan code tham khao
            try { 
                BufferedWriter out = new BufferedWriter(new FileWriter("slang.txt", true));               
                out.write(slang + "`" + meaning + "\n"); 
                out.close(); 
            } 
            catch (IOException e) { 
                System.out.println("exception occoured" + e); 
            } 
            //phan code tham khao

            input.nextLine();
        }

        else {
            System.out.println("Slang da ton tai. Ban muon ghi de hay them meaning cho slang ?(0: Ghi de, 1: Them meaning)");
            int option;
            do {
                System.out.print("Nhap lua chon: ");
                option = input.nextInt();
            } while (option < 0 || option > 1);

            if (option == 1){
                String add_meaning = this.dict_1.get(slang);
                add_meaning += "/" + meaning;
                this.dict_1.put(slang, add_meaning);
                System.out.println("Them meaning thanh cong!");
                this.updateFileSlang();
            }

            else {
                this.dict_1.put(slang, meaning);
                System.out.println("Da ghi de!");
                this.updateFileSlang();
            }
        }
    }

}
