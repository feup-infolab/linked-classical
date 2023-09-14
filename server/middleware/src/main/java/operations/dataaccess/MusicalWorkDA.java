package operations.dataaccess;

import models.Composition;
import models.Document;
import models.MusicalWork;
import operations.SPARQLOperations;
import operations.queries.MusicalWorks;
import org.apache.jena.rdf.model.Model;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicalWorkDA {

    public String host;
    MusicalWorks queries = new MusicalWorks();
    public Model model;

    public MusicalWorkDA(String host) {
        this.host = host;
    }
    public MusicalWork getWork(String id) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);
        MusicalWork musicalWork = new MusicalWork();

        ArrayList<HashMap<String, String>> iriTriples = conn.executeQuery(queries.getIri(id));

        if (iriTriples.isEmpty()) {
            throw new Exception(String.format("The musical work with id '%s' does not exist.", id));
        }
        musicalWork.setURI(iriTriples.get(0).get("musicalWork"));

        ArrayList<HashMap<String, String>> labelTriples = conn.executeQuery(queries.getLabel(id));
        if (!labelTriples.isEmpty()) {
            musicalWork.setLabel(labelTriples.get(0).get("label"));
        }

        ArrayList<HashMap<String, String>> aliasTriples = conn.executeQuery(queries.getAlias(id));
        ArrayList<String> alias = new ArrayList<>();
        for (HashMap<String, String> aliasTriple: aliasTriples) {
            alias.add(aliasTriple.get("alias"));
        }
        musicalWork.setAlias(alias);

        ArrayList<HashMap<String, String>> titleTriples = conn.executeQuery(queries.getTitle(id));
        if (!titleTriples.isEmpty()) {
            musicalWork.setTitle(titleTriples.get(0).get("title"));
        }

        ArrayList<HashMap<String, String>> remarksTriples = conn.executeQuery(queries.getRemarks(id));
        if (!remarksTriples.isEmpty()) {
            musicalWork.setRemarks(remarksTriples.get(0).get("remarks"));
        }

        ArrayList<HashMap<String, String>> keyTriples = conn.executeQuery(queries.getKey(id));
        if (!keyTriples.isEmpty()) {
            musicalWork.setKey(keyTriples.get(0).get("key"));
        }

        ArrayList<HashMap<String, String>> numberTriples = conn.executeQuery(queries.getNumber(id));
        if (!numberTriples.isEmpty()) {
            musicalWork.setNumber(numberTriples.get(0).get("number"));
        }

        ArrayList<HashMap<String, String>> opusTriples = conn.executeQuery(queries.getOpus(id));
        if (!opusTriples.isEmpty()) {
            musicalWork.setOpus(opusTriples.get(0).get("opus"));
        }

        ArrayList<HashMap<String, String>> catalogueTriples = conn.executeQuery(queries.getCatalogue(id));
        if (!catalogueTriples.isEmpty()) {
            musicalWork.setCatalogue(catalogueTriples.get(0).get("catalogue"));
        }

        ArrayList<HashMap<String, String>> isPartOfTriples = conn.executeQuery(queries.getIsPartOf(id));
        if (!isPartOfTriples.isEmpty()) {
            HashMap<String, String> isPartOf = new HashMap<>();
            isPartOf.put("musicalWorkURI", isPartOfTriples.get(0).get("partOf"));
            isPartOf.put("musicalWorkUuid", isPartOfTriples.get(0).get("partOfUuid"));
            musicalWork.setIsPartOf(isPartOf);
        }

        ArrayList<HashMap<String, String>> compositionTriples = conn.executeQuery(queries.getComposition(id));
        if (!compositionTriples.isEmpty()) {
            Composition composition = new Composition();
            String dateString = compositionTriples.get(0).get("date");
            int date = Integer.parseInt(dateString.split("\\^\\^")[0]);
            composition.setDate(date);
            composition.setComposerURI(compositionTriples.get(0).get("composer"));
            composition.setComposerUuid(compositionTriples.get(0).get("composerId"));
            musicalWork.setComposition(composition);
        }

        ArrayList<HashMap<String, String>> documentTriples = conn.executeQuery(queries.getDocument(id));
        ArrayList<Document> documents = new ArrayList<>();
        for (HashMap<String, String> documentTriple: documentTriples) {
            Document document = new Document();
            document.setSiteName(documentTriple.get("siteName"));
            document.setDocumentURI(documentTriple.get("document"));
            document.setDocumentUuid(documentTriple.get("documentUuid"));
            documents.add(document);
        }
        musicalWork.setDocuments(documents);

        return musicalWork;
    }

    private boolean validateInsertForm(Map<String, Object> insertForm) {
        if (!insertForm.containsKey("URI") || !insertForm.containsKey("title"))
            return false;

        if (!(insertForm.get("URI") instanceof String) || !(insertForm.get("title") instanceof String))
            return false;

        String URI = (String) insertForm.get("URI");

        Pattern pattern = Pattern.compile("http://dbtune.org/classical/resource/work/.+");
        Matcher matcher = pattern.matcher(URI);
        return matcher.matches();
    }

    public Map<String, String> createWork(Map<String, Object> workData) throws Exception {
        HashMap<String, String> response = new HashMap<>();

        if (!validateInsertForm(workData)) {
            response.put("message", "Invalid body! Please provide the work URI and the associated triples.");
            return response;
        }

        SPARQLOperations conn = new SPARQLOperations(this.host);

        String musicalWorkUuid = UUID.randomUUID().toString();
        String musicalWorkURI = (String) workData.get("URI");
        String title = (String) workData.get("title");

        conn.executeUpdate(queries.createWork(
                musicalWorkUuid,
                musicalWorkURI,
                title
        ));

        response.put("message", "Musical work created successfully.");
        response.put("uuid", musicalWorkUuid);

        return response;
    }

    public Map<String, String> deleteWork(String composerId, String workId) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);
        conn.executeUpdate(queries.deleteWork(composerId, workId));

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Work was deleted successfully.");

        return response;
    }

    public ArrayList<HashMap<String, String>> searchWork(String searchString) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        return conn.executeQuery(queries.searchWork(searchString));
    }
}
