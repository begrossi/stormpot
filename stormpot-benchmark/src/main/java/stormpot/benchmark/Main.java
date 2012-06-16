package stormpot.benchmark;

import java.util.Properties;

public class Main {
  public static void main(String[] args) throws Exception {
    /*
     * NOTE:
     * Remember to add pass-through properties to the pom.xml file,
     * when adding properties here!
     */
    Benchmark benchmark = null;
    if (prop("help")) {
      System.out.print(
          "#######################################\n" +
          "# How to use the benchmark tool:\n");
      explain("throughput-single", "Single-threaded throughput");
      System.out.print("# Additionally, the following options may be given " +
      		"to any benchmark:\n");
      explainAuxiliary("clock.precise",
          "Measure latency more precisely, at the cost of reducing througput.");
      System.out.print(
          "#######################################\n");
      return;
    }
    if (prop("throughput-single")) {
      benchmark = new SingleThreadedThroughput();
    }
    
    if (benchmark != null) {
      benchmark.run();
    } else {
      System.out.print(
          "#######################################\n" +
          "#### Run `mvn -Phelp` for options. ####\n" +
          "#######################################\n");
    }
    System.exit(0);
  }

  private static boolean prop(String prop) {
    Properties props = System.getProperties();
    return Boolean.parseBoolean(props.getProperty(prop, "false"));
  }

  private static void explain(String name, String desc) {
    System.out.printf("#   Run `mvn -D%s` for a %s benchmark.\n", name, desc);
  }

  private static void explainAuxiliary(String name, String desc) {
    System.out.printf("#   -D%s\n#\t%s\n", name, desc);
  }
}