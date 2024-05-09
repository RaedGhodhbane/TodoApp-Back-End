package Todo.TodoApp.controllers;

import Todo.TodoApp.entities.Todo;
import Todo.TodoApp.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    // @Autowired permet d'effectuer l'injection de dépendances automatique qui consiste à fournir objet
    // les dépendances dont il a besoin pour accomplir ses tâches ( ex : injection par constructeur :
    // c'est les dépendances qui sont fournies via les paramètres du constructeur lors de la création de l'objet),
    // injection par setter, injection par champ..)

    private TodoRepository todoRepository;

    @GetMapping("/list")
    // @GetMapping est une annotation utilisée pour mapper les requêtes HTTP ( associer des requêtes
    // HTTP reçues par un serveur ( à des méthodes de traitement spécifiques dans une application web
    // de type Get à des méthodes de contrôleur spécifique dans une application web
    // Le serveur web doit être configuré pour écouter sur un port spécifique (par exemple, le port 80)
    // Cette configuration est généralement effectuée dans le fichier de configuration du serveur, tel que
    // le fichier application.properties pour Spring Boot.

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoRepository.save(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Todo not found with id" + id));
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Todo not found with id " + id));

        // Mettre à jour les champs du todo existant avec les valeurs de todoDetails
        todo.setName(todoDetails.getName());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        todo.setLastUpdated(new Date());

        // Sauvegarder les modifications dans la base de données
        Todo updatedTodo = todoRepository.save(todo);

        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Todo not found with id " + id));
        todoRepository.delete(todo);
        return ResponseEntity.ok().build();
    }
}
