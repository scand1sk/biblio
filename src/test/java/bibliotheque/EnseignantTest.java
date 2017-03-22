package bibliotheque;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EnseignantTest {

	private Lecteur l = new Enseignant("Jean");

	@Test
	public void getNbMaxOuvrageTest() {
		assertThat(l.getNbMaxOuvrage(), equalTo(32));
	}

	@Test
	public void getDureeMaxEmpruntTest() {
		assertThat(l.getDureeMaxEmprunt(), equalTo(60));
	}
}
