package at.refugeescode.mp9mypersonaltodolistnosqlmongodb.persistence.repository;

import at.refugeescode.mp9mypersonaltodolistnosqlmongodb.persistence.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
