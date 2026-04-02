package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		// 1. Vérification de l'identité
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
			return;
		}

		// 2. Recherche du produit
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		String[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);

		if (vendeurs == null || vendeurs.length == 0) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
			return;
		}

		// 3. Choix du commerçant
		System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
		for (int i = 0; i < vendeurs.length; i++) {
			System.out.println((i + 1) + " - " + vendeurs[i]);
		}
		
		int choixVendeur = -1;
		do {
			choixVendeur = Clavier.entrerEntier(""); // On récupère le choix de l'utilisateur
		} while (choixVendeur < 1 || choixVendeur > vendeurs.length);
		
		String vendeurChoisi = vendeurs[choixVendeur - 1];

		// 4. Déplacement à l'étal et transaction
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeurChoisi);
		System.out.println("Bonjour " + nomAcheteur);
		
		int quantite = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		int quantiteAchetee = controlAcheterProduit.acheterProduit(vendeurChoisi, quantite);

		// 5. Affichage du résultat selon le stock disponible
		if (quantiteAchetee == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement il n'y en a plus !");
		} else if (quantiteAchetee < quantite) {
			System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement " + vendeurChoisi + " n'en a plus que " + quantiteAchetee + ".");
			System.out.println(nomAcheteur + " achète tout le stock de " + vendeurChoisi + ".");
		} else {
			System.out.println(nomAcheteur + " achète " + quantite + " " + produit + " à " + vendeurChoisi + ".");
		}
	}
}