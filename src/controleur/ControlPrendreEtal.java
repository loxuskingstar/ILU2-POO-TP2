package controleur;

import frontiere.Clavier;
import villagegaulois.Village;
import personnages.Gaulois;

public class ControlPrendreEtal {
	private Village village;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlPrendreEtal(ControlVerifierIdentite controlVerifierIdentite,
			Village village) {
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.village = village;
	}

	public boolean resteEtals() {
		// Retourne si il reste un etal ou pas
		return (village.rechercherEtalVide());
	}

	public int prendreEtal(String nomVendeur, String produit, int nbProduit) {
		Gaulois gaulois = village.trouverHabitant(nomVendeur);
		int numeroEtal = village.installerVendeur(gaulois, produit, nbProduit);
		return numeroEtal;
		
	}

	
	public boolean verifierIdentite(String nomVendeur) {
		// Vérifie l'identité en utilisant la fonction du contrôleur
		return controlVerifierIdentite.verifierIdentite(nomVendeur);
	}
}
