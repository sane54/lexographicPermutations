package lexoperm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import java.util.Random;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Receiver;

import org.jfugue.*;


public class PermutationsLex_thread_queue {


//public static boolean first_time = true;
public static Queue<Pattern> pattern_queue = new LinkedList<Pattern>();


public static Player pattern_player(Pattern pat, Player plyr ) {
	plyr.play(pat);
	Player pattern_player = new Player();
	return pattern_player;
}

public static void show(int[] a) {
    for (int i = 0; i < a.length; i++)
        System.out.printf("%d ", a[i]);
    System.out.printf("\n");
} 

public static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

public static boolean hasNext(int[] a) {
    int N = a.length;
    

    // find rightmost element a[k] that is smaller than element to its right
    int k; 
    for (k = N-2; k >= 0; k--)
        if (a[k] < a[k+1]) break;
    if (k == -1) return false;

    // find rightmost element a[j] that is larger than a[k]
    int j = N-1;
    while (a[k] > a[j])
        j--;
    swap(a, j, k);

    for (int r = N-1, s = k+1; r > s; r--, s++)
        swap(a, r, s);

    return true;
}

static class perm implements Runnable {

public perm(){
	
}
public void start ()
{
 //  System.out.println("Starting permutations " );
   if (tperm == null)
   {
      tperm = new Thread (this);
      tperm.start ();
   }
}
private Thread tperm;
public void run() {
    // initialize permutation
    int[] a  = new int[12];
    byte Rbyte;
    int indec = 0;
    int offset = 0;
    Random generator = new Random();
    //Debug
    //Player player = new Player();
    for (int i = 0; i < 12; i++)
        a[i] = i;
    // print permutations
    //show(a);
    //Player player = new Player();
    while (hasNext(a)) {
     //  show(a);
      Pattern jpattern = new Pattern();
     jpattern.addElement(new Tempo(480));
   	//jpattern.addElement(new Instrument(Instrument.Tubular_Bells));
    for (int i = 0; i < 12; i++) {
    	offset = (generator.nextInt( 4 ) - 4)*12;
        indec = a[i] + 84 + offset;
        //DEBUG
    	//System.out.println (indec);
    	Rbyte = (byte)indec;
    	Note Rnote = new Note(Rbyte, 0.25);
    	jpattern.addElement(Rnote);
    	//DEBUG
    	//System.out.println (jpattern.getMusicString());
    }//end for
    pattern_queue.add(jpattern);
    
    try {
		Thread.sleep(1205);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    } //end while
}

}

static class playpattern1 implements Runnable{
	private Thread t1;
	Player player;
	public void run() {
	//while (true){
	Timer timer = new Timer();
	//System.out.println(" playpattern 1 starting timer");
	timer.schedule(new Player_Task1(), 1500);
	//System.out.println("Creating playpattern1 player");
	player = new Player();
	//when timer expires
	//}
    }
	   public void start ()
	   {
	     // System.out.println("Starting Playpattern1 " );
	      if (t1 == null)
	      {
	         t1 = new Thread (this);
	         t1.start ();
	      }
	   }
    class Player_Task1 extends TimerTask {
    	public void run () {
    		//System.out.println("calling pattern2");
    		playpattern2 PP2 = new playpattern2();
    		PP2.start();
    		//System.out.println("playpattern1 taking pattern from queue");
    	     // player.play(pattern_queue.remove());
              //Sequence sequence = player.getSequencer().getSequence();
             Sequence sequence = player.getSequence(pattern_queue.remove());
             Sequencer playerSeqencer = player.getSequencer();
            //  playerSeqencer.setTempoInBPM(120);
            System.out.println("sequencer tempo: " + playerSeqencer.getTempoInBPM());
            System.out.println("seqence length: " + sequence.getMicrosecondLength());
            System.out.println("seqence resolution: " + sequence.getResolution());
            System.out.println("seqence division type: " + sequence.getDivisionType());
            lexoperm.lexoplayer2.device.sendSequence(sequence);
              player.close();
        }       
    	
    }
}


static class playpattern2 implements Runnable{
	private Thread t2;
	Player player;
	public void run() {
	//	while (true) {
	Timer timer = new Timer();
	//System.out.println("playpattern2 starting timer");
    timer.schedule(new Player_Task2(), 1500);
	player = new Player();
	//when timer expires
	//	}
    }
	   public void start ()
	   {
	     // System.out.println("Starting Playpattern2 " );
	      if (t2 == null)
	      {
	         t2 = new Thread (this);
	         t2.start ();
	      }
	   }


    class Player_Task2 extends TimerTask {
    	public void run () {
    		//System.out.println("calling pattern1");
    		playpattern1 PP1 = new playpattern1();
    		PP1.start();
    		//System.out.println("playpattern2 taking pattern from queue");
    		//player.play(pattern_queue.remove());
                //Sequence sequence2 = player.getSequencer().getSequence();
               Sequence sequence2 = player.getSequence(pattern_queue.remove());
               // Sequencer playerSeqencer = player.getSequencer();
               // playerSeqencer.setTempoInBPM(120);
                lexoperm.lexoplayer2.device.sendSequence(sequence2);
                player.close();
        }
    	
    }
}


public static void main(String[] args) {
	//int N = 12;
     // int N = Integer.parseInt(args[0]);
	//Queue<Pattern> pattern_queue = new LinkedList<Pattern>();
	//perm(N);


}

}

