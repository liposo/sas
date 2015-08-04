import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


import javax.swing.*;

public class SubstituicaoFrame extends JFrame {
	private static final long serialVersionUID = 2269971701250845001L;
	
	private Integer[] tabChave = {226,202,190,17,91,200,106,221,239,123,165,153,143,235,169,79,65,62,218,213,166,25,60,48,230,247,243,89,215,70,82,21,50,134,36,205,188,148,23,224,244,105,140,1,121,97,242,233,120,201,26,45,189,78,35,232,76,58,51,155,64,28,68,186,77,69,222,176,160,252,253,157,93,90,191,108,61,181,24,102,54,37,4,164,41,44,179,207,173,219,107,217,7,73,237,83,124,55,211,22,63,10,198,74,135,99,101,67,13,133,32,8,144,195,2,14,208,187,80,85,225,231,104,113,95,38,47,245,30,18,171,193,33,114,152,158,49,234,178,228,81,141,142,129,130,128,223,12,147,182,251,109,174,175,15,125,194,161,75,185,11,204,150,209,220,111,110,86,87,199,227,203,149,117,162,192,145,116,229,92,249,250,197,16,29,5,94,139,236,177,56,216,71,210,159,27,3,43,96,9,126,100,112,246,212,103,40,206,180,132,254,31,98,118,137,172,163,119,170,255,184,131,146,39,240,127,53,167,72,42,154,52,57,248,59,20,115,0,214,19,88,168,138,136,84,241,34,196,238,156,122,46,6,66,151,183};
	
		
	private JTextArea memo = new JTextArea();
	
	public SubstituicaoFrame()
	{
		super();
		this.setSize(250, 250);
		this.setTitle("Substituição");

		
		
		JScrollPane scroll = new JScrollPane(memo);
		scroll.setSize(300,300);

		JButton button = new JButton("Selecionar Arquivo");
		
		//FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 15, 15);
		
		//this.setLayout(flow);
		
		this.add(scroll);
		
		this.add(button, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				
				if (file.getName().endsWith(".subs")) {
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
		
		FileInputStream input = new FileInputStream(file);
		
		String nomeOriginal = file.getName().substring(0, file.getName().lastIndexOf(".subs"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		//String novoNome = nomeOriginal.substring(0, nomeOriginal.lastIndexOf("."));
		String novoNome = file.getName() + extOriginal;
		
		File fileOut = new File(novoNome);
		
		FileOutputStream output = new FileOutputStream(fileOut);
		
		List<Integer> listaChave = Arrays.asList(tabChave);
		
		try
		{
			byte[] buff = new byte[100];
			
			int len;
			memo.append("Decript\r\n");
			while ((len = input.read(buff)) > 0)
			{
				byte b;
				for(int i=0; i<len; i++)
				{
					b = buff[i];
					int bb = Byte.toUnsignedInt(b);
					
					int bc = listaChave.indexOf(new Integer(bb));
					
					byte newbyte = (byte) bc;
					
					memo.append((int) bb + " -> " + bc + "\r\n");
					
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
		
		File fileOut = new File(file.getAbsolutePath()+".subs");
		
		FileOutputStream output = new FileOutputStream(fileOut);
		
		try
		{
			byte[] buff = new byte[100];
			
			int len;
			
			memo.append("Cript\r\n");
			while ((len = input.read(buff)) > 0)
			{
				byte b;
				for(int i=0; i<len; i++)
				{
					b = buff[i];
					int bb = Byte.toUnsignedInt(b);
					
					int bc = tabChave[bb];
					
					byte newbyte = (byte) bc;
					
					memo.append(b + " -> " + bc + "\r\n");
					
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
	
	/*
	// GERA TABELA 
	 
				ArrayList<Integer> lista = new ArrayList<Integer>();
				
				for (int i=0; i<=255; i++){
					lista.add(Integer.valueOf(i));					
				}
				
				while (lista.size() > 0)
				{
					int idx = (int) (Math.random()*lista.size());
					
					memo.append( lista.get(idx) + ","  );
					lista.remove(idx);
				}
	*/
	
}
