package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.ComposerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/composer")
public class ComposerController extends Controller {

    @CrossOrigin
    @GetMapping(value = {"/{id}"})
    public HashMap<String, Object> getComposer(
            @PathVariable(name = "id") String id
    ) {
        ComposerService composerService = new ComposerService(getFusekiHost("default"));

        try {
            return composerService.get(id);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            } else {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred.");
            }
        }
    }

    @CrossOrigin
    @GetMapping(value = {"/dbpedia-federation/{id}"})
    public HashMap<String, Object> getDBpediaData(
            @PathVariable(name = "id") String id
    ) {
        ComposerService composerService = new ComposerService(getFusekiHost("default"));

        try {
            return composerService.getDBpediaData(id);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                HashMap<String, Object> response = new HashMap<>();
                response.put("message", e.getMessage());
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred.");
            }
        }
    }

    @CrossOrigin
    @DeleteMapping(value = {"/{id}"})
    public Map<String, String> deleteComposer(
            @PathVariable(name = "id") String id
    ) {
        ComposerService composerService = new ComposerService(getFusekiHost("default"));

        try {
            return composerService.delete(id);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                HashMap<String, String> response = new HashMap<>();
                response.put("message", e.getMessage());
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred.");
            }
        }
    }

    @CrossOrigin
    @PostMapping(value = {"/"})
    public Map<String, String> insertComposer(
            @RequestBody Map<String, Object> insertForm
    ) {
        ComposerService composerService = new ComposerService(getFusekiHost("default"));

        try {
            return composerService.insert(insertForm);
        } catch(Exception e) {
            if (e.getMessage() != null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred.");
            }
        }
    }

    @CrossOrigin
    @GetMapping(value = {"/search"})
    public ArrayList<HashMap<String, String>> searchAllComposers() {
        ComposerService composerService = new ComposerService(getFusekiHost("default"));

        return composerService.searchComposer("");
    }

    @CrossOrigin
    @GetMapping(value = {"/search/{searchString}"})
    public ArrayList<HashMap<String, String>> searchComposer(
            @PathVariable(name = "searchString") String searchString
    ) {
        ComposerService composerService = new ComposerService(getFusekiHost("default"));

        return composerService.searchComposer(searchString);
    }
}