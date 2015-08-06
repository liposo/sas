import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VigenereFrame extends JFrame {

	private static final long serialVersionUID = 468016138618943999L;
	
	//private JTextArea memo = new JTextArea();
	private JTextField campoChave;
	
	public VigenereFrame()
	{
		super();
		this.setSize(500, 90);
		this.setTitle("Vigenère");

		JLabel labelChave = new JLabel("Chave: ");
		campoChave = new JTextField(20);

		JButton button = new JButton("Selecionar Arquivo");
		
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 15, 15);
		
		this.setLayout(flow);
		
		this.add(labelChave);
		
		this.add(campoChave);
		
		this.add(button);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
	}
	
	private void openFile() 
	{
		if (campoChave.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Insira uma chave!" );
			campoChave.setFocusable(true);
			return;
		}
		
		try
		{
			JFileChooser fc = new JFileChooser(".");
		
			if (fc.showOpenDialog(this) == 0)
			{
				
				File file = fc.getSelectedFile();
				
				if (file.getName().endsWith(".vig")) {
					descriptografar(file);
				}
				else
				{
					criptografar(file);
				}
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Pau: "+ex.getMessage());
		}
	}
	
	private void descriptografar(File file) throws Exception
	{
		int idx=0;
		byte[] chave = campoChave.getText().getBytes();
		
		FileInputStream input = new FileInputStream(file);
		
		String nomeOriginal = file.getName().substring(0, file.getName().lastIndexOf(".vig"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = file.getName() + extOriginal;
		
		File fileOut = new File(novoNome);
		
		FileOutputStream output = new FileOutputStream(fileOut);
		
		try
		{
			byte[] buff = new byte[100];
			
			int len;
			while ((len = input.read(buff)) > 0)
			{
				byte b;
				for(int i=0; i<len; i++)
				{					
					b = buff[i];
				
					int ch = Byte.toUnsignedInt(chave[idx]);
					idx++;
					if (idx >= chave.length) idx = 0;					
					
					int bv = Byte.toUnsignedInt(b) - ch;
					
					if (bv <  0)
					{
						bv = bv + 255;
					}
					
					byte newbyte = (byte) bv;
					
					output.write(newbyte);
				}
			}
		}
		finally
		{
			output.close();
			input.close();
		}
		
		JOptionPane.showMessageDialog(this, "Feito" );
	}
	
	private void criptografar(File file) throws Exception {
		
		FileInputStream input = new FileInputStream(file);
		
		File fileOut = new File(file.getAbsolutePath()+".vig");
		
		FileOutputStream output = new FileOutputStream(fileOut);
		
		try
		{
			int idx=0;
			byte[] chave = campoChave.getText().getBytes();
			
			byte[] buff = new byte[100];
			
			int len;
			while ((len = input.read(buff)) > 0)
			{
				byte b;
				for(int i=0; i<len; i++)
				{
					b = buff[i];
					
					int ch = Byte.toUnsignedInt(chave[idx]);
					idx++;
					if (idx >= chave.length) idx = 0;					
					
					int bv = Byte.toUnsignedInt(b) + ch;
					
					if (bv >  255)
					{
						bv = bv - 255;
					}					
					
					byte newbyte = (byte) bv;
					
					
					output.write(newbyte);
				}
			}
		}
		finally
		{
			output.close();
			input.close();
		}
		
		JOptionPane.showMessageDialog(this, "Feito" );
	}
}
