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

	private final String auteur;

	/**
	 * Construit un livre
	 * 
	 * @param titre
	 * @param ref
	 * @param auteur
	 */
	public Livre(String titre, int ref, String auteur) {
		super(titre, ref);
		this.auteur = auteur;
	}

	@Override
	public Optional<Integer> getDureeMax() {
		return Optional.empty();
	}

	/**
	 * @return l'auteur du livre
	 */
	public String getAuteur() {
		return auteur;
	}

	@Override
	public String toString() {
		return "Livre [auteur=" + auteur + ", toString()=" + super.toString() + "]";
	}

}
