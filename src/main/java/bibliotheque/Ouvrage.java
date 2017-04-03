package bibliotheque;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Représente un ouvrage pouvant être emprunté à la bibliothèque
 */
public abstract class Ouvrage {
	private final String titre;
	private final int ref;
	private Optional<Emprunt> empruntActif;
	private Set<Emprunt> empruntsRevolus;

	/**
	 * Construit un ouvrage avec le titre et la référence indiqués
	 * 
	 * @param titre
	 * @param ref
	 */
	public Ouvrage(String titre, int ref) {
		super();
		this.titre = titre;
		this.ref = ref;
		this.empruntActif = Optional.empty();
		this.empruntsRevolus = new HashSet<>();
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
		if (empruntActif.isPresent()) {
			throw new EmpruntIncorrectException("L'ouvrage " + this + " est déjà emprunté : " + empruntActif.get());
		}
		if (emprunt.getOuvrage() != this) {
			throw new EmpruntIncorrectException(
					"L'ouvrage de l'emprunt " + emprunt + " ne correspond pas à l'ouvrage " + this);
		}

		this.empruntActif = Optional.of(emprunt);
	}

	/**
	 * Permet de basculer l'emprunt actif de l'ouvrage dans les emprunts révolus
	 * 
	 * @throws EmpruntIncorrectException
	 *             si l'ouvrage n'a pas d'emprunt actif ou si la date de rendu
	 *             de l'emprunt n'a pas été définie
	 */
	public void rendre() throws EmpruntIncorrectException {
		empruntActif.orElseThrow(() -> new EmpruntIncorrectException("L'ouvrage " + this + " n'est pas emprunté"));
		Emprunt e = empruntActif.get();
		if (!e.getDateRetour().isPresent()) {
			throw new EmpruntIncorrectException("Veuillez d'abord définir la date de rendu de l'emprunt " + e);
		}
		empruntsRevolus.add(e);
		empruntActif = Optional.empty();
	}

	/**
	 * @return l'emprunt actif s'il existe
	 */
	public Optional<Emprunt> getEmpruntActif() {
		return empruntActif;
	}

	/**
	 * 
	 * @return l'ensemble des emprunts révolus
	 */
	public Set<Emprunt> getEmpruntsRevolus() {
		return Collections.unmodifiableSet(empruntsRevolus);
	}

	/**
	 * @return le titre de l'ouvrage
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @return la référence de l'ouvrage
	 */
	public int getRef() {
		return ref;
	}

	/**
	 * @return <tt>true</tt> ssi l'ouvrage est emprunté (i.e., un emprunt est
	 *         actif)
	 */
	public boolean isEmprunte() {
		return empruntActif.isPresent();
	}

	/**
	 * @return la durée maximale (optionnelle) de l'emprunt pour cet ouvrage, en
	 *         jours
	 */
	public abstract Optional<Integer> getDureeMax();

	@Override
	public String toString() {
		return "Ouvrage [titre=" + titre + ", ref=" + ref + ", isEmprunte()=" + isEmprunte() + ", getDureeMax()="
				+ getDureeMax() + "]";
	}

}
