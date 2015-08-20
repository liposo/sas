import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class vigenere extends JFrame {
	
	private static final long serialVersionUID = 3547947247305404954L;
	
	private loadListFromFile list;
	
	private JTextField campoChave;
	
	public vigenere(File file, int mode) throws Exception {
		super();
		
		list = new loadListFromFile(file);
		
		this.setSize(500, 90);
		this.setTitle("Vigenère");

		JLabel labelChave = new JLabel("Chave: ");
		campoChave = new JTextField(20);

		JButton buttonEnc = new JButton("Encrypt");
		JButton buttonDec = new JButton("Decrypt");
		
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 15, 15);
		
		this.setLayout(flow);
		
		this.add(labelChave);
		
		this.add(campoChave);
		
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
						vigenereDecrypt();
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
						vigenereEncrypt();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
	
	private void vigenereEncrypt() throws Exception {
		
		List<Integer> encryptedfileByteIntList = new ArrayList<Integer>();
		
		byte[] chave = campoChave.getText().getBytes();
		int idx=0;
		
		int intAdd;
		
		OutputStream os = new FileOutputStream(list.getFileName()+".vig");
		
		for(int i=0; i<list.getlength(); i++) {
			int ch = Byte.toUnsignedInt(chave[idx]);
			idx++;
			if (idx >= chave.length) idx = 0;
			
			intAdd = list.getIntList().get(i) + ch;
			
			if(intAdd > 256) {
				intAdd = intAdd - 256;
			}
			if(intAdd < 0) {
				intAdd = intAdd + 256;
			}
			
			//System.out.println(intAdd);
			encryptedfileByteIntList.add(intAdd);
			os.write(encryptedfileByteIntList.get(i));
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
	
	private void vigenereDecrypt() throws Exception{
		
		List<Integer> dencryptedfileByteIntList = new ArrayList<Integer>();
		
		byte[] chave = campoChave.getText().getBytes();
		
		int idx=0;
		int intAdd;
		
		String nomeEncrypted = list.getFileName();
		String nomeOriginal = list.getFileName().substring(0,list.getFileName().lastIndexOf(".vig"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = nomeEncrypted + "_decripted" + extOriginal;
		
		OutputStream os = new FileOutputStream(novoNome);
		
		for(int i=0; i<list.getlength(); i++) {
			int ch = Byte.toUnsignedInt(chave[idx]);
			idx++;
			if (idx >= chave.length) idx = 0;
			
			intAdd = list.getIntList().get(i) - ch;
			
			if(intAdd > 256) {
				intAdd = intAdd - 256;
			}
			if(intAdd < 0) {
				intAdd = intAdd + 256;
			}
			
			//System.out.println(intAdd);
			dencryptedfileByteIntList.add(intAdd);
			os.write(dencryptedfileByteIntList.get(i));
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
}
