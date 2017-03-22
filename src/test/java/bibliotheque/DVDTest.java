package bibliotheque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.github.npathai.hamcrestopt.OptionalMatchers.hasValue;
import org.junit.Before;
import org.junit.Test;

public class DVDTest {

	private DVD dvd;

	@Before
	public void setUp() {
		dvd = new DVD("Film de Toto", 123, "Philippe");
	}

	@Test
	public void testGetReal() {
		assertThat(dvd.getReal(), equalTo("Philippe"));
	}

	@Test
	public void testGetDureeMax() {
		assertThat(dvd.getDureeMax(), hasValue(7));
	}

}
