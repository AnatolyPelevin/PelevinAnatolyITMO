package objectmodel;


import utils.CollectionUtils;
import utils.NullArgumentException;

import java.util.Set;

public class RwDatabase {
    private Integer rwDatabaseId;
    private String rwdatabaseName;
    private String databaseDescription;
    private String databaseDriver;
    private String databaseUrl;
    private String databaseUser;
    private String databasePassword;
    private String uniqueid;
    private Set<RwView> rwViews;


    public Integer getRwDatabaseId() {
        return rwDatabaseId;
    }

    public void setRwDatabaseId(Integer rwDatabaseId) {
        this.rwDatabaseId = rwDatabaseId;
    }


    public String getRwdatabaseName() {
        return rwdatabaseName;
    }

    public void setRwdatabaseName(String rwdatabaseName) {
        this.rwdatabaseName = rwdatabaseName;
    }


    public String getDatabaseDescription() {
        return databaseDescription;
    }

    public void setDatabaseDescription(String databaseDescription) {
        this.databaseDescription = databaseDescription;
    }


    public String getDatabaseDriver() {
        return databaseDriver;
    }

    public void setDatabaseDriver(String databaseDriver) {
        this.databaseDriver = databaseDriver;
    }


    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }


    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }


    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }


    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }


    public Set<RwView> getRwViews() {
        return rwViews;
    }

    public void setRwViews(Set<RwView> rwViews) {
        this.rwViews = rwViews;
    }

    @Override
    public String toString() {
        return "Database [name = " + rwdatabaseName + "]";
    }

    public RwView findViewByName(String name) {
        if (name == null) {
            throw new NullArgumentException("name");
        }

        name = name.trim();

        for (RwView view : CollectionUtils.safeCollection(getRwViews())) {
            if (name.equalsIgnoreCase(view.getViewName().trim())) {
                return view;
            }
        }

        return null;
    }

    public RwView findViewByUniqueId(String uniqueId) {
        if (uniqueId == null) {
            throw new NullArgumentException("uniqueId");
        }

        for (RwView view : CollectionUtils.safeCollection(getRwViews())) {
            if (uniqueId.equals(view.getUniqueid())) {
                return view;
            }
        }

        return null;
    }


    public RwDatabase copy() {
        RwDatabase copy = new RwDatabase();

        copy.setRwDatabaseId(rwDatabaseId);
        copy.setDatabaseUrl(databaseUrl);
        copy.setDatabaseUser(databaseUser);
        copy.setDatabasePassword(databasePassword);
        copy.setDatabaseDriver(databaseDriver);
        copy.setDatabaseDescription(databaseDescription);
        copy.setRwdatabaseName(rwdatabaseName);
        copy.setUniqueid(uniqueid);

        return copy;
    }
}
