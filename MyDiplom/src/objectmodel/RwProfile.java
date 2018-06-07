package objectmodel;

import utils.CollectionUtils;
import utils.NullArgumentException;

import java.util.Set;

public class RwProfile {
    private Integer rwProfileId;
    private String profileName;
    private String profileDescription;
    private String uniqueid;
    private Set<RwTransform> rwTransforms;


    public Integer getRwProfileId() {
        return rwProfileId;
    }

    public void setRwProfileId(Integer rwProfileId) {
        this.rwProfileId = rwProfileId;
    }


    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }


    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }


    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }


    public Set<RwTransform> getRwTransforms() {
        return rwTransforms;
    }

    public void setRwTransforms(Set<RwTransform> rwTransforms) {
        this.rwTransforms = rwTransforms;
    }


    @Override
    public String toString() {
        return "Profile [name = " + profileName + "]";
    }

    public RwTransform findTransformByName(String name) {
        if (name == null) {
            throw new NullArgumentException("name");
        }

        for (RwTransform transform : CollectionUtils.safeCollection(rwTransforms)) {
            if (name.equals(transform.getTransformName())) {
                return transform;
            }
        }

        return null;
    }

    public RwTransform findTransformByUniqueId(String uniqueId) {
        if (uniqueId == null) {
            throw new NullArgumentException("uniqueId");
        }

        for (RwTransform transform : CollectionUtils.safeCollection(rwTransforms)) {
            if (uniqueId.equals(transform.getUniqueid())) {
                return transform;
            }
        }

        return null;
    }
}

