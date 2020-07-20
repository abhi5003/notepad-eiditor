
package texteditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Texteditor implements ActionListener, WindowListener
{
    JMenuItem neww, open, save, saveAs, cut, copy, paste, font, font_color, background_color;
    JTextArea tarea;
    JFrame jf, font_frame;
    JComboBox font_family, font_size, font_style;
    JButton apply;
    File file;
    
    Texteditor()
    {
        try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } 
        catch (Exception e)
        {
            System.err.println(e);
        }
        
        jf = new JFrame("*Untiled - NotePad*");
        jf.setSize(700, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        // jFrame icon
        ImageIcon img = new ImageIcon("G:\\javapro\\texteditor\\icon.png");
        jf.setIconImage(img.getImage());
        
        
        // Jmenu bar and Menu
        JMenuBar jmbar = new JMenuBar();
        // file menu and items
        JMenu file = new JMenu("File");
        jmbar.add(file);
        
        neww = new JMenuItem("New");
        neww.addActionListener(this);
        file.add(neww);
        
        open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        
        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        
        saveAs = new JMenuItem("Save As...");
        saveAs.addActionListener(this);
        file.add(saveAs);
        
        // edit menu and items
        JMenu edit = new JMenu("Edit");
        jmbar.add(edit);
        
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        
        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);
        
        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);
        
        // format menu and items
        JMenu format = new JMenu("Format");
        jmbar.add(format);
        
        font=new JMenuItem("Font");
        font.addActionListener(this);
        format.add(font);
        
        font_color = new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);
        
        background_color = new JMenuItem("Background Color");
        background_color.addActionListener(this);
        format.add(background_color);
        
        jf.setJMenuBar(jmbar);
        
        tarea = new JTextArea();
        JScrollPane scroll = new JScrollPane(tarea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        jf.add(scroll);
        
        jf.addWindowListener(this);
        
        
        jf.setVisible(true);
    }

    public static void main(String[] args) 
    {
        new Texteditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
       if(e.getSource()==neww)
       {
           newFile();
           
       }
       if(e.getSource()==open)
       {
           openFile();
       }
       if(e.getSource()==save)
       {
           saveFile();
       }
       if(e.getSource()==saveAs)
       {
           saveAsFile();
       }
       if(e.getSource()==cut)
       {
           tarea.cut();
       }
       if(e.getSource()==copy)
       {
           tarea.copy();
       }
       if(e.getSource()==paste)
       {
           tarea.paste();
       }
       if(e.getSource()==font)
       {
           fontFrame();
       }
       if(e.getSource()==apply)
       {
           setFontOnTextArea();
           
       }
       if(e.getSource()==font_color)
       {
           Color c=JColorChooser.showDialog(jf, "Choose Font Color", Color.BLACK);
           tarea.setForeground(c);
           
       }
       if(e.getSource()==background_color)
       {
          Color bg= JColorChooser.showDialog(jf, "Set Background Color", Color.WHITE);
           tarea.setBackground(bg);
       }
       
       
    }
    void setFontOnTextArea()
    {
        String fontfamily=(String)font_family.getSelectedItem();
           String fontsize=(String)font_size.getSelectedItem();
           String fontstyle=(String)font_style.getSelectedItem();
           
           int style=0;
           if(fontstyle.equals("Plain"))
           {
               style=0;
           }
           else if (fontstyle.equals("Bold")) 
           {
               style=1;
           } else if(fontstyle.equals("Italic"))
           {
               style=2;
           }
           Font fontt = new Font(fontfamily, style, Integer.parseInt(fontsize));
           tarea.setFont(fontt);
           font_frame.setVisible(false);
    }
    void fontFrame()
    {
        font_frame= new JFrame("Choose Font");
        font_frame.setSize(500, 500);
        font_frame.setLocationRelativeTo(jf);
        font_frame.setLayout(null);
        
       String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        font_family=new JComboBox(fonts);
        font_family.setBounds(50, 100, 100, 30);
        font_frame.add(font_family);
        
        String[] sizes ={"10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "32", "38"};
        font_size=new JComboBox(sizes);
        font_size.setBounds(170, 100, 100, 30);
        font_frame.add(font_size);
        
        String[] styles={"Plain", "Bold", "Italic"};
        font_style=new JComboBox(styles);
        font_style.setBounds(300, 100, 100, 30);
        font_frame.add(font_style);
        
        apply = new JButton("Apply");
        apply.setBounds(180, 200, 100, 50);
        apply.addActionListener(this);
        font_frame.add(apply);
        
        font_frame.setVisible(true);
    }
    void saveFile()
    {
        String title=jf.getTitle();
        if (title.equals("*Untiled - NotePad*")) 
        {
            saveAsFile();
        } else 
        {
            String text= tarea.getText();
               try(FileOutputStream fos = new FileOutputStream(file))
               {
                   byte[] b= text.getBytes();
                   fos.write(b);
                   
               } 
               catch (IOException ee) 
               {
                   ee.printStackTrace();
               }
        }
    }
    void saveAsFile()
    {
        JFileChooser fileChooser = new JFileChooser();
           int result = fileChooser.showSaveDialog(jf);
           if(result==0)
           {
               String text = tarea.getText();
               file=fileChooser.getSelectedFile();
               jf.setTitle(file.getName());
               try(FileOutputStream fos = new FileOutputStream(file))
               {
                   byte[] b= text.getBytes();
                   fos.write(b);
                   
               } 
               catch (IOException ee) 
               {
                   ee.printStackTrace();
               }
           }
    }
    void openFile()
       {
           JFileChooser fileChooser = new JFileChooser();
           int result = fileChooser.showOpenDialog(jf);
           if(result==0)
           {
               tarea.setText(" ");
               file= fileChooser.getSelectedFile();
               jf.setTitle(file.getName());
              try(FileInputStream fis = new FileInputStream(file))
             {
               int i;
               while((i=fis.read())!=-1)
               {
                  tarea.append(String.valueOf((char)i));
                  
               }
             } 
             catch (Exception ee)
            {
               ee.printStackTrace();
            }
           } 
       }
    
    void newFile()
    {
         String text = tarea.getText();
           if(!text.equals(""))
           {
               int i = JOptionPane.showConfirmDialog(jf, "Do You Want To Save This File ");
             if(i==0)
             {
               saveAsFile();
               tarea.setText("");
               jf.setTitle("*Untiled - NotePad*");
             }
           }
    }

    @Override
    public void windowOpened(WindowEvent e) 
    {
        
    }

    @Override
    public void windowClosing(WindowEvent e) 
    { 
       String text=tarea.getText();
       String title=jf.getTitle();
        
        if (!text.equals("") && title.equals("*Untiled - NotePad*"))
        {
            int i=JOptionPane.showConfirmDialog(jf, "Do You Want Save This File");
            if(i==0)
            {
                saveAsFile();
            }
            
        } 
        if(!text.equals("") && !title.equals("*Untiled - NotePad*"))
        {
            saveFile();
        }
        
        System.out.println(e);
        
    }

    @Override
    public void windowClosed(WindowEvent e) 
    {
        
    }

    @Override
    public void windowIconified(WindowEvent e) 
    {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) 
    {
        
    }

    @Override
    public void windowActivated(WindowEvent e) 
    {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) 
    {
       
    }

    
}
