package ca.mcmaster.se2aa4.island.team201;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Report {
    Interpreter interpreter;
    private final Logger logger = LogManager.getLogger();

    public Report(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    private String closestInlet() {
        Scan[] creekScans = interpreter.getCreeks();
        Scan site = interpreter.getSite();
        if (creekScans.length == 0) {
            return "No inlet found";
        }

        if (site != null) {
            double minDistance = 100000;
            int minIdx = 0;
            for (int i = 0; i < creekScans.length; i++) {
                int deltaX = creekScans[i].location().x() - site.location().x();
                int deltaY = creekScans[i].location().y() - site.location().y();
                int dxs = deltaX * deltaX;
                int dxy = deltaY * deltaY;
                double t1 = (double) dxs;
                double t2 = (double) dxy;
                double distance = Math.sqrt(t1 + t2);
                if (distance < minDistance) {
                    minDistance = distance;
                    minIdx = i;
                    
                //logger.info("{} {}", creekScans[i].location().x(), creekScans[i].location().y());
                }

            }
            //logger.info("the site is {} {}", site.location().x(), site.location().y());
            String closestCreek = creekScans[minIdx].creeks()[0];
            return closestCreek;
        } else {
            Scan firstCreekScan = creekScans[0];
            String creek = firstCreekScan.creeks()[0];
            return creek;
        }

    }

    public String getReport() {

        return "The closest inlet is " + closestInlet();
    }
}
