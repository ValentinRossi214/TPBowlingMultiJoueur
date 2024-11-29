package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MultiPlayerGameTest {
	
	private PartieMultiJoueurs partie;
	
	@BeforeEach
	public void setUp() {
		partie = new PartieMultiJoueurs();
	}

	@Test
	void testDemarrerPartieSansJoueursCasNull() {
		try {
			partie.demarreNouvellePartie(null);
			fail("On peut démarrer une partie sans joueurs");
		} catch (IllegalArgumentException e) {
			assertEquals("Il faut au moins un joueur", e.getMessage());
		}
	}
	
	@Test
	void testDemarrerPartieSansJoueursCasTaille0() {
		try {
			partie.demarreNouvellePartie(new String[0]);
			fail("On peut démarrer une partie sans joueurs");
		} catch (IllegalArgumentException e) {
			assertEquals("Il faut au moins un joueur", e.getMessage());
		}
	}
	
	@Test
	void testLancerPartieNonDemarree() {
		try {
			partie.enregistreLancer(5);
			fail("La partie n'est pas démarrée mais on peut quand même lancer");
		} catch (IllegalStateException e) {
			assertEquals("La partie n'est pas démarrée", e.getMessage());
		}
	}
	
	@Test
	void testScorePourJoueurInexistant() {
		partie.demarreNouvellePartie(new String[] {"Alice", "Bob"});
		try {
			partie.scorePour("Inconnu");
			fail("On peut demander le score d'un joueur qui n'existe pas");
		} catch (IllegalArgumentException e) {
			assertEquals("Le joueur Inconnu ne joue pas dans cette partie", e.getMessage());
		}
	}
	
	@Test
	void testScorePourJoueurs() {
		partie.demarreNouvellePartie(new String[] {"Alice", "Bob"});
		
		partie.enregistreLancer(2);
		partie.enregistreLancer(2);
		
		partie.enregistreLancer(10);
		
		assertEquals(2 + 2, partie.scorePour("Alice"));
		assertEquals(10, partie.scorePour("Bob"));
	}
	
}
