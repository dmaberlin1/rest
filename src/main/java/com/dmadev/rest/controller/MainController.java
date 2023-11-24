import com.dmadev.rest.DTO.CatDTO;
import com.dmadev.rest.entity.Cat;
import com.dmadev.rest.exception.CatNotFoundException;
import com.dmadev.rest.repository.CatRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name="main_methods", description = "Main API methods")
public class MainController {

    private final CatRepo catRepo;
    private final ObjectMapper objectMapper;

    @Operation(
            summary = "Post new cat in database",
            description = "Add a new cat to the database using the provided CatDTO."
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info("New row: " + catRepo.save(
                Cat.builder()
                        .age(catDTO.getAge())
                        .name(catDTO.getName())
                        .weight(catDTO.getWeight())
                        .build()));
    }

    @SneakyThrows
    @Operation(
            summary = "Get all cats",
            description = "Retrieve a list of all cats from the database."
    )
    @GetMapping("/api/all")
    public String getAll() throws CatNotFoundException {
        List<Cat> cats = catRepo.findAll();
        if (cats == null) {
            throw new CatNotFoundException("Cats not found");
        }

        return objectMapper.writeValueAsString(cats);
    }

    @SneakyThrows
    @Operation(
            summary = "Get all cats",
            description = "Retrieve a list of all cats from the database."
    )
    @GetMapping("/api/allCats")
    public List<Cat> getAllCats() throws CatNotFoundException {
        return catRepo.findAll();
    }

    @Operation(
            summary = "Get a specific cat by ID",
            description = "Retrieve a specific cat from the database using its ID."
    )
    @GetMapping("/api")
    public Cat getCat(@RequestParam long id) {
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Delete a cat by ID",
            description = "Delete a cat from the database using its ID."
    )
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam long id) throws CatNotFoundException {
        if (!catRepo.existsById(id)) {
            throw new CatNotFoundException("Cat not found");
        }
        catRepo.deleteById(id);
    }

    @SneakyThrows
    @Operation(
            summary = "Update a cat",
            description = "Update an existing cat in the database."
    )
    @PutMapping("/api/put")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            throw new CatNotFoundException("Cat not found");
        }

        return catRepo.save(cat).toString();
    }
}
