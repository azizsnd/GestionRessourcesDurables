# Sustainable Resource Management System

## 📚 Overview
This project is a **Sustainable Resource Management System** developed in Java. The application provides tools for managing sustainable resources, energy, waste, and carbon footprint by adhering to ISO regulations. It integrates a comprehensive database and features such as audits, action plans, and user management.

## 🛠️ Features
- **Resource Monitoring**: Track and optimize the usage of sustainable resources.
- **Waste and Energy Management**: Monitor waste production, recycling, and energy consumption.
- **ISO Regulation Management**: Create, update, and manage ISO norms.
- **Environmental Audits**: Conduct audits to ensure compliance with sustainability goals.
- **Action Plans**: Generate corrective action plans for sustainable development.
- **User Roles**: Role-based access for administrators and auditors.

## 🏗️ Architecture
The project follows an **MVC (Model-View-Controller)** architecture to ensure clean separation of concerns. Key components include:

1. **Model**: Represents the data layer, including ISO norms, regulations, and sustainability objectives.
2. **View**: Contains JavaFX-based interfaces for user interaction.
3. **Controller**: Handles logic for database interaction and user actions.
4. **Database**: MySQL-based backend for storing data.
5. **Utils**: Helper utilities for alerts, session management, and view loading.
6. **Services**: Handles data processing and business logic.

## 📖 Prerequisites
- **Java 8+**
- **MySQL Server**
- **JavaFX**
- **Maven** (optional, for dependency management)

## ⚙️ Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/azizsnd/GestionRessourcesDurables.git
   cd GestionRessourcesDurables
   ```
2. Configure the MySQL database:
   - Create a database named `GestionRessourcesDurables`.
   - Update database connection details in `DatabaseConnection.java`.
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/GestionRessourcesDurables";
   private static final String USER = "root";
   private static final String PASSWORD = "yourpassword";
   ```
   - Import the provided SQL schema .

3. Compile and run the project:
   ```bash
   javac Main.java
   java Main
   ```

4. Alternatively, import the project into an IDE like IntelliJ IDEA or NetBeans for easier management.

## 🚀 Usage
- Log in as an administrator or auditor.
- Navigate through ISO management, resource tracking, and audit features.
- Generate corrective action plans to meet sustainability objectives.

## 📽️ Demo
Check out the video demonstration of the project on *LinkedIn* : 


## 👥 Collaborators
- **Mohamed Aziz Sandid** - Developer
- **Jasser Gorsia** - Developer

## 📂 Directory Structure
```
src/
|-- Controller/
|-- DataBase/
|-- Model/
|-- Services/
|-- Utils/
|-- View/
    |-- Components/
```

---
Feel free to contribute or report issues to improve the project!
