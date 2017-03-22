package bibliotheque;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EtudiantLicenceTest {

	private Lecteur l = new EtudiantLicence("Jean");

	@Test
	public void getNbMaxOuvrageTest() {
		assertThat(l.getNbMaxOuvrage(), equalTo(8));
	}

	@Test
	public void getDureeMaxEmpruntTest() {
		assertThat(l.getDureeMaxEmprunt(), equalTo(14));
	}
}
