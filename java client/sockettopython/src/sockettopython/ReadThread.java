package sockettopython;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class ReadThread extends Thread {
	
	Socket socket;  

    DataOutputStream dout;  
    DataInputStream din;
    JTextArea textArea;
    
    

    
	public ReadThread(Socket socket, DataOutputStream dout, DataInputStream din,JTextArea textArea) {
		super();
		this.socket = socket;
		this.dout = dout;
		this.din = din;
		this.textArea = textArea;
	}

	ReadThread(){
		
	}

	public void run(){
	
		boolean x = true;
		while(x) {
			try {
				if(din.available() != 0) {
					int count = din.available();  
			        byte[] ary = new byte[count];  
			        din.read(ary); 
			        String res="";
			        
			        for (byte bt : ary) {  
			          char k = (char) bt;  
			          res+=(k);  
			        }
			        
			        
			        textArea.append(res+"\n") ;
					
		        	
				}
			}catch(Exception e) {
				System.out.println(" read thread "+e.getMessage());
			}
		}
	}
}
