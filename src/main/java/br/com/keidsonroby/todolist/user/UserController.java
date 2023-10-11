package br.com.keidsonroby.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;
  
  // @PostMapping("/")
  // public void create(@RequestBody UserModel userModel) {
  //   System.out.println(userModel.getUsername());
  // }
  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      // Request error
      // quando tiver um user com o mesmo nome
      // Mensagem de erro
      // Status code
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usário já existe!");
    }

    // Request success
    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
