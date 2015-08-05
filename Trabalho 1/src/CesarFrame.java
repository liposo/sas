import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.*;

public class CesarFrame extends JFrame {

	private static final long serialVersionUID = 2269971701250845501L;

	private JSpinner spinnerChave;
	
	public CesarFrame()
	{
		super();
		this.setSize(300, 90);
		this.setTitle("Cesar");
		
		JLabel labelChave = new JLabel("Chave: ");
		spinnerChave = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		JButton button = new JButton("Selecionar Arquivo");
		
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 15, 15);
		
		this.setLayout(flow);
		
		this.add(labelChave);
		
		this.add(spinnerChave);
		
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
		try
		{
			JFileChooser fc = new JFileChooser(".");
		
			if (fc.showOpenDialog(this) == 0)
			{
				
				File file = fc.getSelectedFile();
				
				if (file.getName().endsWith(".cesar")) {
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
	
	private void descriptografar(File file) throws Exception{
		int chave = (Integer) spinnerChave.getValue();
		
		FileInputStream input = new FileInputStream(file);
		
		String nomeOriginal = file.getName().substring(0, file.getName().lastIndexOf(".cesar"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = nomeOriginal.substring(0, nomeOriginal.lastIndexOf("."));
		novoNome = novoNome + "_original" + extOriginal;
		
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
					
					int bc = b - chave;
					
					if (bc <  0)
					{
						bc = bc + 255;
					}
					
					byte newbyte = (byte) bc;
					
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
		int chave = (Integer) spinnerChave.getValue();
		
		FileInputStream input = new FileInputStream(file);
		
		File fileOut = new File(file.getAbsolutePath()+".cesar");
		
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
					
					int bc = b + chave;
					
					if (bc >  255)
					{
						bc = bc - 255;
					}
					
					byte newbyte = (byte) bc;
					
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
