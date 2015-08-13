import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class vigenere {
	
	public vigenere(File originalFile, File encryptedFile) throws Exception {
		loadListFromFile list = new loadListFromFile(originalFile, encryptedFile);
		
		int[] chaveVigenere = new int[list.getOriginalByteslen()];
		boolean confirma = false;
		
		Arrays.fill(chaveVigenere, 0);
		
		if(list.getOriginalByteslen() == list.getEncryptedBytesLen()) {
			for(int i=0; i<list.getOriginalByteslen(); i++) {
				if(chaveVigenere[i] == 0) {
					chaveVigenere[i] = (list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i));
					if(chaveVigenere[i] < 0) {
						chaveVigenere[i] = chaveVigenere[i] + 256;
					}
					if(chaveVigenere[i] > 255) {
						chaveVigenere[i] = chaveVigenere[i] - 256;
					}
					confirma = true;
				} else {
					if(chaveVigenere[i] != ((list.getEncryptedByteIntList().get(i) - list.getOriginalByteIntList().get(i)))%255){
						confirma = false;
						break;
					}
				}
			}
			if(confirma) {
				JOptionPane.showMessageDialog(null, "Cifra de Vigenère, chave = "+mostraChave(chaveVigenere));
			} else {
				JOptionPane.showMessageDialog(null,"Chave não identificada. Possivel que não seja Cifra de Vigenère!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Arquivos com tamanhos diferente, não é cifra de Vigenère");
		}
	}
	
	private String mostraChave(int[] chaveVigenere) {
		
//		JFrame subsFrame = new JFrame();
//		JTextArea memo = new JTextArea();
//		JScrollPane scroll = new JScrollPane(memo);
//		
//		scroll.setSize(600,300);
//		memo.setLineWrap(true);
//		memo.setWrapStyleWord(true);
//		subsFrame = new JFrame();
//		subsFrame.setTitle("Chave - Substituição");
//		subsFrame.setSize(300, 300);
//		subsFrame.setVisible(true);
//		subsFrame.setResizable(true);
//		subsFrame.setLocationRelativeTo(null);
//		subsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		subsFrame.add(scroll);
//		
		char[] chaveChar = new char[chaveVigenere.length];
	
		for(int i=0; i<chaveVigenere.length; i++) {
			//System.out.print(String.valueOf(Character.toChars(chaveVigenere[i])));
			//System.out.print(String.valueOf(chaveVigenere[i])+",");
			chaveChar[i] = (char) chaveVigenere[i];
		}
		
		String chaveString = String.valueOf(chaveChar);
		//System.out.println(chaveString);
		//System.out.println(chaveChar.length);
		//System.out.println(chaveString.length());
		
		List<String> chaveList = Arrays.asList(chaveString.split(""));
		List<Character> uniqueChars = new ArrayList<Character>();
		int i=0;
        Set<String> uniqueWords = new HashSet<String>(chaveList);
        for (String word : uniqueWords) {
            //System.out.println(word + ": " + Collections.frequency(chaveList, word));
            uniqueChars.add(word.charAt(i));
        }
//        for(int a=0; a<uniqueChars.size(); a++) {
//        	//System.out.print(uniqueChars.get(a));
//        	memo.append(uniqueChars.get(a).toString());
//        }
        String[] strArray = new String[] {chaveString};
        String chaveFinal = null;
        String repeated = null;
        for (String str : strArray) {
            repeated = str.replaceAll("(.+?)\\1+", "$1-");
            //System.out.println(repeated);
            //memo.append(repeated);
        }
        if (repeated.contains("-")) {
            // Split it.
        	String[] parts = repeated.split("-");
        	chaveFinal = parts[0]; 
        } else {
            throw new IllegalArgumentException("String " + repeated + " does not contain -");
        }
        //memo.append(chaveFinal);
        return chaveFinal;
	}
}