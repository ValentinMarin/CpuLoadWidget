/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CpuLoadWidget;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;

/**
 *
 * @author marin
 */
public class ProgressThread extends Thread{
    
    double cpu_load;
    Arc locarArc;
    Label locarPercent;

    ProgressThread(Arc arc_progress, Label percent) {
        this.locarArc = arc_progress;
        this.locarPercent = percent;
    }
    
    public double GetCpuLoad() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                        OperatingSystemMXBean.class);
        
        // What % load the overall system is at, from 0.0-1.0
        cpu_load = osBean.getSystemCpuLoad();
        
        return cpu_load;
    }
    
    @Override
    public void run(){
        
        while(true){
            // Sleep for a while
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Interrupted exception will occur if
                // the Worker object's interrupt() method
                // is called. interrupt() is inherited
                // from the Thread class.
                break;
            }
            
            double cpuRow = GetCpuLoad();
            int cpuInt = (int)(cpuRow*100);
            
            Platform.runLater(()->(
                locarArc.setLength(cpuRow*360)
            ));
            Platform.runLater(()->(
                locarPercent.setText(String.valueOf(cpuInt))
            ));
        }
    }
    
}
