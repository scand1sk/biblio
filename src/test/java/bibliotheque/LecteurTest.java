package bibliotheque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class LecteurTest {

	private Lecteur l;

	@Before
	public void setUp() throws Exception {
		l = new EtudiantLicence("Jean");
	}

	private DVD dvdTest() {
		return new DVD("Film de Toto", 123, "Philippe");
	}

	private Emprunt empruntTest(DVD dvd) {
		return new Emprunt(l, dvd);
	}

	private Emprunt empruntTest() {
		return empruntTest(dvdTest());

	}

	@Test
	public void empruntNominal() throws EmpruntIncorrectException {
		Emprunt e = empruntTest();
		assertThat(l.getActifs(), empty());
		assertThat(l.getRevolus(), empty());

		l.addEmpruntActif(e);

		assertThat(l.getActifs(), hasItem(e));
		assertThat(l.getRevolus(), empty());

		e.setDateRetour(LocalDate.now());

		l.rendre(e);

		assertThat(l.getActifs(), empty());
		assertThat(l.getRevolus(), hasItem(e));

	}

	@Test(expected = EmpruntIncorrectException.class)
	public void empruntLimiteMax() throws EmpruntIncorrectException {
		int maxOuvrage = l.getNbMaxOuvrage();
		Iterator<Emprunt> emprunts = Stream.generate(() -> new DVD("histoire", 5, "auteur")).map(d -> new Emprunt(l, d))
				.iterator();
		for (int i = 0; i <= maxOuvrage; i++) {
			l.addEmpruntActif(emprunts.next());
		}
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void empruntIncorrect() throws EmpruntIncorrectException {
		Lecteur l2 = new EtudiantLicence("Jacques");
		DVD dvd = dvdTest();
		Emprunt e2 = new Emprunt(l2, dvd);
		// Ajout un emprunt du lecteur l2 au lecteur l
		l.addEmpruntActif(e2);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void rendreSansDate() throws EmpruntIncorrectException {
		Emprunt e = empruntTest();
		l.addEmpruntActif(e);
		l.rendre(e);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void rendreInactif() throws EmpruntIncorrectException {
		Emprunt e = empruntTest();
		l.rendre(e);
	}

	@Test
	public void testSecurite() {
		Emprunt e = empruntTest();
		try {
			Set<Emprunt> revolus = l.getRevolus();
			revolus.add(e);
			assertThat(l.getRevolus(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
		try {
			Set<Emprunt> revolus = l.getActifs();
			revolus.add(e);
			assertThat(l.getActifs(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
	}

	@Test
	public void getNomTest() {
		assertThat(l.getNom(), equalTo("Jean"));
	}

}
