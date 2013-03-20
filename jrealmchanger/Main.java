package jrealmchanger;
import javax.swing.JDialog;
public class Main {
    
    public static void main(String[] args) {
        String VERSION="enUS";
        String OS="Win";
        boolean wow=false;
        boolean exit=false;
        String path="";
        while(!wow){
	    try{
		FileBrowser f=new FileBrowser();
		int res=f.showOpenDialog(f);
		if(res==f.APPROVE_OPTION){
		    path=(f.getSelectedFile().getAbsolutePath());
		    if((f.getSelectedFile().getName()).equals("Wow.exe")){
			wow=true;
		    }
		}
		else if(res!=f.APPROVE_OPTION){
		    wow=true;
		    exit=true;
		}
		if(exit){
		    System.exit(0);
		}
	    }catch(Exception e){   
		//No exception 
	    }
	}   
        String pathrealm="";
        if(OS.equals("Unix")){
            pathrealm=path.substring(0,path.indexOf("Wow.exe"))+"Data/"+VERSION+"/realmlist.wtf";
        }
        else
            pathrealm=path.substring(0,path.indexOf("Wow.exe"))+"Data\\"+VERSION+"\\realmlist.wtf";
        //System.out.println(pathrealm);
        JRealmChanger jrc=new JRealmChanger(path,pathrealm);
        jrc.setVisible(true);
    }
}
