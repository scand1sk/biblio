package bibliotheque;

/**
 * Un {@link EtudiantLicence} est un Lecteur particulier :
 * <ul>
 * <li>dont la duree d’emprunt maximale est 14 jours
 * <li>dont le nombre d’emprunts simultanés autorisés est de 8.
 * </ul>
 *
 * 
 */
public class EtudiantLicence extends Lecteur {

	/**
	 * Construit un EtudiantLicence
	 * 
	 * @param nom
	 */
	public EtudiantLicence(String nom) {
		super(nom, 8, 14);
	}
}
