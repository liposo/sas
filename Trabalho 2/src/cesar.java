import java.io.File;

import javax.swing.JOptionPane;


public class cesar {
	
	public cesar(File originalFile, File encryptedFile) throws Exception {
		loadListFromFile list = new loadListFromFile(originalFile, encryptedFile);
		
		int chaveCesar = 0;
		boolean confirma = false;
		
		if(list.getOriginalByteslen() == list.getEncryptedBytesLen()) {
			for(int i=0; i<list.getOriginalByteslen(); i++) {
				if(chaveCesar == 0) {
					chaveCesar = (list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i));
					confirma = true;
				} else {
					if(chaveCesar != (list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i))){
						confirma = false;
						break;
					}
				}
			}
			if(confirma) {
				JOptionPane.showMessageDialog(null, "Cifra de César, chave = "+chaveCesar);
			} else {
				JOptionPane.showMessageDialog(null,"Chave não identificada. Possivel que não seja Cifra de César!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Arquivos com tamanhos diferente, não é cifra de César");
		}
	}
}
