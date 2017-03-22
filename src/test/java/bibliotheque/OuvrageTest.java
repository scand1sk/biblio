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
		dvd.setEmpruntActif(e);

		Emprunt e2 = new Emprunt(l, dvd);
		dvd.setEmpruntActif(e2);
	}

	@Test(expected = EmpruntIncorrectException.class)
	public void setEmpruntActifIncorrect() throws EmpruntIncorrectException {
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
		dvd.setEmpruntActif(e);
		dvd.rendre();
	}

	@Test
	public void testSecuriteRevolus() {
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
