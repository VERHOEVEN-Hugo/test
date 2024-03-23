package be.helha.dao.daoimpl;

public class Persistance {
    private static String MOCK;
    private static String DB;
    private String type;
    private String dao;
    private String url;
    private String user;
    private String password;

    public Persistance(String type, String dao, String url, String user, String password) {
        this.type = type;
        this.dao = dao;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public String getDao() {
        return dao;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
