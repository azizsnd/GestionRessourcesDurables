package Model.Utilisateurs;

public class Auditeur extends Utilisateur {
    
    public Auditeur(String nom, int idAuditeur, String motDePasse) {
        super(nom, idAuditeur, motDePasse);
    }

    public void effectuerAudit() {
        System.out.println("L'auditeur " + nom + " effectue un audit.");
    }
    
}
