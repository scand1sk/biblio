package bibliotheque;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Permet de représenter un emprunt, actif ou révolu.
 * 
 */
public class Emprunt {

	/**
	 * Construit un emprunt
	 * 
	 * @param dateEmprunt
	 * @param lecteur
	 * @param ouvrage
	 */
	public Emprunt(LocalDate dateEmprunt, Lecteur lecteur, Ouvrage ouvrage) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Construit un emprunt à la date du jour
	 * 
	 * @param lecteur
	 * @param ouvrage
	 */
	public Emprunt(Lecteur lecteur, Ouvrage ouvrage) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return la date de l'emprunt
	 */
	public LocalDate getDateEmprunt() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return la date de retour, absente si l'emprunt n'est pas révolu
	 */
	public Optional<LocalDate> getDateRetour() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return la date limite de retour (dépend de l'ouvrage)
	 */
	public Optional<LocalDate> getDateLimite() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Définit la date de retour.
	 * 
	 * @param dateRetour
	 * @throws IllegalStateException
	 *             si la date de retour est déjà définie
	 * @throws IllegalArgumentException
	 *             si la date de retour est antérieure à la date de l'emprunt
	 */
	public void setDateRetour(LocalDate dateRetour) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return le lecteur ayant réalisé l'emprunt
	 */
	public Lecteur getLecteur() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return l'ouvrage emprunté
	 */
	public Ouvrage getOuvrage() {
		// TODO
		throw new NotImplementedError();
	}

	@Override
	public String toString() {
		// TODO
		throw new NotImplementedError();
	}

}
