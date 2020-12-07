
package eu.brain.iot.robot.tables.jsonReader;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class StorageTable {

    @SerializedName("Storage_Points")
    @Expose
    private List<StoragePoint> storagePoints = null;

    public List<StoragePoint> getStoragePoints() {
        return storagePoints;
    }

    public void setStoragePoints(List<StoragePoint> storagePoints) {
        this.storagePoints = storagePoints;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("storagePoints", storagePoints).toString();
    }

}
