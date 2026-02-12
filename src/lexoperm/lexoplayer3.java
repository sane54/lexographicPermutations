package lexoperm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import static lexoperm.PermutationsLex_thread_queue3.hasNext;
import org.jfugue.DeviceThatWillReceiveMidi;
import org.jfugue.Instrument;
import org.jfugue.Note;
import org.jfugue.Pattern;
import org.jfugue.Player;
import org.jfugue.Tempo;

public class lexoplayer3 {

    public static DeviceThatWillReceiveMidi device = null;
    public static Queue<Pattern> pattern_queue = new LinkedList<Pattern>();
    
    
    
    public static void main(String[] args) {  
        MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info device1 : devices) {
            System.out.println("name: " + device1.getName() + "    Description: " + device1.getDescription());
            if (device1.getName().equals("Out To MIDI Yoke:  1")) {
                try {
                    device = new DeviceThatWillReceiveMidi(device1);
                    System.out.println("Assigned Midi Yoke");
                }catch (MidiUnavailableException e) {
                }  
            }
        }
        int side_limit = 12;
        int[] a  = new int[side_limit];
        byte Rbyte;
        int indec;
        int offset;
        Random generator = new Random();
        Player player = new Player();
        for (int i = 0; i < side_limit; i++)
            a[i] = i;
        while (hasNext(a)) {
            for (int i = 0; i < side_limit; i++) {
                System.out.print(a[i] + ", ");
            }
            System.out.println();
            PolygonDrawing myp = new PolygonDrawing(a);
            myp.setVisible(true);
            Pattern jpattern = new Pattern();
            jpattern.addElement(new Tempo(440));
            byte instbyte = 71;
            Note Rnote;
            jpattern.addElement(new Instrument(instbyte));
            //jpattern.add("Rw");
            for (int i = 0; i < 12; i++) {
                offset = (generator.nextInt( 4 ) - 4)*12;
                indec = a[i] + 84 + offset;
                //DEBUG
                //System.out.println (indec);
                Rbyte = (byte)indec;

                if (i == 11) {
                    //System.out.println("extra length for " + i);
                    Rnote = new Note(Rbyte, 2.5);
                }
                else {
                    Rnote = new Note(Rbyte, 0.25);                        
                }
                jpattern.addElement(Rnote);
                //DEBUG
                //System.out.println (jpattern.getMusicString());
            }//end for
            jpattern.add("Rwww");
            player.play(jpattern);
            myp.dispose();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(lexoplayer3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //end while

    }

}
