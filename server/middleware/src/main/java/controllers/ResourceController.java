package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.ResourceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/resource")
public class ResourceController extends Controller {
    @CrossOrigin
    @GetMapping("/{uuid}")
    public HashMap<String, Object> getResource(
            @PathVariable("uuid") String uuid
    ) {
        String fusekiHost = getFusekiHost("default");
        ResourceService resourceService = new ResourceService(fusekiHost);
        HashMap<String, Object> result = resourceService.get(uuid);
        if (result.get("URI") == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find a resource with the provided IDs.");
        return result;
    }

    @CrossOrigin
    @GetMapping(value = {"/external-datasets"})
    public ArrayList<HashMap<String, String>> externalDatasets () {
        String fusekiHost = getFusekiHost("default");
        ResourceService resourceService = new ResourceService(fusekiHost);

        return resourceService.getExternalDatasets();
    }

    @CrossOrigin
    @PostMapping(value = {"/link-external-entity"})
    @ResponseBody
    public Map<String, String> linkExternalEntity(
            @RequestBody HashMap<String, String> entityLinkForm
    ) {
        String fusekiHost = getFusekiHost("default");
        ResourceService resourceService = new ResourceService(fusekiHost);

        try {
            return resourceService.linkExternalEntity(entityLinkForm);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred");
            }
        }
    }

    @CrossOrigin
    @DeleteMapping(value = {"/unlink-external-entity"})
    public Map<String, String> deleteExternalEntityLink(
            @RequestBody HashMap<String, String> removeEntityLinkForm
    ) {
        String fusekiHost = getFusekiHost("default");
        ResourceService resourceService = new ResourceService(fusekiHost);

        try {
            return resourceService.deleteExternalEntityLink(removeEntityLinkForm);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some error occurred");
            }
        }
    }

    @CrossOrigin
    @GetMapping(value = {"/external-infoboxes"})
    public HashMap<String, ArrayList<HashMap<String, String>>> externalInfoboxes(
            @RequestParam(value = "uuid") String uuid
    ) {
        String fusekiHost = getFusekiHost("default");
        ResourceService resourceService = new ResourceService(fusekiHost);

        return resourceService.getExternalInfoboxes(uuid);
    }

    @CrossOrigin
    @GetMapping(value = {"/external-info-summary"})
    public HashMap<String, HashMap<String, Object>> getExternalInfoSummary(
            @RequestParam(value = "uuid", defaultValue = "") String uuid
    ) {
        String fusekiHost = getFusekiHost("default");
        ResourceService resourceService = new ResourceService(fusekiHost);

        return resourceService.getExternalInfoSummary(uuid);
    }
}
