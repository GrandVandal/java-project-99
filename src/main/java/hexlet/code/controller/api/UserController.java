package hexlet.code.controller.api;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class UserController {

    private static final String AUTHORITY = """
            @userUtils.getCurrentUser().getId() == #id
            or @userUtils.getCurrentUser().getRole().equals('ADMIN')""";

    @Autowired
    private UserService userService;

    /**
     * Authentication needed.
     * @return {@code List<UserDTO>}
     */
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> index() {
        var users = userService.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(users.size()))
                .body(users);
    }

    /**
     * Authentication needed.
     * @param id long
     * @return {@code UserDTO}
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO show(@PathVariable long id) {
        return userService.findById(id);
    }

    /**
     * Authentication free.
     * @param userData UserCreateDTO
     * @return {@code UserDTO}
     */
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserCreateDTO userData) {
        return userService.create(userData);
    }

    /**
     * Current user or admin only.
     * @param id long
     * @param userData UserCreateDTO
     * @return {@code UserDTO}
     */
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(AUTHORITY)
    public UserDTO update(@PathVariable long id, @RequestBody @Valid UserUpdateDTO userData) {
        return userService.update(id, userData);
    }

    /**
     * Current user or admin only.
     * @param id long
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(AUTHORITY)
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
