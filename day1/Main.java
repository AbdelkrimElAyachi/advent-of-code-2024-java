import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import  java.util.Scanner;


public class Main {


    public static void main(String[] argv) {
        File input = new File(argv[0]);

        ArrayList<Integer> Llist = new ArrayList<>();
        ArrayList<Integer> Rlist = new ArrayList<>();

        // reading input file
        if(!input.canRead()){
            throw new Error("cannot read file");
        }
        try {
            Scanner scan = new Scanner(input);

            while(scan.hasNextLine()){
                String data = scan.nextLine();
                if(data.isBlank()){
                    continue;
                }
                String[] words = data.replaceAll("\\s+", " ").split(" ");
                int L = Integer.parseInt(words[0]);
                int R = Integer.parseInt(words[1]);
                Llist.add(L);
                Rlist.add(R);
            }

        } catch (FileNotFoundException e) {
            System.err.println("error scanning file stream "+e);
        }


        part1(Rlist, Llist);
        part2(Rlist, Llist);

    }

    // part 1 solution
    public static void part1(ArrayList<Integer> Rlist,ArrayList<Integer> Llist){
        order(Rlist);
        order(Llist);
        int diff = 0;
        for(int i=0; i<Rlist.size(); i++){
            if(Llist.get(i) < Rlist.get(i)){
                diff = diff + Rlist.get(i) - Llist.get(i);
            }
            else{
                diff = diff + Llist.get(i) - Rlist.get(i);
            }
        }
        System.out.println("part 1 solution "+diff);
    }

    // part 2 solution
    public static void part2(ArrayList<Integer> Rlist,ArrayList<Integer> Llist){
        int score = 0;

        for(int i = 0; i<Llist.size(); i++){
            int number_of_repeats = 0;
            for(int j=0; j<Rlist.size(); j++){
                if(Llist.get(i).equals(Rlist.get(j))){
                    number_of_repeats++;
                }
            }
            score = score + number_of_repeats * Llist.get(i); 
        }

        System.out.println("part 2 solution "+score);
    }

    //bubble sort
    public static void order(ArrayList<Integer> list){
        boolean order;
        do { 
            order = true;
            for(int i=0; i<list.size()-1; i++){
                if(list.get(i) > list.get(i+1)){
                    int temp = list.get(i+1);
                    list.set(i+1,list.get(i));
                    list.set(i,temp);
                    order = false;
                }
            }
        } while (!order);
    }




}
