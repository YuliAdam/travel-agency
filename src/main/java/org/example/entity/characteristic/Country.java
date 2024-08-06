package org.example.entity.characteristic;

import org.example.utils.StringConstants;
import org.springframework.beans.factory.annotation.Value;

public enum Country {
    AU, EG, BY, RU, FR, IL, IT, USA;

    @Value("coordinates.by")
    private static String by;
    @Value("coordinates.au")
    private static String au;
    @Value("coordinates.eg")
    private static String eg;
    @Value("coordinates.ru")
    private static String ru;
    @Value("coordinates.fr")
    private static String fr;
    @Value("coordinates.il")
    private static String il;
    @Value("coordinates.it")
    private static String it;
    @Value("coordinates.usa")
    private static String usa;

    private static final String COORDINATE_PATTERN = "lat=%s&lon=%s";

    private static String getCoordinatesFormat(String countryCoordinates) {
        String[] coordinates = countryCoordinates.split(StringConstants.PIPE);
        return String.format(COORDINATE_PATTERN, coordinates[0], coordinates[1]);
    }

    public static String getCoordinate(Country country) {
        return switch (country) {
            case BY -> getCoordinatesFormat(by);
            case AU -> getCoordinatesFormat(au);
            case EG -> getCoordinatesFormat(eg);
            case FR -> getCoordinatesFormat(fr);
            case IL -> getCoordinatesFormat(il);
            case IT -> getCoordinatesFormat(it);
            case RU -> getCoordinatesFormat(ru);
            case USA -> getCoordinatesFormat(usa);
            default -> getCoordinatesFormat(it);
        };
    }
}
