package models;

import enums.TypeComposant;

import java.util.UUID;

public class Composant {
    private UUID id;
    private String nom;
    private double tva;
    private TypeComposant typeComposant;
    private UUID projetId;
}
