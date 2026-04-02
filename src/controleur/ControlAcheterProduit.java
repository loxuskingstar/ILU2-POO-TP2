package controleur;

import villagegaulois.Village;
import personnages.Gaulois;
import villagegaulois.Etal;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	// Vérifie si le client est un habitant
	public boolean verifierIdentite(String nomClient) {
		return controlVerifierIdentite.verifierIdentite(nomClient);
	}

	// Retourne un tableau avec les noms des vendeurs d'un produit spécifique
	public String[] rechercherVendeursProduit(String produit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		if (vendeurs == null) {
			return null;
		}
		
		String[] nomsVendeurs = new String[vendeurs.length];
		for (int i = 0; i < vendeurs.length; i++) {
			nomsVendeurs[i] = vendeurs[i].getNom();
		}
		return nomsVendeurs;
	}

	// Réalise la transaction et retourne la quantité réellement achetée
	public int acheterProduit(String nomVendeur, int quantiteDemande) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		return etal.acheterProduit(quantiteDemande);
	}
}