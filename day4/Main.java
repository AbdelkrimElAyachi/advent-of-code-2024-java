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
        ArrayList<String> list = new ArrayList<>();


        while(scan.hasNextLine()){
            String line = scan.nextLine();
            list.add(line);
        }

        part1(list);
        part2(list);
    }

    public static void part2(ArrayList<String> list){
        int count = 0;
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<list.get(i).length(); j++){
                if('A' == list.get(i).charAt(j))
                    if( isX_MASDiagonal(i, j, list) )
                        count++;
                
            }
        }

        System.out.println(count);   
    }

    public static void part1(ArrayList<String> list){

        int count = 0;
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<list.get(i).length(); j++){
                if('X' == list.get(i).charAt(j))
                    count = count + isXMASHorizontal(i, j, list) + isXMASVertical(i, j, list) + isXMASDiagonal(i, j, list);
                
            }
        }

        System.out.println(count);
    }

    public static int isXMASHorizontal(int i, int j, ArrayList<String> list){
        int count = 0;
        if((j+4 <= list.get(i).length()) && "XMAS".equals(list.get(i).substring(j,j+4))){
            count++;
        }
        if(j>=3 && "SAMX".equals(list.get(i).substring(j-3,j+1))){
            count++;
        }
        return count;
    }


    public static int isXMASVertical(int i, int j, ArrayList<String> list){
        int count = 0;
        String xmas;

        if( i+4 <= list.size() ) 
        {
            xmas =  ""+list.get(i).charAt(j) + list.get(i+1).charAt(j) + list.get(i+2).charAt(j) + list.get(i+3).charAt(j);
            if(xmas.equals("XMAS"))
                count++;
        }
        if( i >= 3){
            xmas = ""+list.get(i).charAt(j) + list.get(i-1).charAt(j) + list.get(i-2).charAt(j) + list.get(i-3).charAt(j);
            if(xmas.equals("XMAS"))
                count++;
        }
        return count;
    }


    public static int isXMASDiagonal(int i, int j, ArrayList<String> list){
        int count = 0;
        String xmas;

        if( i+4 <= list.size()  && j>=3 ) 
        {
            xmas =  ""+list.get(i).charAt(j) + list.get(i+1).charAt(j-1) + list.get(i+2).charAt(j-2) + list.get(i+3).charAt(j-3);
            if(xmas.equals("XMAS"))
                count++;
        }
        if(i+4 <= list.size() && j+4 <= list.get(i).length()){
            xmas =  ""+list.get(i).charAt(j) + list.get(i+1).charAt(j+1) + list.get(i+2).charAt(j+2) + list.get(i+3).charAt(j+3);
            if(xmas.equals("XMAS"))
                count++;
        }
        if( i >= 3 && j>=3){
            xmas = ""+list.get(i).charAt(j) + list.get(i-1).charAt(j-1) + list.get(i-2).charAt(j-2) + list.get(i-3).charAt(j-3);
            if(xmas.equals("XMAS"))
                count++;
        }
        if( i >= 3 && j+4 <= list.get(i).length()){
            xmas = ""+list.get(i).charAt(j) + list.get(i-1).charAt(j+1) + list.get(i-2).charAt(j+2) + list.get(i-3).charAt(j+3);
            if(xmas.equals("XMAS"))
                count++;
        }
        return count;
    }

    public static boolean isX_MASDiagonal(int i, int j, ArrayList<String> list){
        int MASNumber = 0;
        boolean isit = false;
        String mas;

        if(i>=1 && j >= 1 && i+1 < list.size() && j+1 < list.get(i).length()){
            //check the two characters above A
            if( 'M' == list.get(i-1).charAt(j-1) ){
                //check if it is diagonal
                mas = "M" + list.get(i).charAt(j) + list.get(i+1).charAt(j+1);
                if("MAS".equals(mas)){
                    MASNumber++;
                }
            }
            if( 'M' == list.get(i-1).charAt(j+1) ){
                //check if it is diagonal
                mas = "M" + list.get(i).charAt(j) + list.get(i+1).charAt(j-1);
                if("MAS".equals(mas)){
                    MASNumber++;
                }
            }
            //check the two down A
            if( 'M' == list.get(i+1).charAt(j-1) ){
                //check if it is diagonal
                mas = "M" + list.get(i).charAt(j) + list.get(i-1).charAt(j+1);
                if("MAS".equals(mas)){
                    MASNumber++;
                }
            }
            if( 'M' == list.get(i+1).charAt(j+1) ){
                //check if it is diagonal
                mas = "M" + list.get(i).charAt(j) + list.get(i-1).charAt(j-1);
                if("MAS".equals(mas)){
                    MASNumber++;
                }
            }
            if(MASNumber == 2){
                isit = true;
            }
        }
        return isit;
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