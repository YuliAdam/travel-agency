package org.example.client;

import org.example.entity.characteristic.Country;
import org.example.utils.StringConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalClient {

    @Value("foo.url")
    String fooUrl;

    @Value("foo.host")
    String fooHost;

    @Value("foo.key")
    String fooKey;

    private static final String TEMP_C_DELIMITER = "temp\":";

    private static final String[] WHETHER_DESCRIPTION_DELIMITER = new String[]{"\",\"code", "description\":\""};

    public String fooMadeCall(Country country) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format(fooUrl, Country.getCoordinate(country));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(fooHost.split(StringConstants.PIPE)[0], fooHost.split(StringConstants.PIPE)[1]);
        httpHeaders.add(fooKey.split(StringConstants.PIPE)[0], fooKey.split(StringConstants.PIPE)[1]);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class).getBody();
    }

    public List<String> weatherNow(Country country) {
        Double tempF = Double.parseDouble((Arrays.stream(fooMadeCall(country).split(TEMP_C_DELIMITER, 2)).toList())
                .get(1).substring(0, 4));
        String weatherDescription = (Arrays.stream((Arrays.stream(fooMadeCall(country).split(WHETHER_DESCRIPTION_DELIMITER[0], 2)).toList())
                .get(0).split(WHETHER_DESCRIPTION_DELIMITER[1])).toList()).getLast();
        return List.of(convertToCelsius(tempF).toString(), weatherDescription);
    }

    private Integer convertToCelsius(Double tempF) {
        return (int) (tempF - 32) * 5 / 9;
    }


}
