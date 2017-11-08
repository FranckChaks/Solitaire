package src;

public class Pioche{

    public  ArrayList<Carte> cartes;

    public Pioche() {
        cartes = new ArrayList<Carte>();
        for (int c = 0; c < 4 ; c++) {
            for (int v = 0; v < 13; v++) {
                Carte carte = new Carte(v, c);
                //card.visible = true;
                cartes.add(carte);
            }
        }
    }

    public Carte getCarte() {
        return cartes.remove(0);
    }

    public void shuffle() {
        ArrayList<Carte> newPioche = new ArrayList<Carte>();
        for (int i = 0; i < 51; i++) {
            int rand = new Random().nextInt(cartes.size());
            newPioche.add(cartes.remove(rand));
        }
        newPioche.add(cartes.remove(0));
        cartes = newPioche;
    }

    public void print() {
        for (int i = 0; i < 52; i++) {
            Carte carte = cartes.get(i);
            carte.makeVisible();
            carte.print();
            System.out.println();
        }
    }


}
