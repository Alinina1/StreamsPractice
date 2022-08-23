package practice2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OktmoData {
    private List<Place> places = new ArrayList<>();

    public void readFile(String filename) {
        places.clear();
        Path p = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(p, Charset.forName("cp1251"));
            for (String s: lines) {
                readLine(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//"01";"512";"000";"146";"9";"2";"п Калиновка";;;"493";"3";12.08.2021;01.01.2022
   private static final Pattern RE=Pattern.compile("\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d\";\"\\d\";)\"([а-я]+)\\s([a-zA-Zа-яА-Я\\s-]+)\"");
    private void readLine(String s) {
        Matcher m = RE.matcher(s);
        if (m.find()) {
            places.add(new Place(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)),
                    Integer.parseInt(m.group(4)), m.group(7), m.group(6)
                    ));
        }
    }

    public Place findPlace(String name){
        return places.stream().filter(x-> x.name.equals(name)).findAny().get();
    }

    public Stream<Place> findLocality(Place place){
        return places.stream().filter(x->x.code1==place.code1 && x.code2==place.code2 && x.code3 != 0 && x.code4!=0);
    }

    public Stream<Place> findLocalityByCode1(Place place){
        return places.stream().filter(x->x.code1==place.code1 && x.code4!=0);
    }

    public Stream<Place> findLocalityOVO(){
        return places.stream().filter(x->x.name.endsWith("ово") || x.name.contains("-"));

    }

    public Stream<Place> getSortedDistinctLocations(){
        return places.stream().filter(distinctByKey(Place::getName)).sorted(new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return (o1.name).compareTo(o2.name);
            }
        });
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public Place findTheLongestPlaceName(){
        return places.stream().filter(x->x.code4!=0).max(Comparator.comparing(p -> p.getName().length())).orElse(null);
//                new Comparator<Place>() {
//            @Override
//            public int compare(Place o1, Place o2) {
//                if(o1.name.length() > o2.name.length()){
//                    return 1;
//                }
//                else if(o1.name.length() < o2.name.length()){
//                    return -1;
//                }
//                else return 0;
//            }
//        }).get();
    }

    public Stream<Map.Entry<String, Long>> getSortedByPopLocations(){
        return places.stream().collect(Collectors.groupingBy(Place::getName, Collectors.counting())).
                entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue));//)
    }

    public List<Place> getPlaces() {
        return places;
    }
    //(x.name).equals(name)
}
