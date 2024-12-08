import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;   

class Main {

    public static void main(String[] args) {
        // getting arguments (the name file containing the input)
        String filename = null;
        if(args.length == 1){
            filename = args[0];
        }
        else{
            System.err.println("Error : bad format \ncorrect format >> java Main.java file ");
            System.exit(1);
        }

        Scanner scan = readFile(filename);

        scan.useDelimiter("\\A");

        String data = scan.next();
        String pattern = "mul\\([0-9]*,[0-9]*\\)";
        String pattern2 = "mul\\([0-9]*,[0-9]*\\)|do\\(\\)|don't\\(\\)";

        part1(data, pattern);
        part2(data, pattern2);
    }


    public static void part2(String data, String pattern){
        ArrayList<String> list ;
        list = getMatches(data,pattern);
        boolean enabled = true;

        int res = 0;
        for(String val:list){
            if(val.equals("do()")){
                enabled = true;
            }
            else if(val.equals("don't()")){
                enabled = false;
            }
            else if(enabled){
                val = val.replaceAll("[mul\\(\\)]", "");
                String[] lol = val.split(",");
                res = res + (Integer.valueOf(lol[0]) * Integer.valueOf(lol[1]));
            }
        }
        System.out.println(res);
    }


    public static void part1(String data, String pattern){
        ArrayList<String> list ;
        list = getMatches(data,pattern);

        int res = 0;
        for(String val:list){
            val = val.replaceAll("[mul\\(\\)]", "");
            String[] lol = val.split(",");
            res = res + (Integer.valueOf(lol[0]) * Integer.valueOf(lol[1]));
        }
        System.out.println(res);
    }

    public static ArrayList<String> getMatches(String input,String pattern) {
        ArrayList<String> matches = new ArrayList<>();

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);

        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    //return scanner object that let you read the file line by line
    public static Scanner readFile(String filename){
        File file;
        Scanner scan = null;
        
        file = new File(filename);
        if(!file.exists()){
            System.err.println("Error file does not exist");
            System.exit(1);
        }
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading the file \n"+e);
        }
        return  scan;
    }
}