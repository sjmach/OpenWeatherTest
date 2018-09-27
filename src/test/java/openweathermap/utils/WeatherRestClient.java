package openweathermap.utils;

import net.aksingh.owmjapis.core.OWM;

public class WeatherRestClient {
    private static volatile WeatherRestClient instance = null;
    public OWM owmclient;

    // private constructor
    private WeatherRestClient() {
        owmclient = new OWM("db74a96e3e9ef8893eaf1f91bff4eeef");
    }

    public static WeatherRestClient getInstance() {
        if (instance == null) {
            synchronized (WeatherRestClient.class) {
                // Double check
                if (instance == null) {
                    instance = new WeatherRestClient();
                }
            }
        }
        return instance;
    }
}