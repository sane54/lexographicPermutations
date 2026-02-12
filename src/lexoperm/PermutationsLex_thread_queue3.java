package lexoperm;


import java.util.Random;
import org.jfugue.*;



public class PermutationsLex_thread_queue3 {

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

        private Thread tperm;

        public void start (){
           System.out.println("Starting permutations " );
           if (tperm == null) {
              tperm = new Thread (this);
              tperm.start ();
           }
        }

        @Override
        public void run() {
            // initialize permutation
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
                //while (myp.isShowing()==false) System.out.println("Wait");
                Pattern jpattern = new Pattern();
                jpattern.addElement(new Tempo(440));
                byte instbyte = 71;
                Note Rnote;
                jpattern.addElement(new Instrument(instbyte));
                for (int i = 0; i < 12; i++) {
                    offset = (generator.nextInt( 4 ) - 4)*12;
                    indec = a[i] + 84 + offset;
                    //DEBUG
                    //System.out.println (indec);
                    Rbyte = (byte)indec;

                    if (i == 11) {
                        //System.out.println("extra length for " + i);
                        Rnote = new Note(Rbyte, 1.5);
                    }
                    else {
                        Rnote = new Note(Rbyte, 0.25);                        
                    }
                    jpattern.addElement(Rnote);
                    //DEBUG
                    //System.out.println (jpattern.getMusicString());
                }//end for
                //jpattern.add(" Cmaj7w ");
                player.play(jpattern);
                myp.dispose();
            } //end while
        }//end method Run
    }//end class Perm

//    static class cubes implements Runnable {
//
//        public cubes(){
//
//        }
//        public void start ()
//        {
//         //  System.out.println("Starting permutations " );
//           if (tperm2 == null)
//           {
//              tperm2 = new Thread (this);
//              tperm2.start ();
//           }
//        }
//        
//        private Thread tperm2;
//        
//        @Override
//        public void run() {
//            //System.out.println("beautiful feet beautiful soup");
//        }
//
//    }//end cubes

}//end PermutationsLex_thread_queue3.java