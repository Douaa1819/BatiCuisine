package models;

import java.util.Date;
import java.util.UUID;

public class Devis {
    private UUID id;
    private Date dateEmission;
    private Date dateValidee;
    private boolean accepte;
    private UUID projetId;
}
