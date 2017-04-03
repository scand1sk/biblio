package bibliotheque;

import java.util.Set;

/**
 * Permet de représenter un lecteur
 */
public class Lecteur {
	/**
	 * Construit un lecteur, en indiquant son nom
	 * 
	 * @param nom
	 * @param nbMaxOuvrage
	 * @param dureeMaxEmprunt
	 */
	public Lecteur(String nom, int nbMaxOuvrage, int dureeMaxEmprunt) {
		super();
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Ajoute un emprunt actif au lecteur
	 * 
	 * @param e
	 */
	public void addEmpruntActif(Emprunt e) throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Bascule un emprunt actif du lecteur vers les emprunts révolus
	 * 
	 * @param e
	 * @throws EmpruntIncorrectException
	 *             si l'emprunt n'est pas dans la liste des emprunts actifs ou
	 *             si l'emprunt n'a pas de date de retour définie
	 */
	public void rendre(Emprunt e) throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	@Override
	public String toString() {
		// TODO
		throw new NotImplementedError();
	}

	public String getNom() {
		// TODO
		throw new NotImplementedError();
	}

	public int getNbMaxOuvrage() {
		// TODO
		throw new NotImplementedError();
	}

	public int getDureeMaxEmprunt() {
		// TODO
		throw new NotImplementedError();
	}

	public Set<Emprunt> getActifs() {
		// TODO
		throw new NotImplementedError();
	}

	public Set<Emprunt> getRevolus() {
		// TODO
		throw new NotImplementedError();
	}

}
