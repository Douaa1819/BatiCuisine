package models;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Devis {
    private UUID id;
    private  LocalDate dateEmission;
    private  LocalDate dateValidee;
    private boolean accepte;
    private UUID projetId;


    public Devis(UUID id, LocalDate dateEmission, LocalDate dateValidee, boolean accepte, UUID projetId) {
        this.id = id;
        this.dateEmission = dateEmission;
        this.dateValidee = dateValidee;
        this.accepte = accepte;
        this.projetId = projetId;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValidee() {
        return dateValidee;
    }

    public void setDateValidee(LocalDate dateValidee) {
        this.dateValidee = dateValidee;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public UUID getProjetId() {
        return projetId;
    }

    public void setProjetId(UUID projetId) {
        this.projetId = projetId;
    }


    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", dateEmission=" + dateEmission +
                ", dateValidee=" + dateValidee +
                ", accepte=" + accepte +
                ", projetId=" + projetId +
                '}';
    }
}
