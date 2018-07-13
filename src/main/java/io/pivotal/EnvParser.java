package io.pivotal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EnvParser {

    private final static Logger logger = LoggerFactory.getLogger(EnvParser.class);

    private static EnvParser instance;

    private EnvParser() {
    }

    public static EnvParser getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (EnvParser.class) {
            if (instance == null) {
                instance = new EnvParser();
            }
        }
        return instance;
    }

    public String getUsername() throws IOException {
        Map credentials = getCredentials();
        return (String) credentials.get("username");
    }

    public String getPasssword() throws IOException {
        Map credentials = getCredentials();
        return (String) credentials.get("password");
    }
    
    public String getUrl() throws IOException {
        Map credentials = getCredentials();
        return (String) credentials.get("url");
    }
    
    private Map getCredentials() throws IOException {
        Map credentials = null;
        String envContent = System.getenv().get("VCAP_SERVICES");
        ObjectMapper objectMapper = new ObjectMapper();
        Map services = objectMapper.readValue(envContent, Map.class);
        List gemfireService = getUserProvideService(services);
        if (gemfireService != null) {
            Map serviceInstance = (Map) gemfireService.get(0);
            credentials = (Map) serviceInstance.get("credentials");
        }
        return credentials;
    }
    
    private List getUserProvideService(Map services) {
        List l = (List) services.get("user-provided");
        if (l == null) {
            throw new IllegalStateException(
                    "User Provide Service is not bound to this application");
        }
        return l;
    }
}
