package hexlet.code.app.controller.api;

import hexlet.code.app.dto.user.UserCreateDTO;
import hexlet.code.app.dto.user.UserUpdateDTO;
import hexlet.code.app.dto.user.UserDTO;
import hexlet.code.app.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/users")
public final class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> index() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO show(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserCreateDTO userData) {
        return userService.create(userData);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable long id, @RequestBody @Valid UserUpdateDTO userData) {
        return userService.update(id, userData);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}