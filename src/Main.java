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
                    System.out.println();
                    System.out.print("Press \"d\" Déplacer la carte (entrez ensuite le numéro de la ligne d'origine, puis la ligne d'arrivée et le position de la carte visible\n");
                    System.out.print("Press \"p\" Piocher une carte\n");
                    System.out.print("Press \"nouveau\" Nouveau jeu\n");
                    System.out.print("Press \"r\" Réafficher le game ma men\n");
                    System.out.print("Press \"quitter\" quitter la partie\n");
                    System.out.print("Press \"afficher\" afficher la taille de la pile\n");
                    System.out.println();
                } else if (next.equals("d")) {
                    int from = in.nextInt();
                    int to = in.nextInt();
                    int number = in.nextInt();

                    solitaire.move(from, to, number);
                    solitaire.print();
                } else if (next.equals("p")) {
                    solitaire.drawStack();
                    solitaire.print();
                } else if (next.equals("nouveau")) {
                    break;
                } else if (next.equals("r")) {
                    solitaire.print();
                } else if (next.equals("quitter")) {
                    break;
                } else if (next.equals("afficher")) {
                    solitaire.printSize();
                } else {
                    System.out.print("Veuillez entrer une option valide\n");
                }

                if (solitaire.win()) {
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
