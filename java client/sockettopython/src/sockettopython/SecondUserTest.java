package sockettopython;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SecondUserTest {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		
		// for the SSL encryption
		/*SSLSocketFactory factory =  (SSLSocketFactory)SSLSocketFactory.getDefault();
		
		SSLSocket sslsocket = (SSLSocket)factory.createSocket("localhost", 2004);
		 
		sslsocket.startHandshake();
		
		
		DataOutputStream dout=new DataOutputStream(sslsocket.getOutputStream());  
        DataInputStream din=new DataInputStream(sslsocket.getInputStream());


        dout.writeUTF("hi iheb");
        dout.flush();

        System.out.println("send first mess");
        String str = din.readUTF();//in.readLine();

        System.out.println("Message"+str);


        dout.close();  
        din.close();
        sslsocket.close();*/
		JFrame frame ;
	    JPanel chatPanel ;
	    JPanel inputPanel ;
	    JTextField textField ;
	    JTextArea textArea ;
	    JButton button ;
	    
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Your username please :");
		 final String  username = keyboard.nextLine();
		
		// Create and set up the window of chat
				frame = new JFrame("Chat Window") ;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
				frame.setSize(new Dimension(300, 200)) ;
			 
				// Create and set up the panels
				chatPanel = new JPanel( new BorderLayout() ) ;
				inputPanel = new JPanel( new BorderLayout() ) ;
			 
				// Add the panel to the window
				frame.getContentPane().add(chatPanel, BorderLayout.CENTER) ;
				// frame.getContentPane().add(inputPanel, BorderLayout.SOUTH) ;
			 
				// Set the window visible
				frame.setVisible( true ) ;
				
		
		textField = new JTextField() ;
		textArea = new JTextArea() ;
		button = new JButton("Send") ;
		textArea.setEditable(false) ;
	 
		inputPanel.add(textField, BorderLayout.CENTER) ;
		inputPanel.add(button, BorderLayout.EAST) ;
	 
		chatPanel.add(new JScrollPane(textArea), BorderLayout.CENTER) ;
		chatPanel.add(inputPanel, BorderLayout.SOUTH) ;
	 
		chatPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)) ;
	 
		// Add listeners to the button and the textField
		
		
		
		
		
		
		
		
		
		
		Socket socket=new Socket("localhost",2004);  

	        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());  
	        DataInputStream din=new DataInputStream(socket.getInputStream());
	        
	        ReadThread r = new ReadThread(socket,dout,din,textArea);
	        
	        r.start();
	        

	      
		
		
		button.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//textArea.append(username+" > "+textField.getText() + "\n") ;
				
				
		        
		        try {
		        	dout.writeUTF( username+" > "+ textField.getText());
			        dout.flush();
			        textField.setText("") ;
		        }catch(Exception e) {
		        	
		        }
		        
				
			}
        });
		
		

		
	        
	
	
	}


}
