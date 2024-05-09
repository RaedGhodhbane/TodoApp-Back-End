package Todo.TodoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
// Créer la classe qui est la table dans le MySQL Workbench
@Entity
// @Entity : Utilisé pour créer la table dans la base de données
@Data
// @Data : Utilisé pour créer un constructeur par défaut et ajouter les Getters et les Setters
@Table(name = "todo")
// @Table : Utilisé pour ajouter les métadatas pour la table : pour créer un schema dans la base de données
// Dans la Schema il y'a les colonnes et les propriétés ( Exemple : maxlength...)
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    // @Id : Veut dire que l'id est un clé primaire de la table
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue pour incrémenter l'id de 1 à chaque fois dans la table : Pour indiquer la méthode pour générer l'ID
    private Long id;
    @Column(name = "username")
    // Ajouter une colonne avec le nom username dans la table Todo
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "completed")
    private boolean completed;
    @Column(name = "datecreated")
    private Date dateCreated;
    @Column(name = "lastupdated")
    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date(new Date().getTime());
        lastUpdated = new Date(new Date().getTime());
    }
}
