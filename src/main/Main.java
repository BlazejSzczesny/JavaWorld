package main;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        World world=new World(9,9);
        int dziala=1;
        while (dziala==1){
            System.out.println("0 - Zakończ");
            System.out.println("[ENTER] - Następna runda");
            System.out.println("Podaj opcję:");
            Scanner scanner=new Scanner(System.in);
            String akcja=scanner.nextLine();
            switch (akcja){
                case "":
                    if(world.getTurn()==0) {
                    }
                    world.ifHeGoDied();
                    world.addAlien();
                    world.makeTurn();
                    world.ifReproduce();
                    world.natureProtection();
                    System.out.println(world.getBoard());
                    break;
                case "0":
                    dziala=0;

            }
        }


    }
}
