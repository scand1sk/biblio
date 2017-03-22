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

	private DVD dvd;
	private Lecteur l;
	private Emprunt e;

	@Before
	public void setUp() throws Exception {
		dvd = new DVD("Film de Toto", 123, "Philippe");
		l = new EtudiantLicence("Jean");
		e = new Emprunt(l, dvd);
	}

	@Test
	public void empruntNominal() throws EmpruntIncorrectException {
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
		Emprunt e2 = new Emprunt(l2, dvd);
		l.addEmpruntActif(e2);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void rendreSansDate() throws EmpruntIncorrectException {
		l.addEmpruntActif(e);
		l.rendre(e);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void rendreInactif() throws EmpruntIncorrectException {
		l.rendre(e);
	}

	@Test
	public void testSecurite() {
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
