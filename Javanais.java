import java.util.*;

public class Javanais {
	
	/* 
	 * Ici la lettre 'y' sera seulement considéré comme une voyelle
	 * car dans l'exemple le mot "moyen" donne "mavoyen" et pas "mavoyaven"
	 * ce qui implique que 'y' est seulement considéré comme une voyelle 
	 * si je respecte les consignes.
	 */
	 
	/* String contenant toutes les voyelles */
	static String voyelles = "aeiouyAEIOUY"; 
	
	/* String contenant toutes les consonnes */
	static String consonnes = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ"; 
	
	/* Vérifie si c est une voyelle <=> c est dans la chaine <voyelles> */
	public static boolean estUneVoyelle(char c) {
		return (voyelles.indexOf(c) != -1);
	}
	
	/* Vérifie si c est une consonne <=> c est dans la chaine <consonnes> */
	public static boolean estUneConsonne(char c) {
		return (consonnes.indexOf(c) != -1);
	}
	
	/* Vérifie si la chaine de caractère mot est valide ou non */
	public static boolean motValide(String mot) {
		boolean motValide = true;
		
		for(int i = 0; i < mot.length(); i++) {
			if(!estUneVoyelle(mot.charAt(i))) {
				if(!estUneConsonne(mot.charAt(i))) {
					motValide = false;
					break;
				}
			}
		}
		
		return motValide;
	}
	
	/* 
	 * Cherche l'indice de la n-ème occurence du caractère c
	 * la chaîne de caractère mot.
	 * Exemple : indiceCaractere("bonjour",'o',2) renvoie 4
	 * Comme la 2e occurence de 'o' dans "bonjour" se trouve à l'indice 4
	 */
	 public static int indiceCaractere(String mot, char c, int n) {
		int occurence = 1;
		int indice = -1;
		
		if(n == 1) {
			return mot.indexOf(c);
		}
		
		for(int i = 0; i < mot.length(); i++) {
			if(mot.charAt(i) == c) {
				if(occurence != n) {
					occurence++;
					continue;
				}
				else {
					indice = i;
					break;
				}
			}
		}
		
		return indice;
	}
	
	/* Cherche le nombre d'occurences de la lettre c dans la liste */
	public static int nombreOccurence(ArrayList<Character> liste, char c) { 
		int nb = 0;
		for(Character caractere : liste) {
			if(caractere.equals(c)) {
				nb++;
			}
		}
		return nb;
	}
	
	/* 
	 * Va transformer la chaine de caractère mot en une nouvelle
	 * chaine javanaise avec la syllabe pris en paramètre
	 */
	public static String insereSyllabe(String mot, String syllabe) {
		String nouveauMot = mot;
		String partie1mot;
		String partie2mot;
		int indiceVoyelleNouveauMot;
		ArrayList<Character> liste_voyelles = new ArrayList<Character>();
		
		if(!motValide(mot)) {
			System.err.println("Erreur : Le mot écrit ne doit contenir que des lettres");
			System.exit(0);
		}
		
		if(voyelles.indexOf(mot.charAt(0)) != -1) { //si la 1ere lettre est une voyelle
			liste_voyelles.add(mot.charAt(0));
			nouveauMot = syllabe + mot; //j'ajoute la syllabe avant le mot
		}
		
		for(int i = 0; i < mot.length()-1; i++) { //parcours du mot
			if(estUneConsonne(mot.charAt(i))) { //si le caractère en cours est une consonne
				if(estUneVoyelle(mot.charAt(i+1))) { //si le suivant est une voyelle
					if(liste_voyelles.contains(mot.charAt(i+1))) { //si ce suivant a déja été vu auparavant
						int nbOccurence = nombreOccurence(liste_voyelles,mot.charAt(i+1)); //nb de fois vu
						indiceVoyelleNouveauMot = indiceCaractere(nouveauMot,mot.charAt(i+1),nbOccurence+1);
					}
					else {
						indiceVoyelleNouveauMot = nouveauMot.indexOf(mot.charAt(i+1));
					}
					liste_voyelles.add(mot.charAt(i+1)); //j'ajoute cette voyelle dans la liste
					partie1mot = nouveauMot.substring(0,indiceVoyelleNouveauMot);
					partie2mot = nouveauMot.substring(indiceVoyelleNouveauMot,nouveauMot.length());
					nouveauMot = partie1mot + syllabe + partie2mot;
				}
			}
		}

		return nouveauMot;
		
	}
	
	public static void main(String[] args) {
		String mot;
		String syl = "av";
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrez un mot puis appuyer sur 'Entrée' : ");
		mot = sc.next();
		System.out.println("Nouveau mot javanais avec la syllabe '"+syl+"' : "+insereSyllabe(mot,syl));
	}
	
	
	
}
