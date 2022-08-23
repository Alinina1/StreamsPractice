package practice2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    static OktmoData d;
    @BeforeAll
    static void read() {
        d=new OktmoData();
        d.readFile("oktmo.csv");
    }
    @Test void test() {
        assertEquals(195766, d.getPlaces().size());
    }

    @Test
    void test2(){
        OktmoData d = new OktmoData();
        d.readFile("oktmo.csv");
//        System.out.println(d.getPlaces().size());
//        for(Place p: d.getPlaces()){
//            System.out.println(p.toString());
//        }

//        assertEquals("1, 512, 0, 146, п Калиновка", d.findPlace("п Калиновка").toString());
//
//        Place placeInzer = d.findPlace("Инзенский муниципальный район");
//        assertEquals("Инзенский муниципальный район", placeInzer.name);
//
//        System.out.println("Населенные пункты района Инзенский муниципальный район: \n");
//        d.findLocality(placeInzer).forEach(System.out::println);
//
//        Place placeChelyabinsk = d.findPlace("Муниципальные образования Челябинской области");
//        System.out.println("Населенные пункты региона Муниципальные образования Челябинской области: \n");
//        d.findLocalityByCode1(placeChelyabinsk).forEach(System.out::println);
//
//        d.findLocalityOVO().forEach(System.out::println);

//        System.out.println("Отсортированные неповторяюшиеся: \n");
//        d.getSortedDistinctLocations().forEach(System.out::println);


//        System.out.println(d.findTheLongestPlaceName().getName());
        d.getSortedByPopLocations().forEach(System.out::println);
    }
}
