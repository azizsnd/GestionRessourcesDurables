package Model.GestionDeRisque;


public sealed class RisqueEnviromental permits IncidentEnvironnemental {
    private int id;
    private String nom;
    private String description;
    private double probabilite;
    private double impact;
    private int priorite;

    public RisqueEnviromental(String nom, String description, double probabilite, double impact, int priorite) {
        this.nom = nom;
        this.description = description;
        this.probabilite = probabilite;
        this.impact = impact;
        this.priorite = priorite;
    }
    public RisqueEnviromental(int id, String nom, String description, double probabilite, double impact, int priorite) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.probabilite = probabilite;
        this.impact = impact;
        this.priorite = priorite;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProbabilite() {
        return probabilite;
    }

    public void setProbabilite(double probabilite) {
        this.probabilite = probabilite;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public double evaluerGravite() {
        return probabilite * impact;
    }

    @Override
    public String toString() {
        return "RisqueEnviromental{" + "nom=" + nom + ", description=" + description + ", probabilite=" + probabilite + ", impact=" + impact + ", priorite=" + priorite + '}';
    }

}
