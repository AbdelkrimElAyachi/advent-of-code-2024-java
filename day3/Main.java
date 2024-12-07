import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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