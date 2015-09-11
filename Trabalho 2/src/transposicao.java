import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class transposicao {
	
	loadListFromFile list;
	
	int[] encArray;
	int[] oriArray;
	
	public transposicao(File originalFile, File encryptedFile) throws Exception {
		
		list = new loadListFromFile(originalFile, encryptedFile);
		encArray = list.encryptedAsPrimitiveIntArray();
		oriArray = list.originalAsPrimitiveIntArray();
		
		int chave = 1;
		int intAdd;
		
		boolean igual = false;
		
		do {
			
			List<Integer[]> decryptedfileByteIntList = new ArrayList<Integer[]>();
			List<Integer> decryptedfileByteIntListAUX = new ArrayList<Integer>();
			List<Integer> newDecryptedfileByteIntListAUX = new ArrayList<Integer>();
			
			int qtLinha = (int) Math.ceil(encArray.length / chave);
			//System.out.println("Chave:"+chave+" Linhas:"+qtLinha+System.getProperty("line.separator"));
			
			for(int i=0; i<qtLinha; i++) {
				Integer[] linha = new Integer[chave];
				decryptedfileByteIntList.add(linha);
			}
			
			int idxColuna = 0;
			int idxLinha = 0;
			
			for(int b=0; b<list.getEncryptedBytesLen(); b++) {
				Integer[] linha = decryptedfileByteIntList.get(idxLinha);
				if(list.getEncryptedByteIntList().get(b) != null && idxColuna<linha.length) {
					linha[idxColuna] = list.getEncryptedByteIntList().get(b);
				}
				idxLinha++;
				if(idxLinha >= qtLinha) {
					idxLinha = 0;
					idxColuna++;
				}
			}
		    
			for(int i=0; i<decryptedfileByteIntList.size(); i++) { 	
				Integer[] linha = decryptedfileByteIntList.get(i);
				for(int c=0; c<linha.length; c++) {
					intAdd = linha[c];
					decryptedfileByteIntListAUX.add(intAdd);
				}
			}
			
//			System.out.println(); System.out.println("--------------# Matriz #---------------------------"); System.out.println();
//			for(int i=0; i<decryptedfileByteIntList.size(); i++) {
//				System.out.println(Arrays.toString(decryptedfileByteIntList.get(i)) + System.getProperty("line.separator"));
//			}
			
			int qtZeros = list.getEncryptedBytesLen() - list.getOriginalByteslen();
			//System.out.println("Original size:"+list.getOriginalByteslen()+" DecSize:"+decryptedfileByteIntList.size()+" Dif:"+qtZeros);
			newDecryptedfileByteIntListAUX = decryptedfileByteIntListAUX.subList(0, decryptedfileByteIntListAUX.size() - qtZeros);
			
//			System.out.println("------ Array Tentativa #"+chave+" ------");
//			for(int i=0; i<newDecryptedfileByteIntListAUX.size(); i++) {
//				System.out.printf(newDecryptedfileByteIntListAUX.get(i)+" ");
//			}
//			System.out.println();
//			System.out.println("Tamanho Array:"+newDecryptedfileByteIntListAUX.size());
//			System.out.println();
//			System.out.println();
//			
//			System.out.println("------ Array Original #"+chave+" ------");
//			for(int i=0; i<oriArray.length; i++) {
//				System.out.printf(oriArray[i]+" ");
//			}
//			System.out.println();
//			System.out.println("Tamanho Array:"+oriArray.length);
//			System.out.println();
//			System.out.println();
			
			int[] intArray = new int[newDecryptedfileByteIntListAUX.size()];
			int value = 0;
			for(int i=0; i<newDecryptedfileByteIntListAUX.size(); i++) {
				value = newDecryptedfileByteIntListAUX.get(i);
				intArray[i] = value;
			}
			
			if(Arrays.equals(intArray, oriArray)) {
				igual = true;
				//System.out.println("Chave: "+chave);
				JOptionPane.showMessageDialog(null, "Chave = "+chave);
			}
			chave++;
		} while(!igual);
		
	}
	
}

