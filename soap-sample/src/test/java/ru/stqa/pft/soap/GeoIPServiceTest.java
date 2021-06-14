package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static java.nio.file.Paths.get;
import static org.testng.Assert.assertEquals;

public class GeoIPServiceTest {

    @Test
    public void testMyIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("176.115.151.160");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>51</State></GeoIP>");
    }
}
