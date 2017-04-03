package bibliotheque;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Permet de représenter un emprunt, actif ou révolu.
 * 
 */
public class Emprunt {
	private final LocalDate dateEmprunt;
	private Optional<LocalDate> dateRetour;

	private final Lecteur lecteur;
	private final Ouvrage ouvrage;

	/**
	 * Construit un emprunt
	 * 
	 * @param dateEmprunt
	 * @param lecteur
	 * @param ouvrage
	 */
	public Emprunt(LocalDate dateEmprunt, Lecteur lecteur, Ouvrage ouvrage) {
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = Optional.empty();
		this.lecteur = lecteur;
		this.ouvrage = ouvrage;
	}

	/**
	 * Construit un emprunt à la date du jour
	 * 
	 * @param lecteur
	 * @param ouvrage
	 */
	public Emprunt(Lecteur lecteur, Ouvrage ouvrage) {
		this(LocalDate.now(), lecteur, ouvrage);
	}

	/**
	 * 
	 * @return la date de l'emprunt
	 */
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	/**
	 * 
	 * @return la date de retour, optionnalle si l'emprunt n'est pas révolu
	 */
	public Optional<LocalDate> getDateRetour() {
		return dateRetour;
	}

	/**
	 * 
	 * @return la date limite de retour (dépend de l'ouvrage)
	 */
	public Optional<LocalDate> getDateLimite() {
		return ouvrage.getDureeMax().map(d -> dateEmprunt.plusDays(d));
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
		if (this.dateRetour.isPresent()) {
			throw new IllegalStateException("La date de retour est déjà définie");
		}
		if (this.dateEmprunt.compareTo(dateRetour) > 0) {
			throw new IllegalArgumentException("La date de retour ne peut être antérieure à la date de l'emprunt");
		}
		this.dateRetour = Optional.of(dateRetour);
	}

	/**
	 * @return le lecteur ayant réalisé l'emprunt
	 */
	public Lecteur getLecteur() {
		return lecteur;
	}

	/**
	 * @return l'ouvrage emprunté
	 */
	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	@Override
	public String toString() {
		return "Emprunt [dateEmprunt=" + dateEmprunt + ", dateRetour=" + dateRetour + ", lecteur=" + lecteur
				+ ", ouvrage=" + ouvrage + "]";
	}

}
