package Model.Utilisateurs;

public sealed class Utilisateur permits Administrateur, Auditeur{
    protected String nom;
    protected int idUtilisateur;
    protected String motDePasse;

    public Utilisateur(String nom, int idUtilisateur, String motDePasse) {
        this.nom = nom;
        this.idUtilisateur = idUtilisateur;
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean login(String motDePasse) {
        return this.motDePasse.equals(motDePasse);
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", idUtilisateur=" + idUtilisateur + ", motDePasse=" + motDePasse + '}';
    }
    
}
