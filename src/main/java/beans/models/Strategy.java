package beans.models;

import java.util.Map;

public class Strategy {

    private Map<String, String> volumesMap;

    public Strategy(Map<String, String> volumesMap) {
        this.volumesMap = volumesMap;
    }

    public Map<String, String> getVolumesMap() {
        return volumesMap;
    }

    public void setVolumesMap(Map<String, String> volumesMap) {
        this.volumesMap = volumesMap;
    }
}
