package bibliotheque;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Permet de représenter une bibliothèque modélisée par :
 * <ul>
 * <li>Un ensemble (Set) d'Ouvrage
 * <li>Un ensemble de Lecteur
 * <li>Un ensemble d'emprunts actifs
 * <li>Un ensemble d'emprunts révolus
 * </ul>
 * 
 * Un emprunt est dit révolu si et seulement si il est rendu, même s'il est en
 * retard.
 * 
 */
public class Bibliotheque {

	
	
	/**
	 * Construit une bibliothèque
	 */
	public Bibliotheque() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Génère un emprunt pour le lecteur l et ouvrage o à la date donnée.
	 * L'emprunt est ajouté à l'ensemble des emprunts actifs de la bibliothèque,
	 * du lecteur et de l'ouvrage.
	 * 
	 * @param date
	 * @param l
	 * @param o
	 * 
	 * @return L'Emprunt généré
	 * @throws EmpruntIncorrectException
	 *             Si le lecteur n'est pas inscrit à la bibliothèque, l'ouvrage
	 *             n'est pas référencé, l'ouvrage est déjà emprunté ou le
	 *             lecteur a déjà atteint sa limite d'emprunts.
	 */
	public Emprunt emprunter(LocalDate date, Lecteur l, Ouvrage o) throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Génère un emprunt pour le lecteur l et ouvrage o à la date du jour.
	 * L'emprunt est ajouté à l'ensemble des emprunts actifs de la bibliothèque,
	 * du lecteur et de l'ouvrage.
	 * 
	 * @see #emprunter(LocalDate, Lecteur, Ouvrage)
	 * @param l
	 * @param o
	 * @return L'Emprunt généré
	 * @throws EmpruntIncorrectException
	 *             Si le lecteur n'est pas inscrit à la bibliothèque, l'ouvrage
	 *             n'est pas référencé, l'ouvrage est déjà emprunté ou le
	 *             lecteur a déjà atteint sa limite d'emprunts.
	 */
	public Emprunt emprunter(Lecteur l, Ouvrage o) throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Rend l'emprunt indiqué. L'emprunt est transféré des emprunts actifs aux
	 * emprunts révolus de la bibliothèque, du lecteur et de l'ouvrage.
	 * 
	 * @param e
	 * @throws EmpruntIncorrectException
	 *             Si l'emprunt n'est pas actif dans n'importe lequel des trois
	 *             objets concernés.
	 */
	public void rendre(Emprunt e) throws EmpruntIncorrectException {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Inscrit le lecteur à la bibliothèque. Sans effet si le lecteur est déjà
	 * inscrit.
	 * 
	 * @param l
	 */
	public void inscrire(Lecteur l) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Référence un ouvrage à la bibliothèque.
	 * 
	 * @param o
	 * @throws IllegalStateException
	 *             si l'ouvrage a un emprunt actif.
	 * @throws IllegalArgumentException
	 *             s'il existe un ouvrage référencé portant la même référence
	 */
	public void referencer(Ouvrage ouvrage) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Déréférence un ouvrage à la bibliothèque. Sans effet si l'ouvrage n'est
	 * pas référencé.
	 * 
	 * @param o
	 */
	public void dereferencer(Ouvrage o) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Recherche des lecteurs par nom.
	 * 
	 * @param nom
	 * @return la liste des lecteurs inscrits dont le nom correspond à
	 *         l'argument.
	 */
	public List<Lecteur> rechercherLecteur(String nom) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * Recherche des ouvrages par référence
	 * 
	 * @param ref
	 * @return l'ouvrage référencé correspondant à l'argument (éventuellement
	 *         aucun).
	 */
	public Optional<Ouvrage> rechercherOuvrage(int ref) {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * @return la liste des emprunts en retard
	 */
	public List<Emprunt> retards() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return l'ensemble des lecteurs inscrits
	 */
	public Set<Lecteur> getLecteurs() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return l'ensemble des emprunts actifs
	 */
	public Set<Emprunt> getActifs() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return l'ensemble des emprunts révolus
	 */
	public Set<Emprunt> getRevolus() {
		// TODO
		throw new NotImplementedError();
	}

	/**
	 * 
	 * @return l'ensemble des ouvrages référencés
	 */
	public Set<Ouvrage> getOuvrages() {
		// TODO
		throw new NotImplementedError();
	}

}
