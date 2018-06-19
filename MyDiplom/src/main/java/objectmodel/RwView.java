package objectmodel;


import dictionaries.ViewType;
import job.transform.types.RwRecord;
import utils.CollectionUtils;
import utils.NullArgumentException;

import java.util.Set;

public class RwView {
    private Integer rwViewId;
    private String viewName;
    private ViewType viewType;
    private String viewDescription;
    private String uniqueid;
    private Set<RwRecord> rwRecords;
    private RwDatabase rwDatabase;

    public Integer getRwViewId() {
        return rwViewId;
    }

    public void setRwViewId(Integer rwViewId) {
        this.rwViewId = rwViewId;
    }


    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }


    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }


    public String getViewDescription() {
        return viewDescription;
    }

    public void setViewDescription(String viewDescription) {
        this.viewDescription = viewDescription;
    }


    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }


    public Set<RwRecord> getRwRecords() {
        return rwRecords;
    }

    public void setRwRecords(Set<RwRecord> rwRecords) {
        this.rwRecords = rwRecords;
    }

    public RwDatabase getRwDatabase() {
        return rwDatabase;
    }

    public void setRwDatabase(RwDatabase rwDatabase) {
        this.rwDatabase = rwDatabase;
    }


    public RwRecord findRecordByName(String name) {
        if (name == null) {
            throw new NullArgumentException("name");
        }

        for (RwRecord record : CollectionUtils.safeCollection(getRwRecords())) {
            if (name.equals(record.getRecordName())) {
                return record;
            }
        }

        return null;
    }

    public RwRecord findRecordByUniqueId(String uniqueId) {
        if (uniqueId == null) {
            throw new NullArgumentException("uniqueId");
        }

        for (RwRecord record : CollectionUtils.safeCollection(getRwRecords())) {
            if (uniqueId.equals(record.getUniqueid())) {
                return record;
            }
        }

        return null;
    }


    public RwView copy() {
        RwView copy = new RwView();

        copy.setRwViewId(rwViewId);
        copy.setViewName(viewName);
        copy.setViewType(viewType);
        copy.setViewDescription(viewDescription);
        copy.setUniqueid(uniqueid);
        copy.setRwDatabase(rwDatabase);
        copy.setRwRecords(getRwRecords());

        return copy;
    }
}
