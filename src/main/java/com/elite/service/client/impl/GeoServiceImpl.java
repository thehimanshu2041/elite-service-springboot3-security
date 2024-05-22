package com.elite.service.client.impl;

import com.elite.model.client.GeoInfo;
import com.elite.model.client.IpInfo;
import com.elite.service.client.GeoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class GeoServiceImpl implements GeoService {

    @Value("${client.api.ip-info}")
    private String ipInfoUrl;

    @Value("${client.api.geo-info}")
    private String geoInfoUrl;

    @Override
    public GeoInfo getGeoInfo() {
        RestTemplate restTemplate = new RestTemplate();
        IpInfo ipInfo = restTemplate
                .getForObject(ipInfoUrl, IpInfo.class);
        return restTemplate
                .getForObject(geoInfoUrl.replace("ip-address", ipInfo.getIp()),
                        GeoInfo.class);
    }
}
