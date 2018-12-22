/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lexoperm;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Owner
 */
public class Authority
{
   //-----------------------------------------------------------------
   //  Displays some words of wisdom.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Authority");

      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      JPanel primary = new JPanel();
      primary.setBackground (Color.yellow);
      primary.setPreferredSize (new Dimension(250, 75));

      JLabel label1 = new JLabel ("Question authority,");
      JLabel label2 = new JLabel ("but raise your hand first.");

      primary.add (label1);
      primary.add (label2);

      frame.getContentPane().add(primary);
      frame.pack();
      frame.setVisible(true);
   }
}
