package mt.com;

public class Credentials {

    private String websiteUserName;
    private String websitePassword;

    public Credentials(String websiteUserName, String websitePassword) {
        this.setWebsiteUserName(websiteUserName);
        this.setWebsitePassword(websitePassword);
    }

    public String getWebsiteUserName() {
        return websiteUserName;
    }

    public void setWebsiteUserName(String websiteUserName) {
        this.websiteUserName = websiteUserName;
    }

    public String getWebsitePassword() {
        return websitePassword;
    }

    public void setWebsitePassword(String websitePassword) {
        this.websitePassword = websitePassword;
    }
}
