package hexlet.code.controller.api;

import hexlet.code.dto.task.status.TaskStatusDTO;
import hexlet.code.dto.task.status.TaskStatusCreateDTO;
import hexlet.code.dto.task.status.TaskStatusUpdateDTO;
import hexlet.code.service.TaskStatusService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task_statuses")
public class TaskStatusController {

    @Autowired
    private TaskStatusService taskStatusService;

    /**
     * Authentication needed.
     * @return {@code List<TaskStatusDTO>}
     */
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskStatusDTO>> index() {
        var taskStatuses = taskStatusService.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(taskStatuses.size()))
                .body(taskStatuses);
    }

    /**
     * Authentication needed.
     * @param id long
     * @return {@code TaskStatusDTO}
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDTO show(@PathVariable long id) {
        return taskStatusService.findById(id);
    }

    /**
     * Authentication needed.
     * @param taskStatusData TaskStatusCreateDTO
     * @return {@code TaskStatusDTO}
     */
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatusDTO create(@RequestBody @Valid TaskStatusCreateDTO taskStatusData) {
        return taskStatusService.create(taskStatusData);
    }

    /**
     * Authentication needed.
     * @param id long
     * @param taskStatusData TaskStatusUpdateDTO
     * @return {@code TaskStatusDTO}
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDTO update(@PathVariable long id, @RequestBody @Valid TaskStatusUpdateDTO taskStatusData) {
        return taskStatusService.update(id, taskStatusData);
    }

    /**
     * Authentication needed.
     * @param id long
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        taskStatusService.delete(id);
    }
}
