import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        while (true) {
            Solitaire solitaire = new Solitaire();
            solitaire.drawStack();
            solitaire.print();
            Scanner in = new Scanner(System.in);
            while (true) {
                String next = in.next().toLowerCase();

                if (next.equals("help")) {
                    //System.out.println();
                    System.out.print("Press \"m\" to move cards\n");
                    System.out.print("Press \"d\" to draw from the stack\n");
                    System.out.print("Press \"new\" to start a new game\n");
                    System.out.print("Press \"redraw\" to redraw the game\n");
                    System.out.print("Press \"quit\" to quit the game\n");
                    System.out.print("Press \"size\" to print the size of the stack\n");
                    System.out.println();
                } else if (next.equals("d")) {
                    int from = in.nextInt();
                    int to = in.nextInt();
                    int number = in.nextInt();

                    solitaire.move(from, to, number);
                    solitaire.print();
                } else if (next.equals("d")) {
                	solitaire.drawStack();
                	solitaire.print();
                } else if (next.equals("new")) {
                    break;
                } else if (next.equals("redraw")) {
                	solitaire.print();
                } else if (next.equals("quit")) {
                    break;
                } else if (next.equals("size")) {
                	solitaire.printSize();
                } else {
                    System.out.print("Veuillez entrer une option valide\n");
                }

                if (solitaire.win()) {
                	System.out.println("k");
                    System.out.print("*****************\n");
                    System.out.print("*****Gay Gay*****\n");
                    System.out.print("*****************\n");
                    System.out.print("**Pour regeeker**\n");
                    System.out.print("***ENTRER \"nouveau\"***\n");
                    System.out.print("*****************\n");
                }
            }
            in.close();
        }
    }
}
