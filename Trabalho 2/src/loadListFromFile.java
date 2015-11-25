import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class loadListFromFile {
	
	private List<Integer> originalByteIntList = new ArrayList<Integer>();
	private List<Integer> encryptedByteIntList = new ArrayList<Integer>();
	
	private int originalByteslen;
	private int encryptedBytesLen;
	
	public loadListFromFile(File originalFile, File encryptedFile) throws Exception {
		try {
			byte[] bytesOriginalArray = FileUtils.readFileToByteArray(originalFile);
			byte[] bytesEncryptedArray = FileUtils.readFileToByteArray(encryptedFile);
		
			originalByteslen = bytesOriginalArray.length;
			encryptedBytesLen = bytesEncryptedArray.length;
			
			if(originalByteslen == encryptedBytesLen) {
				for(int i=0; i<originalByteslen; i++) {
					originalByteIntList.add(Byte.toUnsignedInt(bytesOriginalArray[i]));
					encryptedByteIntList.add(Byte.toUnsignedInt(bytesEncryptedArray[i]));
				}
			} else {
				for(int i=0; i<originalByteslen; i++) {
					originalByteIntList.add(Byte.toUnsignedInt(bytesOriginalArray[i]));
				}
				for(int j=0; j<encryptedBytesLen; j++) {
					encryptedByteIntList.add(Byte.toUnsignedInt(bytesEncryptedArray[j]));
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar lista: "+e.getMessage());
		}
	}
	
	public List<Integer> getOriginalByteIntList() {
		return originalByteIntList;
	}
	
	public List<Integer> getEncryptedByteIntList() {
		return encryptedByteIntList;
	}
	
	public int getOriginalByteslen() {
	 return originalByteslen;
	}
	
	public int getEncryptedBytesLen() {
		 return encryptedBytesLen;
	}
	
	public int[] encryptedAsPrimitiveIntArray() {
		int[] intArray = new int[getEncryptedBytesLen()];
		int value = 0;
		for(int i=0; i<getEncryptedBytesLen(); i++) {
			value = getEncryptedByteIntList().get(i);
			intArray[i] = value;
		}
		return intArray;
	}
	
	public int[] originalAsPrimitiveIntArray() {
		int[] intArray = new int[getOriginalByteslen()];
		int value = 0;
		for(int i=0; i<getOriginalByteslen(); i++) {
			value = getOriginalByteIntList().get(i);
			intArray[i] = value;
		}
		return intArray;
	}
}
