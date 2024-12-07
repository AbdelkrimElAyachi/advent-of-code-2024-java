import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static void main(String[] args){
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
        ArrayList<ArrayList<Integer>> listArrays = new ArrayList<>();

        // converting data into arraylist
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.isBlank()){
                continue;
            }
            // splitting the line by space " " or whatever number of spaces there is "       "
            String[] data = line.split("\\s+");
            ArrayList<Integer> arr = new ArrayList<>();
            for(String val : data){
                arr.add(Integer.valueOf(val));
            }
            listArrays.add(arr);
        }

        part1(listArrays);
        part2(listArrays);

    }

    // part 1 solution
    public static void part1(ArrayList<ArrayList<Integer>> listArrays){
        int count = 0;
        for(int i=0; i<listArrays.size(); i++){
            boolean lol = isSafe(listArrays.get(i));
            if(lol){
                count++;
            }
        }
        System.out.println("part 1 solution "+count);
    }


    // part 2 solution
    public static void part2(ArrayList<ArrayList<Integer>> listArrays){
        int count = 0;
        for(int i=0; i<listArrays.size(); i++){
            boolean lol = isSafe(listArrays.get(i));
            if(lol){
                count++;
            }
            // in case the array is not safe check if removing an element make it safe
            else{
                for(int j=0; j<listArrays.get(i).size(); j++){
                    ArrayList<Integer> clonedList = new ArrayList<>(listArrays.get(i));
                    // cloning the array to remove the element without effecting the original array
                    clonedList.remove(j);
                    boolean lmfao = isSafe(clonedList);
                    if(lmfao){
                        count++;
                        break;
                    }
                }
            }
        }
        System.out.println("part 2 solution "+count);
    }


    //checking if the element is safe
    public static boolean isSafe(ArrayList<Integer> list){
        boolean safe = false ;
        boolean increment ;
        increment = (list.get(1)-list.get(0)>0);

        for(int i=0; i<list.size()-1; i++){
            if(increment && ( (list.get(i+1)-list.get(i))<=3 && (list.get(i+1)-list.get(i))>=1 )){
                safe = true;
            }
            else if(!increment && ( (list.get(i)-list.get(i+1))<=3 && (list.get(i)-list.get(i+1)>=1 ) )){
                safe = true;
            }
            else{
                safe = false;
                break;
            }
        }
        return  safe;
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