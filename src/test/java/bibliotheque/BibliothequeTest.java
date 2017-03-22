package bibliotheque;

import static com.github.npathai.hamcrestopt.OptionalMatchers.hasValue;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BibliothequeTest {

	private Bibliotheque b;
	private Lecteur l;
	private Ouvrage o;

	@Before
	public void setUp() throws Exception {
		l = new EtudiantLicence("Jean");
		o = new Livre("Histoire de Toto", 123, "Philippe");
		b = new Bibliotheque();
	}

	@Test
	public void testNominal() throws EmpruntIncorrectException {
		assertThat(b.getOuvrages(), empty());
		b.referencer(o);
		assertThat(b.getOuvrages(), hasItem(o));

		assertThat(b.getLecteurs(), empty());
		b.inscrire(l);
		assertThat(b.getLecteurs(), hasItem(l));

		assertThat(b.getActifs(), empty());
		assertThat(b.getRevolus(), empty());
		Emprunt e = b.emprunter(l, o);
		assertThat(e.getDateRetour(), isEmpty());
		assertThat(b.getActifs(), hasItem(e));
		assertThat(l.getActifs(), hasItem(e));
		assertThat(o.getEmpruntActif(), hasValue(e));

		b.rendre(e);
		assertThat(e.getDateRetour(), isPresent());
		assertThat(b.getActifs(), empty());
		assertThat(b.getRevolus(), hasItem(e));
		assertThat(l.getActifs(), not(hasItem(e)));
		assertThat(o.getEmpruntActif(), isEmpty());
		assertThat(l.getRevolus(), hasItem(e));
		assertThat(o.getEmpruntsRevolus(), hasItem(e));

	}

	@Test(expected = EmpruntIncorrectException.class)
	public void testEmprunterDouble() throws EmpruntIncorrectException {
		b.inscrire(l);
		b.referencer(o);
		b.emprunter(l, o);
		b.emprunter(l, o);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void testEmprunterNonInscrit() throws EmpruntIncorrectException {
		b.referencer(o);
		b.emprunter(l, o);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void testEmprunterNonReference() throws EmpruntIncorrectException {
		b.inscrire(l);
		b.emprunter(l, o);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void testRendre() throws EmpruntIncorrectException {
		Emprunt e = new Emprunt(l, o);
		b.rendre(e);
	}

	@Test
	public void testInscrire() {
		assertThat(b.getLecteurs(), empty());
		b.inscrire(l);

		Set<Lecteur> lecteurs = new HashSet<Lecteur>(Arrays.asList(l));
		assertThat(b.getLecteurs(), equalTo(lecteurs));
		b.inscrire(l);
		assertThat(b.getLecteurs(), equalTo(lecteurs));
	}

	@Test
	public void testReferencer() {
		assertThat(b.getOuvrages(), empty());
		b.referencer(o);
		assertThat(b.getOuvrages(), hasItem(o));
	}

	@Test(expected = IllegalStateException.class)
	public void testReferencerEmprunte() throws EmpruntIncorrectException {
		Emprunt e = new Emprunt(l, o);
		o.setEmpruntActif(e);
		b.referencer(o);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReferencerDoublon() {
		b.referencer(o);
		Ouvrage o2 = new Livre("Bla", 123, "Blu");
		b.referencer(o2);
	}

	@Test
	public void testDereferencer() {
		assertThat(b.getOuvrages(), empty());
		b.dereferencer(o);
		assertThat(b.getOuvrages(), empty());
		b.referencer(o);
		assertThat(b.getOuvrages(), hasItem(o));
		b.dereferencer(o);
		assertThat(b.getOuvrages(), empty());
	}

	@Test(expected = IllegalStateException.class)
	public void testDereferenceEmprunte() throws EmpruntIncorrectException {
		b.referencer(o);
		b.inscrire(l);
		b.emprunter(l, o);
		b.dereferencer(o);
	}

	@Test
	public void testRechercherLecteur() {
		assertThat(b.rechercherLecteur("Toto"), empty());
		b.inscrire(l);
		assertThat(b.rechercherLecteur("Jean"), hasItem(l));
		Lecteur l2 = new EtudiantLicence("Jean");
		b.inscrire(l2);
		assertThat(b.rechercherLecteur("Jean"), hasItems(l, l2));
	}

	@Test
	public void testRechercherOuvrage() {
		assertThat(b.rechercherOuvrage(123), isEmpty());
		b.referencer(o);
		b.referencer(new Livre("Livre", 456, "B"));
		assertThat(b.rechercherOuvrage(123), hasValue(o));
	}

	@Test
	public void testRetards() throws EmpruntIncorrectException {
		b.inscrire(l);

		b.referencer(o);
		b.emprunter(l, o);
		assertThat(b.retards(), empty());

		Ouvrage o3 = new Livre("Livre", 12, "C");
		b.referencer(o3);
		b.emprunter(LocalDate.of(2016, 7, 1), l, o3);
		assertThat(b.retards(), empty());

		Ouvrage o2 = new DVD("Test", 98, "B");
		b.referencer(o2);
		Emprunt e = b.emprunter(LocalDate.of(2016, 7, 1), l, o2);
		assertThat(b.retards(), hasItem(e));

	}

	@Test
	public void testSecurite() {
		try {
			Set<Emprunt> revolus = b.getRevolus();
			revolus.add(new Emprunt(l, o));
			assertThat(b.getRevolus(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
		try {
			Set<Emprunt> actifs = b.getActifs();
			actifs.add(new Emprunt(l, o));
			assertThat(b.getActifs(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
		try {
			Set<Lecteur> lecteurs = b.getLecteurs();
			lecteurs.add(l);
			assertThat(b.getLecteurs(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
		try {
			Set<Ouvrage> ouvrages = b.getOuvrages();
			ouvrages.add(o);
			assertThat(b.getOuvrages(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
	}

}
