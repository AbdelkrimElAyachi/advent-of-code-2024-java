import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// we need to find the word xmas no matter how it appears
// examples

// XMAS SAMX
//  X X  X S    S
//   M   M A   A A
//  A A  A M  M   M
// S   S S X X     X

// so its either appear 
// horizantly (normal and backward), vertical (normal and backward) , or diagonal

class Main {

    public static void main(String[] args) {
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
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        while(scan.hasNextLine()){
        }

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

    public static boolean isXmasHorizontal(int i, int j, ArrayList<ArrayList<String>> list){
        switch{
            case "X":
                System.out.println("X");
                break;
            default:
                System.out.println("unknown character");
        }
    }

    public static boolean isXMASVertical(int i, int j, ArrayList<ArrayList<String>> list){
    }

    public static boolean isXMASDiagonal(int i, int j, ArrayList<ArrayList<String>> list){

    }

}