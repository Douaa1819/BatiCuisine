package models;

import enums.TypeComposant;
import java.util.UUID;

public class MainOeuvre extends Composant {
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;


    public MainOeuvre(UUID id, String nom, double tva, TypeComposant typeComposant, UUID projetId,
                      double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super(id, nom, tva, typeComposant, projetId);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }


    @Override
    public String toString() {
        return "MainOeuvre{" +
                "tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                "} " + super.toString();
    }
}
