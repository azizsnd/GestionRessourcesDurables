package Model.Utilisateurs;

public sealed class Utilisateur permits Administrateur, Auditeur{
    private int id;
    private String nom;
    private String motDePasse;

    public Utilisateur(String nom,String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(int id, String nom,String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.motDePasse = motDePasse;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        return "Utilisateur{" + "nom=" + nom + ", id=" + id +
                ", motDePasse=" + motDePasse + '}';
    }
    
}
