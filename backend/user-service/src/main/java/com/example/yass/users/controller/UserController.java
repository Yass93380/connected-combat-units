package com.example.yass.users.controller;

import com.example.yass.generated.model.Users;
import com.example.yass.users.model.dto.UserCreateOrUpdateDto;
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

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Set<User>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> user(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Set<User>> createUsers(@Valid @RequestBody Set<UserCreateOrUpdateDto> usersDto) {
        return ResponseEntity.ok(userService.createAllUsers(usersDto));
    }

    @PostMapping("/import")
    public ResponseEntity<String> createUsersFromFile(@RequestParam(name = "file") MultipartFile file) {

        try (InputStream xmlInput = file.getInputStream();
             InputStream xsdInput = new ClassPathResource("/xsd_schemas/users.xsd").getInputStream()) {

                // Objet Java
                JAXBContext context = JAXBContext.newInstance(Users.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                // schema xsd
                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(new StreamSource(xsdInput));
                unmarshaller.setSchema(schema);

                // xml to objet Java
                Users users = (Users) unmarshaller.unmarshal(xmlInput);
                Set<UserCreateOrUpdateDto> usersEntity = users.getUser().stream().map(user ->
                        new UserCreateOrUpdateDto(null, user.getName(), MilitaryRank.valueOf(user.getRank()),
                                UserRole.valueOf(user.getRole()))).collect(Collectors.toSet());

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
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserCreateOrUpdateDto dto) {
        // suppression silencieuse, on supprime que le User existe ou pas

        String name = dto.getName();
        userService.deleteUser(name, dto.getRank());
        return ResponseEntity.ok("L\'utilisateur " + name + " a bien été supprimé s\'il existe.");
    }
}
