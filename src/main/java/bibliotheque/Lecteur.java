package bibliotheque;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Permet de représenter un lecteur
 */
public class Lecteur {

	private String nom;
	private int nbMaxOuvrage;
	private int dureeMaxEmprunt;

	private Set<Emprunt> actifs;
	private Set<Emprunt> revolus;

	/**
	 * Construit un lecteur, en indiquant son nom
	 * 
	 * @param nom
	 * @param nbMaxOuvrage
	 * @param dureeMaxEmprunt
	 */
	public Lecteur(String nom, int nbMaxOuvrage, int dureeMaxEmprunt) {
		super();
		this.nom = nom;
		this.nbMaxOuvrage = nbMaxOuvrage;
		this.dureeMaxEmprunt = dureeMaxEmprunt;
		actifs = new HashSet<>();
		revolus = new HashSet<>();
	}

	/**
	 * Ajoute un emprunt actif au lecteur
	 * 
	 * @param e
	 */
	public void addEmpruntActif(Emprunt e) throws EmpruntIncorrectException {
		if (nbMaxOuvrage == actifs.size()) {
			throw new EmpruntIncorrectException("Le lecteur " + this + " a déjà atteint sa limite d'emprunt");
		}
		if (e.getLecteur() != this) {
			throw new EmpruntIncorrectException(
					"Le lecteur de l'emprunt " + e + " ne correspond pas au lecteur " + this);
		}
		actifs.add(e);
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
		if (!e.getDateRetour().isPresent()) {
			throw new EmpruntIncorrectException("Veuillez d'abord définir une date de retour pour l'emprunt " + e);
		}
		boolean removed = actifs.remove(e);
		if (!removed) {
			throw new EmpruntIncorrectException(
					"L'emprunt " + e + " n'est pas dans la liste des emprunts actifs de " + this);
		}
		revolus.add(e);
	}

	@Override
	public String toString() {
		return "Lecteur [nom=" + nom + "]";
	}

	public String getNom() {
		return nom;
	}

	public int getNbMaxOuvrage() {
		return nbMaxOuvrage;
	}

	public int getDureeMaxEmprunt() {
		return dureeMaxEmprunt;
	}

	public Set<Emprunt> getActifs() {
		return Collections.unmodifiableSet(actifs);
	}

	public Set<Emprunt> getRevolus() {
		return Collections.unmodifiableSet(revolus);
	}

}
