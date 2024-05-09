package Todo.TodoApp.repositories;

import Todo.TodoApp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// Repo est une interface qui contient les annotations des méthodes ou bien Override ( hérité ) les
// méthodes prédéfinies dans le JpaRepository ( findById )

// JpaRepository<Todo, Long> Todo est le nom de la table dans entities et Long est le type d'ID
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
