package lexoperm;

import java.util.LinkedList;
import java.util.Queue;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiUnavailableException;
import lexoperm.PermutationsLex_thread_queue2.perm;
import lexoperm.PermutationsLex_thread_queue2.cubes;
import org.jfugue.DeviceThatWillReceiveMidi;
import org.jfugue.Pattern;

public class lexoplayer2 {

    public static DeviceThatWillReceiveMidi device = null;
    public static Queue<Pattern> pattern_queue = new LinkedList<Pattern>();
    public static void main(String[] args) {  
        MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
        for (int i=0;i<devices.length;i++) {
            System.out.println("name: " + devices[i].getName() + "    Description: " + devices[i].getDescription());
            if (devices[i].getName().equals("Out To MIDI Yoke:  1") ) {
		try {
                    device = new DeviceThatWillReceiveMidi(devices[i]);
                    System.out.println("Assigned Midi Yoke");
                } catch (MidiUnavailableException e) {
		}  
            }
        }
        
        perm P = new perm();
        cubes cube = new cubes();
	P.start();
        cube.start();

    }

}
