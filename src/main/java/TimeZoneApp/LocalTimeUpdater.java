package TimeZoneApp;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LocalTimeUpdater extends Thread{
    private JLabel label;

    public LocalTimeUpdater(JLabel label){
        this.label=label;
    }

    @Override
    public void run(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(true){
            sdf.setTimeZone(TimeZone.getDefault());
            String localTime=sdf.format(new Date());
            label.setText("Your local time is "+localTime+" ("+TimeZone.getDefault().getID()+")");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
