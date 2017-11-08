package src;

public class Solitaire {
    private Pioche pioche;

    private LinkedList<Card>[] sets;

    public Solitaire() {
        pioche = new Pioche();
        pioche.shuffle();

        sets = new LinkedList[13];//0-6 Spielstapel, 7-10 Ablegestapel, 11 Stapel, 12 Umgedrehte Karten
        for (int i = 0; i < 13; i++) {
            sets[i] = new LinkedList<Carte>();
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i+1; j++) {
                sets[i].add(deck.getCarte());
            }
            sets[i].getLast().makeVisible();
        }

        for (int i = 0; i < 24; i++) {
            Carte carte= pioche.getCarte();
            carte.makeVisible();
            sets[11].add(carte);
        }
    }

    public void print() {
        for (int i = 0; i < 7; i++) {
            System.out.print(i + ":\t");
            int size = sets[i].size();
            if (size > 0) {
                for (int j = 0; j < size; j++){
                    sets[i].get(j).print();
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println();

        for (int i = 7; i < 11; i++) {
            System.out.print(i + ":\t");
            int size = sets[i].size();
            if (size > 0) {
                for (int j = 0; j < size; j++){
                    sets[i].get(j).print();
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.print(12 + ":\t");
        int size = sets[12].size();
        if (size > 0) {
            for (int j = 0; j < size; j++){
                sets[12].get(j).print();
                System.out.print(", ");
            }
        }

        System.out.println();
        System.out.print("****************************************************");
        System.out.println();
        System.out.println();
    }

    public void drawStack() {
        int size = sets[12].size();
        for (int i = 0; i < size; i++) {
            sets[11].addLast(sets[12].removeLast());
        }
        for (int i = 0; i < (sets[11].size() > 2 ? 3 : sets[11].size()); i++) {
            sets[12].add(sets[11].removeFirst());
        }
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
            System.out.print("Not possible, zero cards\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        }

        if (to > 10) {
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Not permitted, stack\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            return false;
        }

        if (from > 12 || from == 11) {
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Not permitted!\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            return false;
        }

        if (to > 6) {
            if (number > 1) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Not permitted, to many cards\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }


            if (sets[from].isEmpty()) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Not permitted, stack from empty\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }

            if (!sets[from].getLast().isVisible()) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Not permitted, card not visible\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }


            if (checkPile(sets[to].size() == 0? null : sets[to].getLast(), sets[from].getLast())) {
                sets[to].addLast(sets[from].removeLast());
                if (!sets[from].isEmpty()) {
                    sets[from].getLast().makeVisible();
                }
                if (sets[12].isEmpty()) {
                    drawStack();
                }
                return true;
            }

            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            System.out.print("Not permitted, check didn't pass\n");
            System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            return false;
        } else {
            if (from > 6 && number > 1) {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Not permitted, to many cards\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }

            if (number <= sets[from].size()) {
                LinkedList<Carte> temp = new LinkedList<Carte>();
                for (int i = 0; i < number; i++) {
                    temp.addFirst(sets[from].removeLast());
                }

                Carte last = sets[to].size() > 0? sets[to].getLast() : null;
                Carte first = temp.getFirst();

                if (first == null) {
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    System.out.print("Not permitted, first is null\n");
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    repair(temp, from);
                    return false;
                }

                if (!first.isVisible()) {

                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    System.out.print("Not permitted, card not visible\n");
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    repair(temp, from);
                    return false;
                }

                if (checkGame(last, first)) {
                    sets[to].addAll(temp);
                    if (!sets[from].isEmpty()) {
                        sets[from].getLast().makeVisible();
                    }
                    if (sets[12].isEmpty()) {
                        drawStack();
                    }
                    return true;
                } else {
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    System.out.print("Not permitted, check didn't pass\n");
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    repair(temp, from);
                    return false;
                }
            } else {
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                System.out.print("Not possible, not enough cards\n");
                System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                return false;
            }
        }

    }

    private void repair(LinkedList<Carte> temp, int to) {
        while (!temp.isEmpty()) {
            sets[to].addLast(temp.removeFirst());
        }
    }

    public boolean win() {
        return (sets[7].size() == 13 && sets[8].size() == 13 && sets[9].size() == 13 && sets[10].size() == 13);
    }

    public void printSize() {
        System.out.print("Stack size:\t" + sets[11].size() + " (without visible cards)\n");
    }

}