package ma.ens.test;

import ma.ens.entities.Chambre;
import ma.ens.entities.Hotel;
import ma.ens.entities.TypeChambre;
import ma.ens.services.ChambreService;
import ma.ens.services.HotelService;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        HotelService hs = new HotelService();
        ChambreService cs = new ChambreService();

        System.out.println("========== CREATION HOTEL 1 ==========\n");
        Hotel hotel1 = new Hotel("Hotel Mensour", "Marrakech");
        hs.create(hotel1);
        System.out.println("Hotel créé : " + hotel1.getNom());
        System.out.println("Adresse : " + hotel1.getAdress());
        System.out.println("ID : " + hotel1.getId());

        System.out.println("\n========== CREATION CHAMBRES HOTEL 1 ==========\n");
        Chambre c1 = new Chambre(500.0, TypeChambre.SIMPLE, "DISPONIBLE", hotel1);
        cs.create(c1);
        System.out.println("Chambre 1 créée : " + c1.getId());

        Chambre c2 = new Chambre(800.0, TypeChambre.DOUBLE, "OCCUPEE", hotel1);
        cs.create(c2);
        System.out.println("Chambre 2 créée : " + c2.getId());

        Chambre c3 = new Chambre(1200.0, TypeChambre.SUITE, "DISPONIBLE", hotel1);
        cs.create(c3);
        System.out.println("Chambre 3 créée : " + c3.getId());

        System.out.println("\n========== CREATION HOTEL 2 ==========\n");
        Hotel hotel2 = new Hotel("Zephyr", "Agadir");
        hs.create(hotel2);
        System.out.println("Hotel créé : " + hotel2.getNom());
        System.out.println("Adresse : " + hotel2.getAdress());
        System.out.println("ID : " + hotel2.getId());

        System.out.println("\n========== CREATION CHAMBRES HOTEL 2 ==========\n");
        Chambre c4 = new Chambre(800.0, TypeChambre.DOUBLE, "OCCUPEE", hotel2);
        cs.create(c4);
        System.out.println("Chambre 4 créée : " + c4.getId());

        Chambre c5 = new Chambre(1000.0, TypeChambre.DOUBLE, "OCCUPEE", hotel2);
        cs.create(c5);
        System.out.println("Chambre 5 créée : " + c5.getId());

        System.out.println("\n========== FIND BY ID ==========\n");
        Chambre trouvee = cs.findById(c1.getId());
        System.out.println(trouvee);

        System.out.println("\n========== FIND ALL ==========\n");
        for (Chambre c : cs.findAll()) {
            System.out.println(c);
        }

        System.out.println("\n========== FIND BY PRIX ==========\n");
        for (Chambre c : cs.findByPrix(500.0)) {
            System.out.println(c);
        }

        System.out.println("\n========== FIND BY ETAT ==========\n");
        for (Chambre c : cs.findByEtat("DISPONIBLE")) {
            System.out.println(c);
        }

        System.out.println("\n========== UPDATE ==========\n");
        double ancienPrix = trouvee.getPrix();
        trouvee.setPrix(700.0);
        cs.update(trouvee);
        System.out.println("Ancien prix de la chambre " + trouvee.getId() + " : " + ancienPrix);
        System.out.println("Nouveau prix de la chambre " + trouvee.getId() + " : " + trouvee.getPrix());

        System.out.println("\n========== DELETE ==========\n");
        long idASupprimer = c5.getId();
        boolean supprime = cs.delete(c5);
        System.out.println("Suppression de la chambre : " + idASupprimer);
        System.out.println(supprime ? "Chambre supprimée avec succès" : "Échec de la suppression");

        System.out.println("\n========== RECHERCHE PAR ETAT ET PRIX ==========\n");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Saisir l'état recherché (DISPONIBLE / OCCUPEE) : ");
        String etatSaisi = scanner.nextLine().trim().toUpperCase();

        System.out.print("Saisir le prix maximum : ");
        double prixSaisi = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("\nRésultat de la recherche (état = " + etatSaisi
                + ", prix <= " + prixSaisi + ") :");
        for (Chambre c : cs.findByEtatEtPrix(etatSaisi, prixSaisi)) {
            System.out.println(c);
        }

        scanner.close();

        System.out.println("\n========== FIN DU TEST ==========");
    }
}
