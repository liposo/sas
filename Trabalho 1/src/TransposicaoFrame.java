import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

public class TransposicaoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextArea memo = new JTextArea();
	private JSpinner campoChave;
	
	public TransposicaoFrame()
	{
		super();
		this.setSize(500, 300);
		this.setTitle("Transposição");
		
		JScrollPane scroll = new JScrollPane(memo);
		scroll.setSize(300,300);

		JLabel labelChave = new JLabel("Chave: ");
		campoChave = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

		JButton button = new JButton("Selecionar Arquivo");
		
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 15, 15);
		
		JPanel paneltop = new JPanel(flow);
		
		this.add(paneltop, BorderLayout.NORTH);
			
		paneltop.add(labelChave);
		
		paneltop.add(campoChave);
		
		paneltop.add(button);
		
		this.add(scroll);		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
		
		//this.pack();
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
				
				if (file.getName().endsWith(".transp")) {
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
		
		int chave = (Integer) campoChave.getValue();
		FileInputStream input = new FileInputStream(file);
		
		String nomeOriginal = file.getName().substring(0, file.getName().lastIndexOf(".transp"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = file.getName() + extOriginal;
		
		File fileOut = new File(novoNome);
		
		FileOutputStream output = new FileOutputStream(fileOut);
		
		try
		{
			ArrayList<byte[]> matriz = new ArrayList<byte[]>();
			
			byte[] buff = new byte[100];

			int qtde = 0;
			int len;
			while ((len = input.read(buff)) > 0)
			{
				qtde += len;
			}
			
			int qtdeLinha = qtde / chave;
			
			input.close();
			input = new FileInputStream(file);
			
			for (int i=0; i<qtdeLinha; i++)
			{
				byte[] linha = new byte[chave];
				matriz.add(linha);
			}
			

			int idxColuna = 0;
			int idxLinha = 0;
			while ((len = input.read(buff)) > 0)
			{
				for (int b=0; b<len; b++)
				{
					byte[] linha = matriz.get(idxLinha);
					linha[idxColuna] = (byte) Byte.toUnsignedInt(buff[b]);
					idxLinha++;
					
					if (idxLinha >= qtdeLinha)
					{
						idxLinha = 0;
						idxColuna++;
					}
				}
			}
			
			byte[] ultimaLinha = matriz.get(matriz.size()-1);
			int qtdeZeros=0;
			for (int i=ultimaLinha.length-1; i>=0; i--)
			{
				if (ultimaLinha[i] == 0)
				{
					qtdeZeros++;
				}
			}
			
			ultimaLinha = Arrays.copyOf(ultimaLinha, ultimaLinha.length-qtdeZeros);
			matriz.remove(matriz.size()-1);
			matriz.add(ultimaLinha);
			
			for (int i=0; i<matriz.size(); i++)
			{
				byte[] linha = matriz.get(i);
				for (int c=0; c<linha.length; c++)
				{
					byte bb = linha[c];
					output.write(bb);
					
					memo.append(Byte.toString(bb)+", ");
				}
				memo.append(System.getProperty("line.separator") );
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
		
		/*
		 * Criar matriz com o tamanho da chave.
		 */
		int chave = (Integer) campoChave.getValue();
		
		
		FileInputStream input = new FileInputStream(file);
		
		File fileOut = new File(file.getAbsolutePath()+".transp");
		
		FileOutputStream output = new FileOutputStream(fileOut);
		
		try
		{
			ArrayList<byte[]> matriz = new ArrayList<byte[]>();			
			
			byte[] buff = new byte[100];
			
			byte[] linha = new byte[chave];
			int idxLinha = 0;
			
			int len;
			while ((len = input.read(buff)) > 0)
			{
				byte b;
				for(int i=0; i<len; i++)
				{
					b = buff[i];
					int bb = Byte.toUnsignedInt(b);						
					byte newbyte = (byte) bb;
					
					linha[idxLinha] = newbyte;
					idxLinha++;
					if (idxLinha >= chave) {
						matriz.add(linha);
						idxLinha = 0;
						linha = new byte[chave];						
					}

				}
			}
			int nZeros = 0;
			if (idxLinha != 0) 
			{
				while (idxLinha < chave) 
				{
					linha[idxLinha] = 0;
					idxLinha++;
					nZeros += nZeros;
				}
				matriz.add(linha);				
			}
			
				
			for (int i=0; i<matriz.size(); i++)
			{
				memo.append(Arrays.toString(matriz.get(i))+ System.getProperty("line.separator") );
			}

			memo.append("-------------------------------"+ System.getProperty("line.separator") );			
			
			for (int c=0; c<chave; c++)
			{
				for (int i=0; i<matriz.size(); i++)
				{
					linha = matriz.get(i);
					byte bb = linha[c];
					output.write(bb);
					
					memo.append(Byte.toString(bb)+", ");
				}
				memo.append(System.getProperty("line.separator") );
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
