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
}
