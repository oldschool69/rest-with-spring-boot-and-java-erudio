package br.com.oldschool69.rest_with_spring_boot_and_java.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InstanceInformationService {

    private static final String HOST_NAME = "HOST_NAME";

    private static final String DEFAULT_ENV_INSTANCE_GUID = "LOCAL";

    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    public String retrieveInstanceInfo() {
        return hostName.substring(hostName.length()-5);
    }
}
