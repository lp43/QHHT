package com.qhhtofficial.qhht.module;


import com.qhhtofficial.qhht.R;

import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

  public static List<Group> makeGroups() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre(),
        makeSalsaGenre(),
        makeBluegrassGenre());
  }


  public static Group makeRockGenre() {
    return new Group("Rock", makeRockArtists(), R.drawable.ic_electric_guitar);
  }


  public static List<Content> makeRockArtists() {
    Content queen = new Content("Queen", "", true);
    Content styx = new Content("Styx", "", false);
    Content reoSpeedwagon = new Content("REO Speedwagon", "", false);
    Content boston = new Content("Boston", "", true);

    return Arrays.asList(queen, styx, reoSpeedwagon, boston);
  }

  public static Group makeJazzGenre() {
    return new Group("Jazz", makeJazzArtists(), R.drawable.ic_saxaphone);
  }


  public static List<Content> makeJazzArtists() {
    Content milesDavis = new Content("Miles Davis", "", true);
    Content ellaFitzgerald = new Content("Ella Fitzgerald", "", true);
    Content billieHoliday = new Content("Billie Holiday", "", false);

    return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
  }

  public static Group makeClassicGenre() {
    return new Group("Classic", makeClassicArtists(), R.drawable.ic_violin);
  }


  public static List<Content> makeClassicArtists() {
    Content beethoven = new Content("Ludwig van Beethoven", "", false);
    Content bach = new Content("Johann Sebastian Bach", "", true);
    Content brahms = new Content("Johannes Brahms", "", false);
    Content puccini = new Content("Giacomo Puccini", "", false);

    return Arrays.asList(beethoven, bach, brahms, puccini);
  }

  public static Group makeSalsaGenre() {
    return new Group("Salsa", makeSalsaArtists(), R.drawable.ic_maracas);
  }


  public static List<Content> makeSalsaArtists() {
    Content hectorLavoe = new Content("Hector Lavoe", "", true);
    Content celiaCruz = new Content("Celia Cruz", "", false);
    Content willieColon = new Content("Willie Colon", "", false);
    Content marcAnthony = new Content("Marc Anthony", "", false);

    return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
  }

  public static Group makeBluegrassGenre() {
    return new Group("Bluegrass", makeBluegrassArtists(), R.drawable.ic_banjo);
  }


  public static List<Content> makeBluegrassArtists() {
    Content billMonroe = new Content("Bill Monroe", "", false);
    Content earlScruggs = new Content("Earl Scruggs", "", false);
    Content osborneBrothers = new Content("Osborne Brothers", "", true);
    Content johnHartford = new Content("John Hartford", "", false);

    return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
  }

}

