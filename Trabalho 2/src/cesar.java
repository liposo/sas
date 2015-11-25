import java.io.File;

import javax.swing.JOptionPane;


public class cesar {
	
	public cesar(File originalFile, File encryptedFile) throws Exception {
		loadListFromFile list = new loadListFromFile(originalFile, encryptedFile);
		
		int chaveCesar = 0;
		boolean confirma = false;
		
		if(list.getOriginalByteslen() == list.getEncryptedBytesLen()) {
			for(int i=0; i<list.getOriginalByteslen(); i++) {
				//System.out.println(+list.getEncryptedByteIntList().get(i)+" - "+list.getOriginalByteIntList().get(i)+" = "+((list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i))%256));
				if(chaveCesar == 0) {
					chaveCesar = (list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i));
					if(chaveCesar < 0 ) {
						chaveCesar = chaveCesar + 256;
						confirma = true;
					}
					if(chaveCesar > 256) {
						chaveCesar = chaveCesar - 256;
						confirma = true;
					}
					confirma = true;
				} else {
					if(chaveCesar != (list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i))){
						//JOptionPane.showMessageDialog(null, "chave = "+chaveCesar);
						if(chaveCesar < 0 ) {
							chaveCesar = chaveCesar + 256;
							confirma = true;
						}
						if(chaveCesar > 256) {
							chaveCesar = chaveCesar - 256;
							confirma = true;
						}
						//JOptionPane.showMessageDialog(null, "chave = "+chaveCesar);
						confirma = false;
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
