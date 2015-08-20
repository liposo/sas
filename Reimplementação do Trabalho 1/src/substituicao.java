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
import javax.swing.JOptionPane;

public class substituicao extends JFrame {
	
	private static final long serialVersionUID = 1860741509630345482L;

	private Integer[] tabChave = {226,202,190,17,91,200,106,221,239,123,165,153,143,235,169,79,65,62,218,213,166,25,60,48,230,247,243,89,215,70,82,21,50,134,36,205,188,148,23,224,244,105,140,1,121,97,242,233,120,201,26,45,189,78,35,232,76,58,51,155,64,28,68,186,77,69,222,176,160,252,253,157,93,90,191,108,61,181,24,102,54,37,4,164,41,44,179,207,173,219,107,217,7,73,237,83,124,55,211,22,63,10,198,74,135,99,101,67,13,133,32,8,144,195,2,14,208,187,80,85,225,231,104,113,95,38,47,245,30,18,171,193,33,114,152,158,49,234,178,228,81,141,142,129,130,128,223,12,147,182,251,109,174,175,15,125,194,161,75,185,11,204,150,209,220,111,110,86,87,199,227,203,149,117,162,192,145,116,229,92,249,250,197,16,29,5,94,139,236,177,56,216,71,210,159,27,3,43,96,9,126,100,112,246,212,103,40,206,180,132,254,31,98,118,137,172,163,119,170,255,184,131,146,39,240,127,53,167,72,42,154,52,57,248,59,20,115,0,214,19,88,168,138,136,84,241,34,196,238,156,122,46,6,66,151,183};
	
	private loadListFromFile list;
 
	public substituicao(File file, int mode) throws Exception {
		super();
		
		list = new loadListFromFile(file);
		this.setSize(330, 100);
		this.setTitle("Substituição");

		JButton buttonEnc = new JButton("Encrypt");
		JButton buttonDec = new JButton("Decrypt");
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 15, 15);
		this.setLayout(flow);
		
		this.add(buttonEnc);
		this.add(buttonDec);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
		
		this.setLocationRelativeTo(null);
		
		if(mode == 2) {
			buttonEnc.setEnabled(false);
			buttonDec.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						subsDecrypt();
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
						subsEncrypt();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
	
	private void subsEncrypt() throws Exception {
		
		List<Integer> encryptedfileByteIntList = new ArrayList<Integer>();
		
		int intAdd;
		
		OutputStream os = new FileOutputStream(list.getFileName()+".subs");
		
		for(int i=0; i<list.getlength(); i++) {
			intAdd = tabChave[list.getIntList().get(i)];
			encryptedfileByteIntList.add(intAdd);
			os.write(encryptedfileByteIntList.get(i));
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
	
	private void subsDecrypt() throws Exception{
		
		List<Integer> dencryptedfileByteIntList = new ArrayList<Integer>();
		List<Integer> listaChave = Arrays.asList(tabChave);
		
		int intAdd;
		
		String nomeEncrypted = list.getFileName();
		String nomeOriginal = list.getFileName().substring(0,list.getFileName().lastIndexOf(".subs"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = nomeEncrypted + "_decripted" + extOriginal;
		
		OutputStream os = new FileOutputStream(novoNome);
		
		for(int i=0; i<list.getlength(); i++) {
			intAdd = listaChave.indexOf(list.getIntList().get(i));
			dencryptedfileByteIntList.add(intAdd);
			os.write(dencryptedfileByteIntList.get(i));
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
}
