import java.util.ArrayList;
import java.util.Random;


	public class Pioche{

	    public  ArrayList<Carte> cartes;

	    public Pioche() {
	        cartes = new ArrayList<Carte>();
	        for (int i = 0; i < 4 ; i++) 
	            for (int j = 0; j < 13; j++) 
	                cartes.add(new Carte(j, i));
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

