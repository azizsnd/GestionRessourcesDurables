package Model.Utilisateurs;

public final class Auditeur extends Utilisateur {
    
    public Auditeur(String nom,String motDePasse) {
        super(nom, motDePasse);
    }

    public Auditeur(int id, String nom, String motDePasse) {
        super(id, nom, motDePasse);
    }

    public void effectuerAudit() {
        System.out.println("L'auditeur " + getNom() + " effectue un audit.");
    }
    
}
