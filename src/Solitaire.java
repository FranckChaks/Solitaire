import java.util.ArrayList;
import java.util.LinkedList;

public class Solitaire {
	
    private Pioche pioche;

    private ArrayList<LinkedList<Carte>> sets;

    public Solitaire() {
        pioche = new Pioche();
        pioche.shuffle();

        sets = new ArrayList<LinkedList<Carte>>();
        for (int i = 0; i < 13; i++) {
            sets.add(new LinkedList<Carte>());
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i+1; j++) {
                sets.get(i).add(pioche.getCarte());
            }
            sets.get(i).getLast().makeVisible();
        }

        for (int i = 0; i < 24; i++) {
            Carte carte= pioche.getCarte();
            carte.makeVisible();
            sets.get(11).add(carte);
        }
    }

    public void print() {
        for (int i = 0; i < 7; i++) {
            System.out.print(i + ":\t");
            int size = sets.get(i).size();
            if (size > 0) {
                for (int j = 0; j < size; j++){
                    sets.get(i).get(j).print();
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println();

        for (int i = 7; i < 11; i++) {
            System.out.print(i + ": \t");
            int size = sets.get(i).size();
            if (size > 0) {
                for (int j = 0; j < size; j++){
                    sets.get(i).get(j).print();
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print(12 + ": Pioche:\t");
        int size = sets.get(12).size();
        if (size > 0) {
            for (int j = 0; j < size; j++){
                sets.get(12).get(j).print();
                System.out.print(", ");
            }
        }

        System.out.println();
        System.out.print("****************************************************");
        System.out.println();
        System.out.println("Pour voir les commandes, entrez 'help'.");
        System.out.println();
        System.out.println();
    }

    public void drawStack() {
        if(sets.get(12).size() > 0)
        	sets.get(11).addLast(sets.get(12).removeLast());
        sets.get(12).add(sets.get(11).removeFirst());
    }

    public boolean checkGame(Carte top, Carte bottom) {
        if (top == null) {
            return bottom.nombre == 12;
        }
        return (top.red != bottom.red) && (top.nombre - 1 == bottom.nombre);
    }

    public boolean checkPile(Carte top, Carte bottom) {
        if (top == null) {
            return bottom.nombre == 0;
        }
        return (top.couleur == bottom.couleur) && (top.nombre + 1 == bottom.nombre);
    }

    public boolean move(int from, int to, int number) {
        if (number <= 0) {
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Pas Possible, pas de carte\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        }

        if (to > 10) {
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Non permis\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            return false;
        }

        if (from > 12 || from == 11) {
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Non permis !\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            return false;
        }

        if (to > 6) {
            if (number > 1) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Mouvement impossible, réessayez !\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }


            if (sets.get(from).isEmpty()) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Impossible pile vide\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }

            if (!sets.get(from).getLast().isVisible()) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Impossible, carte cachée\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }


            if (checkPile(sets.get(to).size() == 0? null : sets.get(to).getLast(), sets.get(from).getLast())) {
                sets.get(to).addLast(sets.get(from).removeLast());
                if (!sets.get(from).isEmpty()) {
                    sets.get(from).getLast().makeVisible();
                }
                if (sets.get(12).isEmpty()) {
                    drawStack();
                }
                return true;
            }

            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Mouvement impossible, réessayez !\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            return false;
        } else {
            if (from > 6 && number > 1) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Impossible, trop de carte mamen\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }

            if (number <= sets.get(from).size()) {
                LinkedList<Carte> temp = new LinkedList<Carte>();
                for (int i = 0; i < number; i++) {
                    temp.addFirst(sets.get(from).removeLast());
                }

                Carte last = sets.get(to).size() > 0? sets.get(to).getLast() : null;
                Carte first = temp.getFirst();

                if (first == null) {
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    System.out.print("Impossible, le premier est nul\n");
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    repair(temp, from);
                    return false;
                }

                if (!first.isVisible()) {

                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    System.out.print("Impossible, carte cachée mamen\n");
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    repair(temp, from);
                    return false;
                }

                if (checkGame(last, first)) {
                    sets.get(to).addAll(temp);
                    if (!sets.get(from).isEmpty()) {
                        sets.get(from).getLast().makeVisible();
                    }
                    if (sets.get(from).isEmpty()) {
                        drawStack();
                    }
                    return true;
                } else {
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    System.out.print("Impossible, contrôle pas passé\n");
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    repair(temp, from);
                    return false;
                }
            } else {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Impossible, pas assez cartes\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }

        }
       
    }

    private void repair(LinkedList<Carte> temp, int to) {
        while (!temp.isEmpty()) {
            sets.get(to).addLast(temp.removeFirst());
        }
    }

    public boolean win() {
        return (sets.get(7).size() == 13 && sets.get(8).size() == 13 && sets.get(9).size() == 13 && sets.get(10).size() == 13);
    }

    public void printSize() {
        System.out.print("Taille de la pile:\t" + sets.get(11).size() + " (Sans les cartes visibles)\n");
    }

}