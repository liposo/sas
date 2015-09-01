import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class transposicao extends JFrame {

	private static final long serialVersionUID = 334236968449334117L;
	
	private loadListFromFile list;
	
	private JSpinner spinnerChave;
	
	public transposicao(File file, int mode) throws Exception {
		super();
		
		list = new loadListFromFile(file);
		
		this.setSize(330, 100);
		this.setTitle("Transposição");
		
		JLabel labelChave = new JLabel("Chave: ");
		spinnerChave = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		JButton buttonEnc = new JButton("Encrypt");
		JButton buttonDec = new JButton("Decrypt");
		
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 15, 15);
		
		this.setLayout(flow);
		this.add(labelChave);
		this.add(spinnerChave);
		this.add(buttonEnc);
		this.add(buttonDec);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		if(mode == 2) {
			buttonEnc.setEnabled(false);
			buttonDec.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						transpDecrypt();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		} else {
			buttonDec.setEnabled(false);
			buttonEnc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						transpEncrypt();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
	
	private void transpEncrypt() throws Exception {
		
		int chave = (Integer) spinnerChave.getValue();
		
		OutputStream os = new FileOutputStream(list.getFileName()+".transp");
		
		ArrayList<Integer[]> encryptedfileByteIntList = new ArrayList<Integer[]>();
		
		Integer[] linha = new Integer[chave];
		int idxLinha = 0;
		
		int intAdd;
		
		for(int i=0; i<list.getlength(); i++) {
			linha[idxLinha] = list.getIntList().get(i);
			idxLinha++;
			if(idxLinha >= chave) {
				encryptedfileByteIntList.add(linha);
				idxLinha = 0;
				linha = new Integer[chave];
			}
		}
		
		int originalSize = list.getlength();
		if(idxLinha != 0 ){
			while(idxLinha < chave) {
				linha[idxLinha] = 0;
				idxLinha++;
			}
			linha[linha.length-1] = originalSize;
			encryptedfileByteIntList.add(linha);
		} else {
			linha = new Integer[chave];
			while(idxLinha < chave) {
				linha[idxLinha] = 0;
				idxLinha++;
			}
			linha[linha.length-1] = originalSize;
			encryptedfileByteIntList.add(linha);
		}
		
//		System.out.println(); System.out.println("--------------# Completando com Zeros #---------------------------"); System.out.println();
//		for(int i=0; i<encryptedfileByteIntList.size(); i++) {
//			System.out.println(Arrays.toString(encryptedfileByteIntList.get(i)) + System.getProperty("line.separator"));
//		}
		
		for(int c=0; c<chave; c++) {
			for(int i=0; i<encryptedfileByteIntList.size(); i++) {
				linha = encryptedfileByteIntList.get(i);
				intAdd = linha[c];
				os.write(intAdd);
			}
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
	
	private void transpDecrypt() throws Exception{
		
		int chave = (Integer) spinnerChave.getValue();
		
		List<Integer[]> decryptedfileByteIntList = new ArrayList<Integer[]>();
		
		int intAdd;
		int qtdeLinha = list.getlength()/chave; 
		
		String nomeEncrypted = list.getFileName();
		String nomeOriginal = list.getFileName().substring(0,list.getFileName().lastIndexOf(".transp"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = nomeEncrypted + "_decripted" + extOriginal;
		
		OutputStream os = new FileOutputStream(novoNome);
		
		for(int i=0; i<qtdeLinha; i++) {
			Integer[] linha = new Integer[chave];
			decryptedfileByteIntList.add(linha);
		}
		
		int idxColuna = 0;
		int idxLinha = 0;
		
		for(int b=0; b<list.getlength(); b++) {
			Integer[] linha = decryptedfileByteIntList.get(idxLinha);
			linha[idxColuna] = list.getIntList().get(b);
			idxLinha++;
			if(idxLinha >= qtdeLinha) {
				idxLinha = 0;
				idxColuna++;
			}
		}

		Integer[] ultimaLinha = decryptedfileByteIntList.get(decryptedfileByteIntList.size()-1);
		int qtdeZeros = list.getlength() - ultimaLinha[ultimaLinha.length-1];
		
		ultimaLinha = Arrays.copyOf(ultimaLinha, ultimaLinha.length-qtdeZeros);
		decryptedfileByteIntList.remove(decryptedfileByteIntList.size()-1);
		decryptedfileByteIntList.add(ultimaLinha);
		
		for(int i=0; i<decryptedfileByteIntList.size(); i++) {
			Integer[] linha = decryptedfileByteIntList.get(i);
			for(int c=0; c<linha.length; c++) {
				intAdd = linha[c];
				os.write(intAdd);
			}
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}

}
