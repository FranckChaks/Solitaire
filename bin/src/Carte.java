public class Carte {

	    public boolean red;

	    private boolean visible;

	    int nombre;

	    int couleur;

	    public Carte(int nombre, int couleur) {
	        this.red = couleur < 2;
	        this.nombre = nombre;
	        this.couleur = couleur;
	        this.visible = false;
	    }


	    public void print() {
	        String[] forme = {"CA","CO","TR","PI"};
	        String[] valeur = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "V", "D", "R"};
	        if (visible) {
	            System.out.print(forme[couleur] + valeur[nombre]);
	        } else {
	            System.out.print("--");
	        }
	    }

	    public void makeVisible() {
	        visible = true;
	    }

	    public boolean isVisible() {
	        return visible;
	    }
	}

