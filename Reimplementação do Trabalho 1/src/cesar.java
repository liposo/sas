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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class cesar extends JFrame {
	
	private static final long serialVersionUID = 3547947247305404954L;
	
	private loadListFromFile list;
	
	private JSpinner spinnerChave;
	
	public cesar(File file, int mode) throws Exception {
		super();
		
		list = new loadListFromFile(file);
		
		this.setSize(330, 100);
		this.setTitle("Cesar");
		
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
						cesarDecrypt();
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
						cesarEncrypt();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
	}
	
	private void cesarEncrypt() throws Exception {
		
		List<Integer> encryptedfileByteIntList = new ArrayList<Integer>();
		
		int chave = (Integer) spinnerChave.getValue();
		int intAdd;
		
		OutputStream os = new FileOutputStream(list.getFileName()+".cesar");
		
		for(int i=0; i<list.getlength(); i++) {
			intAdd = list.getIntList().get(i) + chave;
			if(intAdd > 256) {
				intAdd = intAdd - 256;
			}
			//System.out.println(intAdd);
			encryptedfileByteIntList.add(intAdd);
			os.write(encryptedfileByteIntList.get(i));
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
	
	private void cesarDecrypt() throws Exception{
		
		List<Integer> dencryptedfileByteIntList = new ArrayList<Integer>();
		
		int chave = (Integer) spinnerChave.getValue();
		int intAdd;
		
		String nomeEncrypted = list.getFileName();
		String nomeOriginal = list.getFileName().substring(0,list.getFileName().lastIndexOf(".cesar"));
		String extOriginal = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
		String novoNome = nomeEncrypted + "_decripted" + extOriginal;
		
		OutputStream os = new FileOutputStream(novoNome);
		
		for(int i=0; i<list.getlength(); i++) {
			intAdd = list.getIntList().get(i) - chave;
			if(intAdd < 0) {
				intAdd = intAdd + 256;
			}
			dencryptedfileByteIntList.add(intAdd);
			os.write(dencryptedfileByteIntList.get(i));
		}
		os.close();
		JOptionPane.showMessageDialog(this, "Feito");
	}
}
