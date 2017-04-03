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

	/**
	 * Construit un DVD
	 * 
	 * @param titre
	 * @param ref
	 * @param real
	 */
	public DVD(String titre, int ref, String real) {
		super(titre, ref);
		// TODO
		throw new NotImplementedError();
	}

	@Override
	public Optional<Integer> getDureeMax() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return le nom du réalisateur
	 */
	public String getReal() {
		// TODO
		throw new NotImplementedError();
	}

	@Override
	public String toString() {
		// TODO
		throw new NotImplementedError();
	}

}
