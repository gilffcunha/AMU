package com.example.lightmonitor;

public class ConnectionDB
{
    private String url;
    private String username;
    private String password;


    public ConnectionDB(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ConnectionDB()
    {
        this.url = "jdbc:mysql://localhost:3306/luxdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; // set your own url
        this.username = "root"; // set your own username
        this.password = "n1554554"; // set your own password
     }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
