package operations.dataaccess;

import models.Resource;
import operations.SPARQLOperations;
import operations.queries.Resources;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceDA {
    public String host;
    Resources queries = new Resources();

    public ResourceDA(String host) {
        this.host = host;
    }

    public Resource getResource(String uuid) {
        SPARQLOperations conn = new SPARQLOperations(this.host);
        Resource resource = new Resource();

        ArrayList<HashMap<String, String>> uriTriples = conn.executeQuery(queries.getURI(uuid));

        if (uriTriples.isEmpty()) {
            return resource;
        }
        resource.setURI(uriTriples.get(0).get("resource"));

        ArrayList<HashMap<String, String>> outgoingRelations = conn.executeQuery(queries.getOutgoingRelations(uuid));
        ArrayList<HashMap<String, String>> incomingRelations = conn.executeQuery(queries.getIncomingRelations(uuid));

        resource.setOutgoingStatements(outgoingRelations);
        resource.setIncomingStatements(incomingRelations);

        ArrayList<HashMap<String, String>> labelTriples = conn.executeQuery(queries.getResourceLabel(uuid));

        if (!labelTriples.isEmpty()) {
            resource.setLabel(labelTriples.get(0).get("label"));
        }

        ArrayList<HashMap<String, String>> typeTriples = conn.executeQuery(queries.getType(uuid));

        if (!typeTriples.isEmpty()) {
            String typeUri = typeTriples.get(0).get("type");
            String[] typeUriSplit = typeUri.split("[/#]");
            String type = typeUriSplit[typeUriSplit.length - 1];
            resource.setType(type);
        }

        return resource;
    }

    public ArrayList<HashMap<String, String>> getExternalDatasets() {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        return conn.executeQuery(queries.getExternalDatasets());
    }

    public void linkExternalEntity(String linkedClassicalEntityURI, String externalEntityURI, String externalDatasetURI) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        conn.executeUpdateList(queries.linkExternalEntity(linkedClassicalEntityURI, externalEntityURI, externalDatasetURI));
    }

    public void deleteExternalEntityLink(String linkedClassicalEntityURI, String externalEntityURI) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        conn.executeUpdateList(queries.deleteExternalEntityLink(linkedClassicalEntityURI, externalEntityURI));
    }

    public ArrayList<String> getAvailableExternalDatasets(String uuid) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        ArrayList<HashMap<String, String>> queryResult = conn.executeQuery(queries.getAvailableExternalDatasets(uuid));

        ArrayList<String> externalDatasets = new ArrayList<>();

        for (HashMap<String, String> resultItem: queryResult) {
            externalDatasets.add(resultItem.get("externalDataset"));
        }

        return externalDatasets;
    }

    public ArrayList<HashMap<String, String>> getExternalInfoboxesWikidata(String uuid) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        return conn.executeQuery(queries.getExternalInfoboxesWikidata(uuid));
    }

    public HashMap<String, Object> getExternalEntityWikidataSummary(String uuid) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        HashMap<String, Object> result = new HashMap<>();

        ArrayList<HashMap<String, String>> queryResult = conn.executeQuery(queries.getExternalEntityWikidataSummary(uuid));

        if (queryResult.size() == 0)
            return result;

        result.put("labelEn", queryResult.get(0).get("labelEn"));
        result.put("description", queryResult.get(0).get("description"));

        ArrayList<String> instanceOfArray = new ArrayList<>();
        ArrayList<String> instanceOfLabelArray = new ArrayList<>();

        for (HashMap<String, String> queryResultRow: queryResult) {
            instanceOfArray.add(queryResultRow.get("instanceOf"));
            instanceOfLabelArray.add(queryResultRow.get("instanceOfLabel"));
        }

        result.put("instanceOf", instanceOfArray);
        result.put("instanceOfLabel", instanceOfLabelArray);

        return result;
    }

    public ArrayList<HashMap<String, String>> searchComposer(String searchText) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        return conn.executeQuery(queries.searchComposer(searchText));
    }

    public ArrayList<HashMap<String, String>> searchMusicalWork(String searchText) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        return conn.executeQuery(queries.searchMusicalWork(searchText));
    }
}
