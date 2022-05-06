import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import java.awt.*;
import java.awt.event.*;

public class TextEditor extends JFrame implements ActionListener {

    JFrame f;//declaring jFrame
    JTextArea textArea;//declaring jTextArea
    
    //Constructior
    public TextEditor(){

        //Setting it's title
        f=new JFrame("TextEditor");

        //Set text area
        textArea=new JTextArea();

        //Creating object or menu bar
        JMenuBar menuBar=new JMenuBar();
        // menuBar.setMargin(new InsetsUIResource(10,10,10,10));

        //Creating menu with name file
        JMenu file=new JMenu("File");
        // menuBar.setFont(new java.awt.Font("Dialog", 0, 20));//setfont to menu

        //Creating menu item
        JMenuItem open=new JMenuItem("Open");
        JMenuItem save=new JMenuItem("Save");
        JMenuItem print=new JMenuItem("Print");
        JMenuItem new_File=new JMenuItem("New");


        //Add action listner
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        new_File.addActionListener(this);

        //Adding item to menu
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(new_File);

        //Creating menu with name edit
        JMenu edit=new JMenu("Edit");

        //Creating menu item
        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");

        //Adding Action Listner
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        //Adding menu item
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        //Creating menu with name close
        JMenuItem close=new JMenuItem("Close");

        //Adding action listner
        close.addActionListener(this);

        //Adding menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(close);

        //set menu bar to frame
        f.setJMenuBar(menuBar);
        
        //set text area
        f.add(textArea);

        f.setSize(700,600);//Setting it's initial size(launching size)(by default null)
        f.setVisible(true);//Make it visible
    }

    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();
        if(s.equals("Cut")) {
            textArea.cut();
        }
        else if(s.equals("Copy")){
            textArea.copy();
        }
        else if(s.equals("Paste")){
            textArea.paste();
        }
        else if(s.equals("Save")){
            //creating an object of jFileChooser class
            JFileChooser j=new JFileChooser("f:");

            //invoke the showSaveDialog Function to show the save dialog
            int r=j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){

                //set the label to the path of the selected directory
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //create a file writer
                    FileWriter wr=new FileWriter(fi,false);

                    //create buffered writer to write
                    BufferedWriter w=new BufferedWriter(wr);

                    w.write(textArea.getText());

                    w.flush();
                    w.close();
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
            }
        }
        else if(s.equals("Print")){
            try{
                textArea.print();
            }
            catch(Exception evt){
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }
        else if(s.equals("Open")){
            //create an object of jfileChooser class
            JFileChooser j=new JFileChooser("f:");

            //invoke the showOptionDialog to show the save dialog
            int r=j.showOpenDialog(null);

            //if the user selects a file 
            if(r == JFileChooser.APPROVE_OPTION){
                //set the label to the path of the selected directory
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //string 
                    String s1="" ,sl="";

                    //file reader
                    FileReader fr=new FileReader(fi);

                    //buffered reader
                    BufferedReader br=new BufferedReader(fr);

                    //initialize sl
                    sl=br.readLine();

                    //take the input from the file
                    while((s1=br.readLine())!=null){
                        sl = sl+ "\n" +s1;
                    }

                    //set the text
                    textArea.setText(sl);
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
            }
        }
        else if(s.equals("New")){
            textArea.setText("");
        }
        else if(s.equals("Close")){
            f.setVisible(false);
        }
    }

    //Driver function
    public static void main(String[] args){
        new TextEditor();//Calling constructor
    }
}
