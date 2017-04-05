package bibliotheque;

import static com.github.npathai.hamcrestopt.OptionalMatchers.hasValue;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class OuvrageTest {

	private DVD dvd;

	@Before
	public void setUp() throws Exception {
		dvd = new DVD("Film de Toto", 123, "Philippe");
	}

	private Lecteur lecteurTest() {
		return new EtudiantLicence("Jean");
	}

	private Emprunt empruntTest(Lecteur l) {
		return new Emprunt(l, dvd);
	}

	private Emprunt empruntTest() {
		return empruntTest(lecteurTest());

	}

	@Test
	public void empruntNominal() throws EmpruntIncorrectException {
		Emprunt e = empruntTest();
		assertFalse(dvd.isEmprunte());
		assertThat(dvd.getEmpruntActif(), isEmpty());

		dvd.setEmpruntActif(e);

		assertThat(dvd.getEmpruntActif(), hasValue(equalTo(e)));
		assertTrue(dvd.isEmprunte());

		e.setDateRetour(LocalDate.now());

		dvd.rendre();

		assertThat(dvd.getEmpruntActif(), isEmpty());
		assertFalse(dvd.isEmprunte());

		assertThat(dvd.getEmpruntsRevolus(), hasItem(e));

	}

	@Test(expected = EmpruntIncorrectException.class)
	public void setEmpruntActifDouble() throws EmpruntIncorrectException {
		Lecteur l = lecteurTest();
		Emprunt e = empruntTest(l);
		dvd.setEmpruntActif(e);

		Emprunt e2 = new Emprunt(l, dvd);
		dvd.setEmpruntActif(e2);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void setEmpruntActifIncorrect() throws EmpruntIncorrectException {
		Lecteur l = lecteurTest();
		Ouvrage o = new DVD("Histoire de Titi", 456, "Vincent");
		Emprunt e1 = new Emprunt(l, o);

		dvd.setEmpruntActif(e1);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void testRendreNonEmprunte() throws EmpruntIncorrectException {
		dvd.rendre();
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void testRendrePasDeDate() throws EmpruntIncorrectException {
		Emprunt e = empruntTest();
		dvd.setEmpruntActif(e);
		dvd.rendre();
	}

	@Test
	public void testSecuriteRevolus() {
		Emprunt e = empruntTest();
		try {
			Set<Emprunt> revolus = dvd.getEmpruntsRevolus();
			revolus.add(e);
			assertThat(dvd.getEmpruntsRevolus(), empty());
		} catch (UnsupportedOperationException exc) {
			// OK
		}
	}

	@Test
	public void testGetTitre() {
		assertThat(dvd.getTitre(), equalTo("Film de Toto"));
	}

	@Test
	public void testGetRef() {
		assertThat(dvd.getRef(), equalTo(123));
	}

}
