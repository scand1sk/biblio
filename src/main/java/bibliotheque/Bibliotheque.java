package bibliotheque;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
	private final Set<Lecteur> lecteurs;
	private final Set<Emprunt> actifs;
	private final Set<Emprunt> revolus;
	private final Set<Ouvrage> ouvrages;

	/**
	 * Construit une bibliothèque
	 */
	public Bibliotheque() {
		lecteurs = new HashSet<>();
		actifs = new HashSet<>();
		revolus = new HashSet<>();
		ouvrages = new HashSet<>();
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
		if (!lecteurs.contains(l)) {
			throw new EmpruntIncorrectException("Le lecteur " + l + " n'est pas inscrit");
		}
		if (!ouvrages.contains(o)) {
			throw new EmpruntIncorrectException("L'ouvrage " + o + " n'est pas référencé");
		}

		Emprunt e = new Emprunt(date, l, o);

		o.setEmpruntActif(e);
		l.addEmpruntActif(e);
		actifs.add(e);

		return e;
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
		return emprunter(LocalDate.now(), l, o);
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
		e.setDateRetour(LocalDate.now());
		e.getLecteur().rendre(e);
		e.getOuvrage().rendre();
		actifs.remove(e);
		revolus.add(e);
	}

	/**
	 * Inscrit le lecteur à la bibliothèque. Sans effet si le lecteur est déjà
	 * inscrit.
	 * 
	 * @param l
	 */
	public void inscrire(Lecteur l) {
		lecteurs.add(l);
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
		if (ouvrage.isEmprunte()) {
			throw new IllegalStateException(
					"L'ouvrage " + ouvrage + " est emprunté : " + ouvrage.getEmpruntActif().get());
		}
		if (ouvrages.stream().anyMatch(o -> o.getRef() == ouvrage.getRef())) {
			throw new IllegalArgumentException("L'ouvrage " + ouvrage + " a une référence déjà existante");
		}
		ouvrages.add(ouvrage);
	}

	/**
	 * Déréférence un ouvrage à la bibliothèque. Sans effet si l'ouvrage n'est
	 * pas référencé.
	 * 
	 * @param o
	 */
	public void dereferencer(Ouvrage o) {
		if (o.isEmprunte()) {
			throw new IllegalStateException("L'ouvrage " + o + " est emprunté : " + o.getEmpruntActif().get());
		}
		ouvrages.remove(o);

	}

	/**
	 * Recherche des lecteurs par nom.
	 * 
	 * @param nom
	 * @return la liste des lecteurs inscrits dont le nom correspond à
	 *         l'argument.
	 */
	public List<Lecteur> rechercherLecteur(String nom) {
		return lecteurs.stream().filter(l -> l.getNom().equals(nom)).collect(Collectors.toList());
	}

	/**
	 * Recherche des ouvrages par référence
	 * 
	 * @param ref
	 * @return l'ouvrage référencé correspondant à l'argument (éventuellement
	 *         aucun).
	 */
	public Optional<Ouvrage> rechercherOuvrage(int ref) {
		return ouvrages.stream().filter(o -> o.getRef() == ref).findAny();
	}

	/**
	 * @return la liste des emprunts en retard
	 */
	public List<Emprunt> retards() {
		LocalDate now = LocalDate.now();
		return actifs.stream().filter(e -> e.getDateLimite().isPresent() && e.getDateLimite().get().compareTo(now) < 0)
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * @return l'ensemble des lecteurs inscrits
	 */
	public Set<Lecteur> getLecteurs() {
		return Collections.unmodifiableSet(lecteurs);
	}

	/**
	 * 
	 * @return l'ensemble des emprunts actifs
	 */
	public Set<Emprunt> getActifs() {
		return Collections.unmodifiableSet(actifs);
	}

	/**
	 * 
	 * @return l'ensemble des emprunts révolus
	 */
	public Set<Emprunt> getRevolus() {
		return Collections.unmodifiableSet(revolus);
	}

	/**
	 * 
	 * @return l'ensemble des ouvrages référencés
	 */
	public Set<Ouvrage> getOuvrages() {
		return Collections.unmodifiableSet(ouvrages);
	}

}
