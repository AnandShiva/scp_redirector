package redirector.model;

public class RedirectRequestDTO{
    // expected to begin with "/"
    private String subPath;
    private String destinationName;

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}