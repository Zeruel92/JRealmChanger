/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jrealmchanger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.Vector;
import java.lang.Runtime;

/**
 *
 * @author gabry
 */
public class JRealmChanger extends JFrame implements ActionListener{
    //Main Frame Declaration:
    private String WOWPATH;
    private String REALMPATH;
    private BufferedReader buff;
    private BufferedWriter wbuff;
    private ButtonGroup GPR;
    private Vector<JRadioButton> RD;
    private JPanel P;
    private JButton OK;
    private JButton ADD;
    private JButton DELETE;
    private JCheckBox RUN;
    private Runtime RNT;
    private File realmlist;
    //Temp Frame Declaration:
    private JFrame ftmp;
    private JPanel ptmp;
    private JLabel ltmp;
    private JTextField ttmp;
    private JButton oktmp;
    private JButton oktmp2;
    private int i;
    
    public JRealmChanger (String wowpath, String realmpath){
        super("RealmChanger by Broly");
        this.setSize(400,300);
        this.WOWPATH=wowpath;
        this.REALMPATH=realmpath;
        this.GPR=new ButtonGroup();
        this.RD=new Vector<JRadioButton>();
        this.P=new JPanel();
        this.add(P);
        this.OK= new JButton("Salva");
        this.OK.addActionListener(this);
        this.ADD=new JButton("Aggiungi");
        this.ADD.addActionListener(this);
        this.DELETE=new JButton("Elimina");
        this.DELETE.addActionListener(this);
        this.realmlist=new File(this.REALMPATH);
        this.RUN=new JCheckBox("Avvia Wow");
        try{
           
            this.buff =new BufferedReader(new FileReader(realmlist));
            String tmp="";
            boolean active;
            while(tmp!=null){
                tmp=this.buff.readLine();                     
		if(tmp!=null){
		    if(tmp.startsWith("#")){
			active=false;
			tmp=tmp.substring(1,tmp.length());
		    }
		    else active=true;
		    JRadioButton bt=new JRadioButton(tmp);
		    this.RD.addElement(bt);
		    this.GPR.add(RD.lastElement());
		    this.RD.lastElement().setSelected(active);
		}              
	    }
            this.buff.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<this.RD.size();i++){
            this.P.add(RD.elementAt(i));
        }
        this.P.add(this.RUN);
        this.P.add(this.OK);
        this.P.add(this.ADD);
        this.P.add(this.DELETE);
	// this.DELETE.setEnabled(false);
	//   this.ADD.setEnabled(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Salva")){
            String tmp="";
            try{
		this.wbuff=new BufferedWriter(new FileWriter(this.realmlist));
		for(i=0;i<this.RD.size();i++){
		    if(this.RD.elementAt(i).isSelected()){
			tmp=this.RD.elementAt(i).getActionCommand()+"\n";
			//System.out.println(tmp);
		    }
		    else{
			tmp="#"+this.RD.elementAt(i).getActionCommand()+"\n";
			// System.out.println(tmp);
		    }
		    this.wbuff.write(tmp);
		    this.wbuff.flush();
		}
		this.wbuff.close();
		if(this.RUN.isSelected()){
		    this.RNT= Runtime.getRuntime();
		    this.RNT.exec(WOWPATH);
		    System.out.println("Starting..."+WOWPATH);
		}
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(e.getActionCommand().equals("Aggiungi")){
            ftmp=new JFrame ("Aggiungi Realm");
            ftmp.setSize(600,150);
            ftmp.setVisible(true);
            ptmp=new JPanel();
            ftmp.add(ptmp);
            ltmp=new JLabel("Inserisci il nuovo RealmList");
            ptmp.add(ltmp);
            ttmp=new JTextField(50);
            ptmp.add(ttmp);
            oktmp=new JButton("Aggiungi Realm");
            oktmp.addActionListener(this);
            ptmp.add(oktmp);
        }
        else if(e.getActionCommand().equals("Aggiungi Realm")){
            this.RD.addElement(new JRadioButton(ttmp.getText()));
            ftmp.setVisible(false);
            this.GPR.add(RD.lastElement());
            this.P.removeAll();
            for(i=0;i<this.RD.size();i++){
		this.P.add(RD.elementAt(i));
            }
	    this.P.add(this.RUN);
            this.P.add(this.OK);
	    this.P.add(this.ADD);
            this.P.add(this.DELETE);
            this.P.repaint();
            this.P.revalidate();
        }
        else if(e.getActionCommand().equals("Elimina")){
            ftmp=new JFrame("Elimina Realm");
            ftmp.setSize(600,150);
            ftmp.setVisible(true);
            ptmp=new JPanel();
            ftmp.add(ptmp);
            for(i=0;i<this.RD.size();i++){
                if(this.RD.elementAt(i).isSelected())
                    break;
            }
            ltmp=new JLabel("Sicuro di voler eliminare il realm: "+this.RD.elementAt(i).getText());
            ptmp.add(ltmp);
            oktmp=new JButton("Si");
            oktmp.addActionListener(this);
            ptmp.add(oktmp);
            oktmp2=new JButton("No");
            oktmp2.addActionListener(this);
            ptmp.add(oktmp2);
        }
        else if(e.getActionCommand().equals("Si")){
            ftmp.setVisible(false);
            this.GPR.remove(this.RD.elementAt(i));
            this.RD.removeElementAt(i);
            this.P.removeAll();
            for(i=0;i<this.RD.size();i++){
		this.P.add(RD.elementAt(i));
            }
	    this.P.add(this.RUN);
            this.P.add(this.OK);
	    this.P.add(this.ADD);
            this.P.add(this.DELETE);
            this.P.repaint();
            this.P.revalidate();
        }
        else if(e.getActionCommand().contains("No")){
            ftmp.setVisible(false);
        }
        
    }
    
}
