package com.pes.takemelegends.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CategoriesParser {

    private static final Map<String, String> categories;
    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("music", "Música");
        aMap.put("conference", "Conferencias");
        aMap.put("comedy", "Comedia");
        aMap.put("learning_education", "Educación");
        aMap.put("family_fun_kids", "Familia");
        aMap.put("festivals_parades", "Festivales");
        aMap.put("movies_film", "Cine");
        aMap.put("food", "Gastronomía");
        aMap.put("fundraisers", "Recaudación");
        aMap.put("art", "Arte");
        aMap.put("support", "Salud");
        aMap.put("holiday", "Vacaciones");
        aMap.put("books", "Lectura");
        aMap.put("attractions", "Museos");
        aMap.put("community", "Comunidad");
        aMap.put("business", "Negocio");
        aMap.put("singles_social", "Nightlife");
        aMap.put("schools_alumni", "Universidad");
        aMap.put("outdoors_recreation", "Aire libre");
        aMap.put("performing_arts", "Artes escénicas");
        aMap.put("animals", "Animales");
        aMap.put("politics_activism", "Política");
        aMap.put("sales", "Ventas");
        aMap.put("science", "Ciencia");
        aMap.put("religion_spirituality", "Religión");
        aMap.put("sports", "Deportes");
        aMap.put("technology", "Tecnología");
        aMap.put("others", "Otros");
        categories = Collections.unmodifiableMap(aMap);
    }

    public static String parseCategory(String category) {
        return categories.get(category);
    }
}
