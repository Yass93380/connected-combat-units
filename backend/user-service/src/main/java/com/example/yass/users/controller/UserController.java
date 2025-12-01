package com.example.yass.users.controller;

import com.example.yass.generated.model.Users;
import com.example.yass.users.model.dto.UserDto;
import com.example.yass.users.model.entity.User;
import com.example.yass.users.model.enums.MilitaryRank;
import com.example.yass.users.model.enums.UserRole;
import com.example.yass.users.service.UserService;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    UserController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'HIERARCHY', 'USER')")
    public ResponseEntity<Set<User>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HIERARCHY', 'USER')")
    public ResponseEntity<User> user(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<User>> createUsers(@Valid @RequestBody Set<UserDto> usersDto) {
        return ResponseEntity.ok(userService.createAllUsers(usersDto));
    }

    @PostMapping("/import")
    @PreAuthorize("hasAnyRole('ADMIN', 'HIERARCHY')")
    public ResponseEntity<String> createUsersFromFile(@RequestParam(name = "file") MultipartFile file) {

        // vider la BD des users existants
        userService.deleteAllUsers();

        try (InputStream xmlInput = file.getInputStream();
             InputStream xsdInput = new ClassPathResource("/xsd_schemas/users.xsd").getInputStream()) {

                // Objet Java
                JAXBContext context = JAXBContext.newInstance(Users.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                // schema xsd
                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(new StreamSource(xsdInput));
                unmarshaller.setSchema(schema);

                // fichier xml to objets Java
                Users users = (Users) unmarshaller.unmarshal(xmlInput);
                Set<UserDto> usersEntity = users.getUser().stream().map(user ->
                    new UserDto(null, user.getName(), encoder.encode(user.getPassword()), MilitaryRank.fromString(user.getRank()),
                            UserRole.fromString(user.getRole()))).collect(Collectors.toSet());

                userService.createAllUsers(usersEntity);
            }
        catch (SAXException | JAXBException ex) {
            return ResponseEntity.badRequest().body("Le fichier \"" + file.getOriginalFilename() + "\" n'est pas conforme au model attendu.");
        }
        catch (IOException ex) {
            return ResponseEntity.internalServerError().body("Erreur serveur interne.");
        }


        return ResponseEntity.ok("Le fichier \"" + file.getOriginalFilename() + "\" a bien été uploadé.");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserDto dto) {
        // suppression silencieuse, on supprime que le User existe ou pas

        String name = dto.getName();
        userService.deleteUser(name, dto.getRank());
        return ResponseEntity.ok("L'utilisateur " + name + " a bien été supprimé s'il existe.");
    }

    @DeleteMapping("/delete/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();

        return ResponseEntity.ok("Les utilisateurs ont bien été supprimés.");
    }
}
