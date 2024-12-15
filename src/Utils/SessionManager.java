package Utils;

import Model.Utilisateurs.Utilisateur;

public class SessionManager {
    private static SessionManager instance;
    private Utilisateur currentUser;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(Utilisateur user) {
        this.currentUser = user;
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }
    public int getId() {
        return currentUser != null ? currentUser.getId(): null;
    }
    public String getNom() {
        return currentUser != null ? currentUser.getNom() : null;
    }

    public String getRole() {
        return currentUser != null ? currentUser.getType() : null;
    }

    public void clearSession() {
        currentUser = null;
    }
}
