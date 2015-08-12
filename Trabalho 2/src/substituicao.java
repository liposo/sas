import java.io.File;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class substituicao {

	int[] chave = new int[255];
	
	public substituicao(File originalFile, File encryptedFile) throws Exception {
		
		loadListFromFile list = new loadListFromFile(originalFile, encryptedFile);
		
		int[] chave = new int[255];
		
		Arrays.fill(chave, 0);
		
		boolean confirma = false;
		
		if(list.getOriginalByteslen() == list.getEncryptedBytesLen()) {
			for(int i=0; i<list.getOriginalByteslen(); i++) {
				if(chave[list.getOriginalByteIntList().get(i)] == 0) {
					chave[list.getOriginalByteIntList().get(i)] = list.getEncryptedByteIntList().get(i);
					//System.out.println(list.getEncryptedByteIntList().get(i) +"==>"+ list.getEncryptedByteIntList().get(i));
					confirma = true;
				} else if (chave[list.getOriginalByteIntList().get(i)] != list.getEncryptedByteIntList().get(i)) {
					confirma = false;
					break;
				}
			}
			if(confirma) {
				JOptionPane.showMessageDialog(null, "Cifra de Substituição.");
				buildAndDisplayChaveFrame(chave);
			} else {
				JOptionPane.showMessageDialog(null,"Chave não identificada. Possivel que não seja Cifra de Substituição!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Arquivos com tamanhos diferente, não é cifra de Substituição");
		}
	}
	
	private void buildAndDisplayChaveFrame(int[] chave) {
		
		JFrame subsFrame = new JFrame();
		JTextArea memo = new JTextArea();
		JScrollPane scroll = new JScrollPane(memo);
		
		scroll.setSize(600,300);
		memo.setLineWrap(true);
		memo.setWrapStyleWord(true);
		subsFrame = new JFrame();
		subsFrame.setTitle("Chave - Substituição");
		subsFrame.setSize(300, 300);
		subsFrame.setVisible(true);
		subsFrame.setResizable(true);
		subsFrame.setLocationRelativeTo(null);
		subsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		subsFrame.add(scroll);
		
		for(int i=0; i<chave.length; i++) {
			if(chave[i]==0) {
				memo.append("["+i+"]"+"==>[NULL], ");
				//System.out.print("["+i+"]"+"==>[NULL], ");
			} else {
				memo.append("["+i+"]"+"==>"+chave[i]+", ");
				//System.out.print("["+i+"]"+"==>"+chave[i]+", ");
			} 
		}
	}
}
