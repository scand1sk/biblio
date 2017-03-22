package bibliotheque;

import static com.github.npathai.hamcrestopt.OptionalMatchers.hasValue;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class EmpruntTest {

	private Lecteur l = new EtudiantLicence("Jean");
	private Ouvrage o = new DVD("Histoire de Toto", 456, "Sophie");
	private Emprunt e;

	@Before
	public void setUp() {
		e = new Emprunt(LocalDate.of(2017, 3, 15), l, o);
	}

	@Test
	public void testGetDateEmprunt() {
		assertThat(e.getDateEmprunt(), equalTo(LocalDate.of(2017, 3, 15)));
	}

	@Test
	public void testGetDateLimite() {

		assertThat(e.getDateLimite(), hasValue(LocalDate.of(2017, 3, 22)));

		Ouvrage o2 = new Livre("Histoire de Titi", 987, "Philippe");
		Emprunt e2 = new Emprunt(l, o2);
		assertThat(e2.getDateLimite(), isEmpty());
	}

	@Test
	public void testDateRetourNominal() {
		assertThat(e.getDateRetour(), isEmpty());
		e.setDateRetour(LocalDate.of(2017, 3, 16));
		assertThat(e.getDateRetour(), hasValue(LocalDate.of(2017, 3, 16)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDateRetourInPast() {
		e.setDateRetour(LocalDate.of(2017, 3, 14));
	}

	@Test(expected = IllegalStateException.class)
	public void testSetDateRetourAlreadyDefined() {
		e.setDateRetour(LocalDate.of(2017, 3, 16));
		e.setDateRetour(LocalDate.of(2017, 3, 17));
	}

	@Test
	public void testGetLecteur() {
		assertThat(e.getLecteur(), equalTo(l));
	}

	@Test
	public void testGetOuvrage() {
		assertThat(e.getOuvrage(), equalTo(o));
	}

}
