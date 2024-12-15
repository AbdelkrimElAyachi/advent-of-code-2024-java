import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main{

    public static void main(String[] args){
        // getting arguments (the name file containint index, ArrayList<ArrayList<Integer>> listing the input)
        String filename = null;
        if(args.length == 1){
            filename = args[0];
        }
        else{
            System.err.println("Error : bad format \ncorrect format >> java Main.java file ");
            System.exit(1);
        }

        Scanner scan = readFile(filename);

        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> rules = new HashMap<>();

        boolean readingUpdates = false;
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.isBlank() && !readingUpdates){
                readingUpdates = true;
            }
            if(line.isBlank())
                continue;

            if(readingUpdates){
                String[] data = line.split(",");
                ArrayList<Integer> update = new ArrayList<>() ;
                for (String val : data) {
                    update.add(Integer.valueOf(val));
                }
                updates.add(update);
            }
            else{
                String[] data = line.split("\\|");
                if(rules.containsKey(Integer.valueOf(data[0]))){
                    rules.get(Integer.valueOf(data[0])).add(Integer.valueOf(data[1]));
                }
                else{
                    ArrayList<Integer> rule = new ArrayList<>();
                    rule.add(Integer.valueOf(data[1]));
                    rules.put(Integer.valueOf(data[0]), rule);
                }
            }
        }

        part2(updates, rules);
    }

    public static void part2(ArrayList<ArrayList<Integer>> updates,HashMap<Integer, ArrayList<Integer>> rules){
        boolean ok;
        int count = 0;
        for(ArrayList<Integer> update: updates){
            ok = true;
            for(int i=0; i<update.size(); i++){
                for(int j=i+1; j<update.size(); j++){
                    if( !rules.get(update.get(i)).contains(update.get(j)) ){
                        ok = false;
                    }
                }
            }
            if(!ok){
                boolean ordered;
                do{
                    ordered = true;
                    for(int i=0; i<update.size(); i++){
                        for(int j=i+1; j<update.size(); j++){
                            if( !rules.get(update.get(i)).contains(update.get(j)) ){
                                ordered = false;
                                int tmp = update.get(j);
                                update.set(j, update.get(i));
                                update.set(i, tmp);
                            }
                        }
                    }
                }while(!ordered);

                count = count + update.get(update.size()/2);
            }
        }

        System.out.println(count);
    }


    public static void part1(ArrayList<ArrayList<Integer>> updates,HashMap<Integer, ArrayList<Integer>> rules){
        boolean ok;
        int count = 0;
        for(ArrayList<Integer> update: updates){
            ok = true;
            for(int i=0; i<update.size(); i++){
                for(int j=i+1; j<update.size(); j++){
                    if( !rules.get(update.get(i)).contains(update.get(j)) ){
                        ok = false;
                    }
                }
            }
            if(ok){
                count = count + update.get(update.size()/2);
            }
        }

        System.out.println(count);
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