package models;

import enums.TypeComposant;
import java.util.UUID;

public class Composant {
    private UUID id;
    private String nom;
    private double tva;
    private TypeComposant typeComposant;
    private UUID projetId;


    public Composant(UUID id, String nom, double tva, TypeComposant typeComposant, UUID projetId) {
        this.id = id;
        this.nom = nom;
        this.tva = tva;
        this.typeComposant = typeComposant;
        this.projetId = projetId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public UUID getProjetId() {

        return projetId;
    }

    public void setProjetId(UUID projetId) {
        this.projetId = projetId;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", tva=" + tva +
                ", typeComposant=" + typeComposant +
                ", projetId=" + projetId +
                '}';
    }
}
