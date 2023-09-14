package operations.dataaccess;

import models.*;
import operations.SPARQLOperations;
import operations.queries.Composers;
import org.apache.jena.rdf.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ComposerDA {
    public String host;
    Composers queries = new Composers();
    public Model model;

    public ComposerDA(String host) {
        this.host = host;
    }
    public Composer getComposer(String id) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);
        Composer composer = new Composer();

        ArrayList<HashMap<String, String>> iriTriples = conn.executeQuery(queries.getIri(id));

        if (iriTriples.isEmpty()) {
            throw new Exception(String.format("The composer with id '%s' does not exist.", id));
        }
        composer.setURI(iriTriples.get(0).get("composerIri"));

        ArrayList<HashMap<String, String>> labelTriples = conn.executeQuery(queries.getLabel(id));
        composer.setLabel(labelTriples.get(0).get("label"));

        ArrayList<HashMap<String, String>> nameTriples = conn.executeQuery(queries.getName(id));
        composer.setName(nameTriples.get(0).get("name"));

        ArrayList<HashMap<String, String>> nationalityTriples = conn.executeQuery(queries.getNationalities(id));
        ArrayList<String> nationalities = new ArrayList<>();
        for (HashMap<String, String> nationalityTriple: nationalityTriples) {
            nationalities.add(nationalityTriple.get("nationality"));
        }
        composer.setNationalities(nationalities);

        ArrayList<HashMap<String, String>> aliasTriples = conn.executeQuery(queries.getAlias(id));
        ArrayList<String> alias = new ArrayList<>();
        for (HashMap<String, String> aliasTriple: aliasTriples) {
            alias.add(aliasTriple.get("alias"));
        }
        composer.setAlias(alias);

        ArrayList<HashMap<String, String>> periodTriples = conn.executeQuery(queries.getPeriod(id));
        if (!periodTriples.isEmpty()) {
            composer.setPeriod(periodTriples.get(0).get("period"));
        }

        ArrayList<HashMap<String, String>> birthTriples = conn.executeQuery(queries.getBirth(id));
        if (!birthTriples.isEmpty()) {
            HashMap<String, String> birthTriple = birthTriples.get(0);

            String birthIri = birthTriple.get("birth");
            String birthLabel = birthTriple.get("label");
            String birthUuid = birthTriple.get("birthUuid");
            String approximateString = birthTriple.get("approximate");
            String dateString = birthTriple.get("date");

            int date = Integer.parseInt(dateString.split("\\^\\^")[0]);
            boolean approximate = (approximateString != null) && (!approximateString.isEmpty());

            Birth birth = new Birth();
            birth.setBirthURI(birthIri);
            birth.setLabel(birthLabel);
            birth.setBirthUuid(birthUuid);
            birth.setApproximate(approximate);
            birth.setDate(date);

            composer.setBirth(birth);
        }

        ArrayList<HashMap<String, String>> deathTriples = conn.executeQuery(queries.getDeath(id));
        if (!deathTriples.isEmpty()) {
            HashMap<String, String> deathTriple = deathTriples.get(0);

            String deathIri = deathTriple.get("death");
            String deathLabel = deathTriple.get("label");
            String deathUuid = deathTriple.get("deathUuid");
            String approximateString = deathTriple.get("approximate");
            String dateString = deathTriple.get("date");

            int date = Integer.parseInt(dateString.split("\\^\\^")[0]);
            boolean approximate = (approximateString != null) && (!approximateString.isEmpty());

            Death death = new Death();
            death.setDeathURI(deathIri);
            death.setLabel(deathLabel);
            death.setDeathUuid(deathUuid);
            death.setApproximate(approximate);
            death.setDate(date);

            composer.setDeath(death);
        }

        ArrayList<HashMap<String, String>> locationsTriples = conn.executeQuery(queries.getLocations(id));
        ArrayList<Location> locations = new ArrayList<>();
        for (HashMap<String, String> locationTriple: locationsTriples) {
            Location location = new Location();
            location.setURI(locationTriple.get("location"));
            location.setName(locationTriple.get("locationName"));
            location.setLatitude(locationTriple.get("latitude"));
            location.setLongitude(locationTriple.get("longitude"));
            location.setPopulation(locationTriple.get("locationPopulation"));
            locations.add(location);
        }
        composer.setLocations(locations);

        ArrayList<HashMap<String, String>> sameAsTriples = conn.executeQuery(queries.getSameAs(id));
        ArrayList<String> sameAs = new ArrayList<>();
        for (HashMap<String, String> sameAsTriple: sameAsTriples) {
            sameAs.add(sameAsTriple.get("sameAs"));
        }
        composer.setSameAs(sameAs);

        ArrayList<HashMap<String, String>> pagesTriples = conn.executeQuery(queries.getPages(id));
        ArrayList<String> pages = new ArrayList<>();
        for (HashMap<String, String> pageTriple: pagesTriples) {
            pages.add(pageTriple.get("page"));
        }
        composer.setPages(pages);

        ArrayList<HashMap<String, String>> wikipediaPageTriples = conn.executeQuery(queries.getWikipediaPage(id));
        if (!wikipediaPageTriples.isEmpty()) {
            composer.setWikipediaPage(wikipediaPageTriples.get(0).get("wikipediaPage"));
        }

        ArrayList<HashMap<String, String>> hasInfluencedTriples = conn.executeQuery(queries.getHasInfluenced(id));
        ArrayList<HashMap<String, String>> influencedComposers = new ArrayList<>();
        for (HashMap<String, String> hasInfluencedTriple: hasInfluencedTriples) {
            HashMap<String, String> influencedComposer = new HashMap<>();
            influencedComposer.put("composerURI", hasInfluencedTriple.get("influencedComposer"));
            influencedComposer.put("composerUuid", hasInfluencedTriple.get("influencedComposerUuid"));
            influencedComposers.add(influencedComposer);
        }
        composer.setHasInfluenced(influencedComposers);

        ArrayList<HashMap<String, String>> influencedByTriples = conn.executeQuery(queries.getInfluencedBy(id));
        ArrayList<HashMap<String, String>> influencedByComposers = new ArrayList<>();
        for (HashMap<String, String> influencedByTriple: influencedByTriples) {
            HashMap<String, String> influencedByComposer = new HashMap<>();
            influencedByComposer.put("composerURI", influencedByTriple.get("composerWhoInfluenced"));
            influencedByComposer.put("composerUuid", influencedByTriple.get("composerWhoInfluencedUuid"));
            influencedByComposers.add(influencedByComposer);
        }
        composer.setInfluencedBy(influencedByComposers);

        ArrayList<HashMap<String, String>> stylePeriodsTriples = conn.executeQuery(queries.getStylePeriods(id));
        ArrayList<StylePeriod> stylePeriods = new ArrayList<>();
        for (HashMap<String, String> stylePeriodTriple: stylePeriodsTriples) {
            StylePeriod stylePeriod = new StylePeriod();
            stylePeriod.setStylePeriodURI(stylePeriodTriple.get("stylePeriod"));
            stylePeriod.setStylePeriodUuid(stylePeriodTriple.get("stylePeriodUuid"));
            stylePeriod.setTitle(stylePeriodTriple.get("stylePeriodTitle"));
            stylePeriods.add(stylePeriod);
        }
        composer.setStylePeriods(stylePeriods);


        return composer;
    }

    public Composer getDBpediaData(String id) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);
        Composer composer = new Composer();

        ArrayList<HashMap<String, String>> associatedTriples = conn.executeQuery(queries.getDBpediaData(id));

        if (associatedTriples.size() == 0) {
            throw new Exception(String.format("The composer with id '%s' does not exist in DBPedia.", id));
        }

        //composer.setAssociatedTriples(associatedTriples);

        return composer;
    }

    public Map<String, String> deleteComposer(String id) throws Exception {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        conn.executeUpdate(queries.deleteComposer(id));

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Composer deleted successfully.");

        return response;
    }

    private boolean validateInsertForm(Map<String, Object> insertForm) {
        if (!insertForm.containsKey("URI") || !insertForm.containsKey("name"))
            return false;

        if (!(insertForm.get("URI") instanceof String) || !(insertForm.get("name") instanceof String))
            return false;

        String URI = (String) insertForm.get("URI");

        Pattern pattern = Pattern.compile("http://dbtune.org/classical/resource/composer/.+");
        Matcher matcher = pattern.matcher(URI);
        return matcher.matches();
    }

    public Map<String, String> insertComposer(Map<String, Object> insertForm) throws Exception {
        HashMap<String, String> response = new HashMap<>();

        if (!validateInsertForm(insertForm)) {
            response.put("message", "Invalid body! Please provide a valid composer URI and the associated information.");
            return response;
        }

        SPARQLOperations conn = new SPARQLOperations(this.host);

        String composerUuid = UUID.randomUUID().toString();
        String composerURI = (String) insertForm.get("URI");
        String name = (String) insertForm.get("name");

        conn.executeUpdate(queries.insertComposer(
                composerUuid,
                composerURI,
                name
        ));

        response.put("message", "Composer created successfully.");
        response.put("uuid", composerUuid);

        return response;
    }

    public ArrayList<HashMap<String, String>> searchComposer(String searchString) {
        SPARQLOperations conn = new SPARQLOperations(this.host);

        return conn.executeQuery(queries.searchComposer(searchString));
    }
}
