package services;

import models.MusicalWork;
import operations.dataaccess.MusicalWorkDA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MusicalWorkService {

    MusicalWorkDA musicalWorkDA;

    public MusicalWorkService(String host) {
        this.musicalWorkDA = new MusicalWorkDA(host);
    }

    public HashMap<String, Object> get(String id) throws Exception {
        MusicalWork musicalWork = musicalWorkDA.getWork(id);
        HashMap<String, Object> result = new HashMap<>();

        result.put("URI", musicalWork.getURI());
        result.put("id", id);
        result.put("label", musicalWork.getLabel());
        result.put("alias", musicalWork.getAlias());
        result.put("title", musicalWork.getTitle());
        result.put("remarks", musicalWork.getRemarks());
        result.put("key", musicalWork.getKey());
        result.put("number", musicalWork.getNumber());
        result.put("opus", musicalWork.getOpus());
        result.put("catalogue", musicalWork.getCatalogue());
        result.put("isPartOf", musicalWork.getIsPartOf());
        result.put("composition", musicalWork.getComposition());
        result.put("documents", musicalWork.getDocuments());

        return result;
    }

    public Map<String, String> update(String composerId, String workId, Map<String, Object> workData) throws Exception {
        delete(composerId, workId);
        workData.put("URI", "http://dbtune.org/classical/resource/work/" + composerId + "/" + workId);
        Map<String, String> result = create(workData);
        result.put("message", "Work was updated successfully.");
        return result;
    }

    public Map<String, String> create(Map<String, Object> workData) throws Exception {
       return musicalWorkDA.createWork(workData);
    }

    public Map<String, String> delete(String composerId, String workId) throws Exception {
        return musicalWorkDA.deleteWork(composerId, workId);
    }

    public ArrayList<HashMap<String, String>> searchWork(String searchString) {
        return musicalWorkDA.searchWork(searchString);
    }
}
