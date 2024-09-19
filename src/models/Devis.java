package models;

import java.util.Date;
import java.util.UUID;

public class Devis {
    private UUID id;
    private Date dateEmission;
    private Date dateValidee;
    private boolean accepte;
    private UUID projetId;


    public Devis(UUID id, Date dateEmission, Date dateValidee, boolean accepte, UUID projetId) {
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

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateValidee() {
        return dateValidee;
    }

    public void setDateValidee(Date dateValidee) {
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
