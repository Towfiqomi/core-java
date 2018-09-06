/*
 *  Copyright (c) 2018 Jens Eliasson, Luleå University of Technology
 *
 *  This work is part of the Productive 4.0 innovation project, which receives grants from the
 *  European Commissions H2020 research and innovation programme, ECSEL Joint Undertaking
 *  (project no. 737459), the free state of Saxony, the German Federal Ministry of Education and
 *  national funding authorities from involved countries.
 */

package eu.arrowhead.core.datamanager;

import eu.arrowhead.common.ArrowheadMain;
import eu.arrowhead.common.misc.CoreSystem;
import java.util.HashSet;
import java.util.Set;

public class DataManagerMain extends ArrowheadMain {

  private DataManagerMain(String[] args) {
    Set<Class<?>> classes = new HashSet<>();
    String[] packages = {"eu.arrowhead.common.exception", "eu.arrowhead.common.json", "eu.arrowhead.common.filter"};
    init(CoreSystem.CHOREOGRAPHER, args, classes, packages);
    listenForInput();
  }

  public static void main(String[] args) {
    new DataManagerMain(args);
  }

}
