package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController taskController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void notSaveIfDescriptionIsNull() throws ValidationException {
        Task task = new Task();
        task.setTask("");
        task.setDueDate(LocalDate.now());
        TaskController taskController = new TaskController();
        try {
            taskController.save(task);
            Assert.fail("Não deveria chegar aqui");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void notSaveIfDateIsNull() {
        Task task = new Task();
        task.setTask("Task1");
        TaskController taskController = new TaskController();
        try {
            taskController.save(task);
            Assert.fail("Não deveria chegar aqui");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void notSaveIfDateIsPast() {
        Task task = new Task();
        task.setTask("Task1");
        task.setDueDate(LocalDate.now().minusMonths(1));
        TaskController taskController = new TaskController();
        try {
            taskController.save(task);
            Assert.fail("Não deveria chegar aqui");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void saveSuccess() throws ValidationException {
        Task task = new Task();
        task.setTask("Task1");
        task.setDueDate(LocalDate.now());
        taskController.save(task);
        Mockito.verify(taskRepo).save(task);
    }
}
