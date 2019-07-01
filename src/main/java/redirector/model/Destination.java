package redirector.model;

import com.google.gson.annotations.SerializedName;

public class Destination {
    public String Name; 
    public String Type; 
    public String URL; 
    public String Authentication; 
    public String ProxyType;
    public String User;
    public String Password;
    @SerializedName("sap-client")
    public String sysClient;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public String getAuthentication() {
        return Authentication;
    }

    public void setAuthentication(String authentication) {
        Authentication = authentication;
    }

    public String getProxyType() {
        return ProxyType;
    }

    public void setProxyType(String proxyType) {
        ProxyType = proxyType;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSysClient() {
        return sysClient;
    }

    public void setSysClient(String sysClient) {
        this.sysClient = sysClient;
    }

}