package bibliotheque;

import java.util.Optional;
import java.util.Set;

/**
 * Représente un ouvrage pouvant être emprunté à la bibliothèque
 */
public abstract class Ouvrage {

	/**
	 * Construit un ouvrage avec le titre et la référence indiqués
	 * 
	 * @param titre
	 * @param ref
	 */
	public Ouvrage(String titre, int ref) {
		super();
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Permet de définir l'emprunt actif d'un ouvrage.
	 * 
	 * @throws EmpruntIncorrectException
	 *             si l'emprunt ne correspond pas à l'ouvrage, ou si il y a déjà
	 *             un emprunt actif. Appeler d'abord {@link #unsetEmpruntActif}.
	 * 
	 * @param emprunt
	 */
	public void setEmpruntActif(Emprunt emprunt) throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Permet de basculer l'emprunt actif de l'ouvrage dans les emprunts révolus
	 * 
	 * @throws EmpruntIncorrectException
	 *             si l'ouvrage n'a pas d'emprunt actif ou si la date de rendu
	 *             de l'emprunt n'a pas été définie
	 */
	public void rendre() throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return l'emprunt actif s'il existe
	 */
	public Optional<Emprunt> getEmpruntActif() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return l'ensemble des emprunts révolus
	 */
	public Set<Emprunt> getEmpruntsRevolus() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return le titre de l'ouvrage
	 */
	public String getTitre() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return la référence de l'ouvrage
	 */
	public int getRef() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return <tt>true</tt> ssi l'ouvrage est emprunté (i.e., un emprunt est
	 *         actif)
	 */
	public boolean isEmprunte() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return la durée maximale (optionnelle) de l'emprunt pour cet ouvrage, en
	 *         jours
	 */
	public abstract Optional<Integer> getDureeMax();

	@Override
	public String toString() {
		// TODO
		throw new NotImplementedError();
	}

}
