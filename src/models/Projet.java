package models;

import enums.EtatProjet;

import java.util.UUID;

public class Projet {
    private UUID id;
    private String nomProjet;
    private double surface;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProjet etatProjet;
    private double tva;
    private UUID clientId;


public Projet(UUID id, String nomProjet, double surface, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, double tva, UUID clientId) {
    this.id = id;
    this.nomProjet = nomProjet;
    this.surface = surface;
    this.margeBeneficiaire = margeBeneficiaire;
    this.coutTotal = coutTotal;
    this.etatProjet = etatProjet;
    this.tva = tva;
    this.clientId = clientId;
}

public UUID getId() {
    return id;
}

public String getNomProjet() {
    return nomProjet;
}

public void setNomProjet(String nomProjet) {
    this.nomProjet = nomProjet;
}

public double getSurface() {
    return surface;
}

public void setSurface(double surface) {
    this.surface = surface;
}

public double getMargeBeneficiaire() {
    return margeBeneficiaire;
}

public void setMargeBeneficiaire(double margeBeneficiaire) {
    this.margeBeneficiaire = margeBeneficiaire;
}

public double getCoutTotal() {
    return coutTotal;
}

public void setCoutTotal(double coutTotal) {
    this.coutTotal = coutTotal;
}

public EtatProjet getEtatProjet() {
    return etatProjet;
}

public void setEtatProjet(EtatProjet etatProjet) {
    this.etatProjet = etatProjet;
}

public double getTva() {
    return tva;
}

public void setTva(double tva) {
    this.tva = tva;
}

public UUID getClientId() {
    return clientId;
}

public void setClientId(UUID clientId) {
    this.clientId = clientId;
}

@Override
public String toString() {
    return "Projet{" +
            "id=" + id +
            ", nomProjet='" + nomProjet + '\'' +
            ", surface=" + surface +
            ", margeBeneficiaire=" + margeBeneficiaire +
            ", coutTotal=" + coutTotal +
            ", etatProjet=" + etatProjet +
            ", tva=" + tva +
            ", clientId=" + clientId +
            '}';
}
}
