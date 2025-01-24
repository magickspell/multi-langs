package magickspell.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
public class HelloWorldController {
  private final UserRepository userRepository;

  public HelloWorldController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/hello")
  public String helloWorld() {
    return "Hello, World! 55";
  }

  @GetMapping("/hello/user")
  public StringBuffer helloWorldParams(
      @RequestParam(name="name", required=false, defaultValue="World") String name,
      @RequestParam(name="age", required=false, defaultValue="27") int age
  ) {
    return new StringBuffer("hello, " + name + " your age is " + age);
  }

  @PostMapping("/user")
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    System.out.println("new user:");
    System.out.println(user);
    UserEntity savedUser = userRepository.save(user);
    System.out.println(savedUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserEntity>> getUsersByName(
      @RequestParam(value = "name", defaultValue = "Alex") String name
  ) {
    List<UserEntity> users = userRepository.findByName(name);
    System.out.println("found users:");
    System.out.println(users);
    System.out.println(users.toString());
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String json = objectMapper.writeValueAsString(users);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(users);
  }
}
