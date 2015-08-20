import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;

public class loadListFromFile {
	
	private List<Integer> fileByteIntList = new ArrayList<Integer>();
	
	byte[] bytesArray;
	
	private int fileByteslen;
	
	private String nomeOriginal;
	
	public loadListFromFile(File file) throws Exception {
		try {
			nomeOriginal = file.getAbsolutePath();
			bytesArray = FileUtils.readFileToByteArray(file);
		
			fileByteslen = bytesArray.length;
			
			for(int i=0; i<fileByteslen; i++) {
				fileByteIntList.add(Byte.toUnsignedInt(bytesArray[i]));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar lista: "+e.getMessage());
		}
	}
	
	public byte[] getByteArray() {
		return bytesArray;
	}
	
	public List<Integer> getIntList() {
		return fileByteIntList;
	}

	public int getlength() {
	 return fileByteslen;
	}
	
	public String getFileName() {
		return nomeOriginal;
	}
}
