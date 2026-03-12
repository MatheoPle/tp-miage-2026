package comm.acme.todolist.domain;

import com.acme.todolist.domain.TodoItem;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoItemTest {
    @Test
    void finalContent_WhenLate(){
        TodoItem todoItem = new TodoItem("0f8-06eb17ba8d34", Instant.parse("2020-02-27T10:31:43Z"), "Test");

        String result = todoItem.finalContent();

        assertEquals(result, "[LATE!] Test");
    }

    @Test
    void finalContent_WhenNotLate(){
        TodoItem todoItem = new TodoItem("0f8-06eb17ba8d34", Instant.now(), "Test");

        String result = todoItem.finalContent();

        assertEquals(result, "Test");
    }

}
