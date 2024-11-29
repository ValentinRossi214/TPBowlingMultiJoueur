package bowling;

public class Main {
	public static void main(String[] args) {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		
		System.out.println(partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"}));
		
		System.out.println(partie.enregistreLancer(5));
		System.out.println(partie.enregistreLancer(3));
		System.out.println(partie.enregistreLancer(10));
		System.out.println(partie.enregistreLancer(7));
		System.out.println(partie.enregistreLancer(3));

		System.out.println(partie.scorePour("Pierre"));
		System.out.println(partie.scorePour("Paul"));
	}
}
