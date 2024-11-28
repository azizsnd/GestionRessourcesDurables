    package Model.entiteDurable;

    import java.util.Date;

    public sealed abstract class EntiteDurable permits Dechet, EmpreinteCarbone, Ressource {
        private int id;
        private String nom;
        private String description;
        private Date dateCreation;
        private ObjectifDurabilite objectif; 

     // Définition des interfaces fonctionnelles pour les comportements
        private VerifierObjectif verifierObjectif = (objectif) -> objectif != null && objectif.objectifEstAtteint();
        private CalculerProgresRestant calculerProgresRestant = (objectif) -> objectif != null ? objectif.getProgresRestant() : 0;

        public EntiteDurable(int id,String nom,String description,Date dateCreation,ObjectifDurabilite objectif) throws ObjectifInvalideException{
            this.id = id;
            this.nom = nom;
            this.description = description;
            this.dateCreation = dateCreation;        
            if (objectif == null) {
             throw new ObjectifInvalideException("L'objectif ne peut pas être null.");
            }   
            this.objectif = objectif;
        }
        public EntiteDurable(String nom,String description,Date dateCreation,ObjectifDurabilite objectif) throws ObjectifInvalideException{
            this.nom = nom;
            this.description = description;
            this.dateCreation = dateCreation;        
            if (objectif == null) {
             throw new ObjectifInvalideException("L'objectif ne peut pas être null.");
            }   
            this.objectif = objectif;
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

        public Date getDateCreation() {
            return dateCreation;
        }

        public ObjectifDurabilite getObjectif() {
            return objectif;
        }

        public void setObjectif(ObjectifDurabilite objectif) throws ObjectifInvalideException {
            if (objectif == null) {
                throw new ObjectifInvalideException("L'objectif ne peut pas être null.");
            }
            this.objectif = objectif;
        }

        public void setDateCreation(Date dateCreation) {
            this.dateCreation = dateCreation;
        }
        // Utilisation de l'interface fonctionnelle pour vérifier si l'objectif est atteint
        public boolean verifierObjectifAtteint() {
            return verifierObjectif.verifier(objectif);
        }

        // Utilisation de l'interface fonctionnelle pour calculer le progrès restant
        public double getProgresRestant() throws ObjectifInvalideException{
            if (objectif == null) {
            throw new ObjectifInvalideException("L'objectif est invalide.");
        }
            return calculerProgresRestant.calculer(objectif);
        }

        @Override
        public String toString() {
            return "EntiteDurable{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", dateCreation=" + dateCreation + ", objectif=" + objectif + '}';
        }

    }
