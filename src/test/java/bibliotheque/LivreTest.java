package bibliotheque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import org.junit.Before;
import org.junit.Test;

public class LivreTest {

	private Livre livre;

	@Before
	public void setUp() {
		livre = new Livre("Histoire de Toto", 123, "Philippe");
	}

	@Test
	public void testGetAuteur() {
		assertThat(livre.getAuteur(), equalTo("Philippe"));
	}

	@Test
	public void testGetDureeMax() {
		assertThat(livre.getDureeMax(), isEmpty());
	}

}
