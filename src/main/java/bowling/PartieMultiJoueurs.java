package bowling;

import java.util.ArrayList;

public class PartieMultiJoueurs implements  IPartieMultiJoueurs {
	private PartieMonoJoueur[] parties;
	private int joueurCourant;
	private String[] nomsDesJoueurs;
	
	public PartieMultiJoueurs() {
	}
	
	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("Il faut au moins un joueur");
		}

		this.nomsDesJoueurs = nomsDesJoueurs;
		
		parties = new PartieMonoJoueur[nomsDesJoueurs.length];
		
		for (int i = 0; i < nomsDesJoueurs.length; i++) {
			parties[i] = new PartieMonoJoueur();
		}
		
		return "Prochain tir : " + nomsDesJoueurs[0] + ", tour n°" + parties[0].numeroTourCourant() + ", boule n°" + parties[0].numeroProchainLancer();
	}
	
	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if (parties == null) {
			throw new IllegalStateException("La partie n'est pas démarrée");
		}
		
		parties[joueurCourant].enregistreLancer(nombreDeQuillesAbattues);
		
		if (parties[joueurCourant].numeroProchainLancer() == 1) {
			if(joueurCourant + 1 < parties.length) {
				joueurCourant++;
			} else {
				joueurCourant = 0;
			}
		}
		
		return "Prochain tir : " + nomsDesJoueurs[joueurCourant] + ", tour n°" + parties[joueurCourant].numeroTourCourant() + ", boule n°" + parties[joueurCourant].numeroProchainLancer();
	}
	
	@Override
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		for (int i = 0; i < nomsDesJoueurs.length; i++) {
			if (nomsDesJoueurs[i].equals(nomDuJoueur)) {
				return parties[i].score();
			}
		}
		
		throw new IllegalArgumentException("Le joueur " + nomDuJoueur + " ne joue pas dans cette partie");
	}
}
