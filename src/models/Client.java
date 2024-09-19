package models;

import java.util.UUID;

public class Client {
    private UUID id;
    private String nom;
    private String adress;
    private String phone;
    private boolean estProfessionnel;


    public Client(UUID id, String nom, String adress, String phone, boolean estProfessionnel) {
        this.id = id;
        this.nom = nom;
        this.adress = adress;
        this.phone = phone;
        this.estProfessionnel = estProfessionnel;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public  void  setPhone(String phone) {
        this.phone= phone;
    }

    public boolean isEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(boolean estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }

}
