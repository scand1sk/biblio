package bibliotheque;

import java.util.Optional;

/**
 * Un livre est un Ouvrage particulier :
 * <ul>
 * <li>qui possède un auteur,
 * <li>qui n’a pas de durée maximale d’emprunt
 * </ul>
 *
 */
public class Livre extends Ouvrage {


	/**
	 * Construit un livre
	 * 
	 * @param titre
	 * @param ref
	 * @param auteur
	 */
	public Livre(String titre, int ref, String auteur) {
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
	 * @return l'auteur du livre
	 */
	public String getAuteur() {
		// TODO
		throw new NotImplementedError();
	}

	@Override
	public String toString() {
		// TODO
		throw new NotImplementedError();
	}

}
