import java.util.HashMap;

public class dic {
    public static void main(String[] args){
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put("Hardware", "Pc");
        dictionary.put("Software", "Pm");
        dictionary.put("College", "T D H");
        dictionary.put("Programer", "L T V");
        dictionary.put("Compile", "B D");
        dictionary.put("Error", "L");
        System.out.println(dictionary.get("Compile"));
    }
}