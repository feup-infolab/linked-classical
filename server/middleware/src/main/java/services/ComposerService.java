package services;

import models.Composer;
import operations.dataaccess.ComposerDA;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class ComposerService {
    ComposerDA composerDA;

    public ComposerService(String host) {
        composerDA = new ComposerDA(host);
    }

    public HashMap<String, Object> get(String id) throws Exception {
        Composer composer = composerDA.getComposer(id);

        HashMap<String, Object> result = new HashMap<>();

        result.put("URI", composer.getURI());
        result.put("id", id);
        result.put("label", composer.getLabel());
        result.put("name", composer.getName());
        result.put("nationalities", composer.getNationalities());
        result.put("alias", composer.getAlias());
        result.put("period", composer.getPeriod());
        result.put("birth", composer.getBirth());
        result.put("death", composer.getDeath());
        result.put("locations", composer.getLocations());
        result.put("sameAs", composer.getSameAs());
        result.put("pages", composer.getPages());
        result.put("wikipediaPage", composer.getWikipediaPage());
        result.put("hasInfluenced", composer.getHasInfluenced());
        result.put("influencedBy", composer.getInfluencedBy());
        result.put("stylePeriods", composer.getStylePeriods());

        return result;
    }

    public HashMap<String, Object> getDBpediaData(String id) throws Exception {
        Composer composer = composerDA.getDBpediaData(id);

        HashMap<String, Object> result = new HashMap<>();

        result.put("URI", composer.getURI());
        //result.put("associatedTriples", composer.getAssociatedTriples());

        return result;
    }

    public Map<String, String> delete(String id) throws Exception {
        return composerDA.deleteComposer(id);
    }

    public Map<String, String> insert(Map<String, Object> insertForm) throws Exception {
        return composerDA.insertComposer(insertForm);
    }

    public ArrayList<HashMap<String, String>> searchComposer(String searchString) {
        return composerDA.searchComposer(searchString);
    }
}


