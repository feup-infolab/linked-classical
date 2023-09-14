package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.ComposerService;
import services.MusicalWorkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/musicalWork")
public class MusicalWorkController extends Controller {

    @CrossOrigin
    @GetMapping(value = {"/{id}"})
    public HashMap<String, Object> getMusicalWork(
            @PathVariable(name = "id") String id
    ) {
        MusicalWorkService musicalWorkService = new MusicalWorkService(getFusekiHost("default"));

        try {
            return musicalWorkService.get(id);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            } else {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred.");
            }
        }
    }

    @CrossOrigin
    @PutMapping("/{composerId}/{workId}")
    public Map<String, String> updateWork(
            @PathVariable("composerId") String composerId,
            @PathVariable("workId") String workId,
            @RequestBody Map<String, Object> workData
    ) {
        String fusekiHost = getFusekiHost("default");
        MusicalWorkService musicalWorkService = new MusicalWorkService(fusekiHost);

        try {
            return musicalWorkService.update(composerId, workId, workData);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                HashMap<String, String> response = new HashMap<>();
                response.put("message", e.getMessage());
                return response;
            } else  {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong with the data that was provided.");
            }
        }
    }

    @CrossOrigin
    @PostMapping("/")
    public Map<String, String> createWork(
           @RequestBody Map<String, Object> workData
    ) {
        String fusekiHost = getFusekiHost("default");
        MusicalWorkService musicalWorkService = new MusicalWorkService(fusekiHost);

        try {
            return musicalWorkService.create(workData);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                HashMap<String, String> response = new HashMap<>();
                response.put("message", e.getMessage());
                return response;
            } else  {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is something wrong with the data that was provided.");
            }
        }
    }

    @CrossOrigin
    @DeleteMapping("/{composerId}/{workId}")
    public Map<String, String> deleteWork(
            @PathVariable("composerId") String composerId, @PathVariable("workId") String workId
    ) {
        String fusekiHost = getFusekiHost("default");
        MusicalWorkService musicalWorkService = new MusicalWorkService(fusekiHost);

        try {
            return musicalWorkService.delete(composerId, workId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The work could not be deleted.");
        }
    }

    @CrossOrigin
    @GetMapping("/search/")
    public ArrayList<HashMap<String, String>> searchAllWorks() {
        String fusekiHost = getFusekiHost("default");
        MusicalWorkService musicalWorkService = new MusicalWorkService(fusekiHost);
        return musicalWorkService.searchWork("");
    }

    @CrossOrigin
    @GetMapping("/search/{searchString}")
    public ArrayList<HashMap<String, String>> searchWork(
            @PathVariable("searchString") String searchString
    ) {
        String fusekiHost = getFusekiHost("default");
        MusicalWorkService musicalWorkService = new MusicalWorkService(fusekiHost);
        return musicalWorkService.searchWork(searchString);
    }
}