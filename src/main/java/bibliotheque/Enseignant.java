package bibliotheque;

/**
 * Un Enseignant est un Lecteur particulier :
 * <ul>
 * <li>dont la duree d’emprunt maximale est 60 jours
 * <li>dont le nombre d’emprunts simultanés autorisés est de 32.
 * </ul>
 *
 * 
 */
public class Enseignant extends Lecteur {

	/**
	 * Construit un Enseignant
	 * 
	 * @param nom
	 */
	public Enseignant(String nom) {
		super(nom, 0, 0);
		// TODO
		throw new NotImplementedError();
	}
}
