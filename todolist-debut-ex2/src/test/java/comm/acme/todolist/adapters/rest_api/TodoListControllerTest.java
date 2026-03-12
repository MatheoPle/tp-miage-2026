package comm.acme.todolist.adapters.rest_api;

import com.acme.todolist.Application;
import com.acme.todolist.adapters.rest_api.TodoListController;
import com.acme.todolist.application.port.in.AddTodoItem;
import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoListController.class)
@ContextConfiguration(classes = Application.class)
class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddTodoItem addTodoItemQuery;

    @MockBean
    private GetTodoItems getTodoItemsQuery;

    @Test
    void should_return_201_and_call_port_when_adding_item() throws Exception {
        TodoItem newItem = new TodoItem("0f8-06eb17ba8d34", Instant.parse("2020-02-27T10:31:43Z"), "Test");

        String jsonContent = objectMapper.writeValueAsString(newItem);

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated());

        verify(addTodoItemQuery).addTodoItem(any(TodoItem.class));
    }
}