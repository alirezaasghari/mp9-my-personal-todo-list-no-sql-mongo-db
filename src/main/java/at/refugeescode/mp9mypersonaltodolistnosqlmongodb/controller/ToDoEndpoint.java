package at.refugeescode.mp9mypersonaltodolistnosqlmongodb.controller;

import at.refugeescode.mp9mypersonaltodolistnosqlmongodb.persistence.model.ToDo;
import at.refugeescode.mp9mypersonaltodolistnosqlmongodb.persistence.repository.ToDoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoEndpoint {

    private ToDoRepository toDoRepository;

    public ToDoEndpoint(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping
    List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    ToDo findOneById(@PathVariable String id) {
        return toDoRepository.findById(id)
                .orElse(null);
    }

    @PutMapping("/{id}/done")
    ToDo done(@PathVariable String id) {
        Optional<ToDo> result = toDoRepository.findById(id);
        if (result.isPresent()) {
            ToDo isDone = markAsDone(result);
            return toDoRepository.save(isDone);
        }
        return null;
    }

    private ToDo markAsDone(Optional<ToDo> result) {
        ToDo isDone = result.get();
        isDone.setDone(true);
        return isDone;
    }

    @PostMapping
    ToDo addNew(@RequestBody ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @DeleteMapping("/{id}")
    String removeOneById(@PathVariable String id) {
        Optional<ToDo> result = toDoRepository.findById(id);
        if (result.isPresent()) {
            String name = getResult(result);
            toDoRepository.deleteById(id);

            return printResult(name);
        }
        return "not available";
    }

    private String printResult(String name) {
        return "The item with Task: '" + name + "' has been deleted.";
    }

    private String getResult(Optional<ToDo> result) {
        ToDo toDo = result.get();
        return toDo.getName();
    }
}
