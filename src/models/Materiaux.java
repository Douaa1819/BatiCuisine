package models;

import enums.TypeComposant;
import java.util.UUID;

public class Materiaux extends Composant {

    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    public Materiaux(UUID id, String nom, double tva, TypeComposant typeComposant, UUID projetId,
                     double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        super(id, nom, tva, typeComposant, projetId);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }




    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }


    @Override
    public String toString() {
        return "Materiaux{" +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                "} " + super.toString();
    }
}
