package bibliotheque;

import java.util.Optional;

/**
 * Un DVD est un Ouvrage particulier :
 * <ul>
 * <li>qui possède un réalisateur,
 * <li>qui a une durée maximale d’emprunt de 7 jours
 * </ul>
 *
 */
public class DVD extends Ouvrage {

	private final String real;

	/**
	 * Construit un DVD
	 * 
	 * @param titre
	 * @param ref
	 * @param real
	 */
	public DVD(String titre, int ref, String real) {
		super(titre, ref);
		this.real = real;
	}

	@Override
	public Optional<Integer> getDureeMax() {
		return Optional.of(7);
	}

	/**
	 * 
	 * @return le nom du réalisateur
	 */
	public String getReal() {
		return real;
	}

	@Override
	public String toString() {
		return "DVD [real=" + real + ", toString()=" + super.toString() + "]";
	}

}
