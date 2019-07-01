package redirector;

import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentVariables{
    public String authServiceUrl;
    public String destinationServiceUrl;
    public String destinationClientId;
    public String destinationClientSecret;
    public String connectivityClientId;
    public String connectivityClientSecret;
    public String connectivityOnPremProxyHost;
    public String connectivityOnPremProxyPort;
    public EnvironmentVariables(){
        String vcap_string = "{\"VCAP_SERVICES\":{\"connectivity\":[{\"binding_name\":null,\"credentials\":{\"clientid\":\"sb-clone42631761bb834d6c810745592b6e2fb3!b6037|connectivity!b137\",\"clientsecret\":\"e9GriV0sW1GWICW4n8Ad/YgQxFU=\",\"identityzone\":\"i332878trial\",\"onpremise_proxy_host\":\"connectivityproxy.internal.cf.sap.hana.ondemand.com\",\"onpremise_proxy_ldap_port\":\"20001\",\"onpremise_proxy_port\":\"20003\",\"onpremise_proxy_rfc_port\":\"20001\",\"onpremise_socks5_proxy_port\":\"20004\",\"tenantid\":\"581ec425-9e01-4b7d-9d2b-b098b5610bec\",\"tenantmode\":\"dedicated\",\"uaadomain\":\"authentication.sap.hana.ondemand.com\",\"url\":\"https://i332878trial.authentication.sap.hana.ondemand.com\",\"verificationkey\":\"-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx/jN5v1mp/TVn9nTQoYVIUfCsUDHa3UN2PnwruzyS7w1Jd+StqwW4/vn87ua2YlZzU8Ob0jR4lbOPCKaHIi0kyNtJXQvQ7LZPG8epQLbx0IIP/WLVVVtB8bL5OWuHma3pUnibbmATtbOh5LksQ2zLMngEjUF5p0BNe/drlAqO253keiY63FL6belKjJGmSqdnotSXxB2ym+HQ0ShaNvTFLEvi2+ObkyjGWgFpQaoCcGq0KX0y0mPzOvdFsNT+rBFdkHiK+Jl638Sbim1z9fItFbH9hiKi3PuATMjf/DJ7mUluDQIDAQAB-----END PUBLIC KEY-----\",\"xsappname\":\"clone42631761bb834d6c810745592b6e2fb3!b6037|connectivity!b137\"},\"instance_name\":\"connectivity-demo-lite\",\"label\":\"connectivity\",\"name\":\"connectivity-demo-lite\",\"plan\":\"lite\",\"provider\":null,\"syslog_drain_url\":null,\"tags\":[\"connectivity\",\"conn\",\"connsvc\"],\"volume_mounts\":[]}],\"destination\":[{\"binding_name\":null,\"credentials\":{\"clientid\":\"sb-clone889a10d6bf704c69bd61afe7c9adbb78!b6037|destination-xsappname!b433\",\"clientsecret\":\"9wfsV7MtKwKjGXayumt1//zg6o0=\",\"identityzone\":\"i332878trial\",\"instanceid\":\"889a10d6-bf70-4c69-bd61-afe7c9adbb78\",\"tenantid\":\"581ec425-9e01-4b7d-9d2b-b098b5610bec\",\"tenantmode\":\"dedicated\",\"uaadomain\":\"authentication.sap.hana.ondemand.com\",\"uri\":\"https://destination-configuration.cfapps.sap.hana.ondemand.com\",\"url\":\"https://i332878trial.authentication.sap.hana.ondemand.com\",\"verificationkey\":\"-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx/jN5v1mp/TVn9nTQoYVIUfCsUDHa3UN2PnwruzyS7w1Jd+StqwW4/vn87ua2YlZzU8Ob0jR4lbOPCKaHIi0kyNtJXQvQ7LZPG8epQLbx0IIP/WLVVVtB8bL5OWuHma3pUnibbmATtbOh5LksQ2zLMngEjUF5p0BNe/drlAqO253keiY63FL6belKjJGmSqdnotSXxB2ym+HQ0ShaNvTFLEvi2+ObkyjGWgFpQaoCcGq0KX0y0mPzOvdFsNT+rBFdkHiK+Jl638Sbim1z9fItFbH9hiKi3PuATMjf/DJ7mUluDQIDAQAB-----END PUBLIC KEY-----\",\"xsappname\":\"clone889a10d6bf704c69bd61afe7c9adbb78!b6037|destination-xsappname!b433\"},\"instance_name\":\"destination-demo-lite\",\"label\":\"destination\",\"name\":\"destination-demo-lite\",\"plan\":\"lite\",\"provider\":null,\"syslog_drain_url\":null,\"tags\":[\"destination\",\"conn\",\"connsvc\"],\"volume_mounts\":[]}],\"xsuaa\":[{\"binding_name\":null,\"credentials\":{\"clientid\":\"sb-ViewBrowserSCP!t6037\",\"clientsecret\":\"ROp/t/7veUJCfMf2MdHldJlhLus=\",\"identityzone\":\"i332878trial\",\"identityzoneid\":\"581ec425-9e01-4b7d-9d2b-b098b5610bec\",\"sburl\":\"https://internal-xsuaa.authentication.sap.hana.ondemand.com\",\"tenantid\":\"581ec425-9e01-4b7d-9d2b-b098b5610bec\",\"tenantmode\":\"dedicated\",\"uaadomain\":\"authentication.sap.hana.ondemand.com\",\"url\":\"https://i332878trial.authentication.sap.hana.ondemand.com\",\"verificationkey\":\"-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx/jN5v1mp/TVn9nTQoYVIUfCsUDHa3UN2PnwruzyS7w1Jd+StqwW4/vn87ua2YlZzU8Ob0jR4lbOPCKaHIi0kyNtJXQvQ7LZPG8epQLbx0IIP/WLVVVtB8bL5OWuHma3pUnibbmATtbOh5LksQ2zLMngEjUF5p0BNe/drlAqO253keiY63FL6belKjJGmSqdnotSXxB2ym+HQ0ShaNvTFLEvi2+ObkyjGWgFpQaoCcGq0KX0y0mPzOvdFsNT+rBFdkHiK+Jl638Sbim1z9fItFbH9hiKi3PuATMjf/DJ7mUluDQIDAQAB-----END PUBLIC KEY-----\",\"xsappname\":\"ViewBrowserSCP!t6037\"},\"instance_name\":\"xsuaa-demo\",\"label\":\"xsuaa\",\"name\":\"xsuaa-demo\",\"plan\":\"application\",\"provider\":null,\"syslog_drain_url\":null,\"tags\":[\"xsuaa\"],\"volume_mounts\":[]}]}}";
        //JSONObject VCAP_SERVICES =  new JSONObject(System.getenv("VCAP_SERVICES"));
        JSONObject VCAP_SERVICES =  new JSONObject(vcap_string).getJSONObject("VCAP_SERVICES");
        //this.authServiceUrl = VCAP_SERVICES.toString();
        JSONObject destination = VCAP_SERVICES.getJSONArray("destination").getJSONObject(0);
        JSONObject connectivity = VCAP_SERVICES.getJSONArray("connectivity").getJSONObject(0);
        JSONObject xsuaa = VCAP_SERVICES.getJSONArray("xsuaa").getJSONObject(0);
        JSONObject destCredentials = destination.getJSONObject("credentials");
        JSONObject connectivityCredentials = connectivity.getJSONObject("credentials");

        JSONObject xsuaaCredentials = xsuaa.getJSONObject("credentials");
        destinationServiceUrl = destCredentials.getString("uri");
        authServiceUrl = xsuaaCredentials.getString("url");
        destinationClientId = destCredentials.getString("clientid");
        destinationClientSecret = destCredentials.getString("clientsecret");
        connectivityClientId = connectivityCredentials.getString("clientid");
        connectivityClientSecret = connectivityCredentials.getString("clientsecret");
        connectivityOnPremProxyHost = connectivityCredentials.getString("onpremise_proxy_host");
        connectivityOnPremProxyPort = connectivityCredentials.getString("onpremise_proxy_port");

    }
}