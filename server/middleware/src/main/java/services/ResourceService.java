package services;

import models.Resource;
import operations.dataaccess.ResourceDA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResourceService {
    ResourceDA resourceDA;

    public ResourceService(String host) {
        this.resourceDA = new ResourceDA(host);
    }

    public HashMap<String, Object> get(String uuid) {
        Resource resource = resourceDA.getResource(uuid);
        HashMap<String, Object> result = new HashMap<>();

        result.put("URI", resource.getURI());
        result.put("label", resource.getLabel());
        result.put("type", resource.getType());
        result.put("outgoingStatements", resource.getOutgoingStatements());
        result.put("incomingStatements", resource.getIncomingStatements());

        return result;
    }

    public ArrayList<HashMap<String, String>> getExternalDatasets() {
        return resourceDA.getExternalDatasets();
    }

    public Map<String, String> linkExternalEntity(HashMap<String,String> entityLinkForm) throws Exception {
        resourceDA.linkExternalEntity(
                entityLinkForm.get("linkedClassicalEntityURI"),
                entityLinkForm.get("externalEntityURI"),
                entityLinkForm.get("externalDatasetURI")
        );

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "External entity linked successfully");

        return response;
    }

    public Map<String, String> deleteExternalEntityLink(HashMap<String,String> removeEntityLinkForm) throws Exception {
        resourceDA.deleteExternalEntityLink(
                removeEntityLinkForm.get("linkedClassicalEntityURI"),
                removeEntityLinkForm.get("externalEntityURI")
        );

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "External entity unlinked successfully");

        return response;
    }

    public HashMap<String, ArrayList<HashMap<String, String>>> getExternalInfoboxes(String uuid) {
        ArrayList<String> availableExternalDatasets = resourceDA.getAvailableExternalDatasets(uuid);

        HashMap<String, ArrayList<HashMap<String, String>>> result = new HashMap<>();

        for (String externalDataset: availableExternalDatasets) {
            switch (externalDataset) {
                case "https://www.wikidata.org/": {
                    result.put("https://www.wikidata.org/", resourceDA.getExternalInfoboxesWikidata(uuid));
                    break;
                }
                case "https://www.dbpedia.org/": {
                    result.put("https://www.dbpedia.org/", new ArrayList<>());
                    break;
                }
                default: {
                    System.err.println(String.format("Unknown external dataset: '%s'", externalDataset));
                    break;
                }
            }
        }

        return result;
    }

    public HashMap<String, HashMap<String, Object>> getExternalInfoSummary(String uuid) {
        ArrayList<String> availableExternalDatasets = resourceDA.getAvailableExternalDatasets(uuid);

        HashMap<String, HashMap<String, Object>> result = new HashMap<>();

        for (String externalDataset: availableExternalDatasets) {
            switch (externalDataset) {
                case "https://www.wikidata.org/": {
                    result.put("https://www.wikidata.org/", resourceDA.getExternalEntityWikidataSummary(uuid));
                    break;
                }
                case "https://www.dbpedia.org/": {
                    result.put("https://www.dbpedia.org/", new HashMap<>());
                    break;
                }
                default: {
                    System.err.println(String.format("Unknown external dataset: '%s'", externalDataset));
                    break;
                }
            }
        }

        return result;
    }

    public ArrayList<HashMap<String, String>> searchComposer(String searchText) {
        return resourceDA.searchComposer(searchText);
    }

    public ArrayList<HashMap<String, String>> searchMusicalWork(String searchText) {
        return resourceDA.searchMusicalWork(searchText);
    }
}
