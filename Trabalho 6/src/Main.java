import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
	
	private JFrame mainFrame;
	private JTextArea outputTextArea = new JTextArea();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainFrame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		initialize();
	}
	
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Calculator5000");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		mainFrame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		mainFrame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());
		mainFrame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		
		JLabel valorX = new JLabel("X: ");
		JLabel valorY = new JLabel("Y: ");
		
		JTextField numX = new JTextField();
		JTextField numY = new JTextField();
		
		numX.setColumns(20);
		numY.setColumns(20);
		
		JButton somaBtn = new JButton("Soma");
		JButton multBtn = new JButton("Multiplicação");
		JButton expoBtn = new JButton("Exponenciação");
		JButton InMultBtn = new JButton("Inverso Multiplicativo");
		
		panelNorth.add(valorX);
		panelNorth.add(numX);
		panelNorth.add(valorY);
		panelNorth.add(numY);
		
		panelCenter.add(somaBtn);
		panelCenter.add(multBtn);
		panelCenter.add(expoBtn);
		panelCenter.add(InMultBtn);
		
		outputTextArea.setEditable(false);
		outputTextArea.setVisible(true);
		outputTextArea.setRows(10);
		outputTextArea.setBackground(Color.lightGray);
		
		panelSouth.add(outputTextArea);
		mainFrame.pack();
		
		somaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTextArea.setText(null);
				if(verificaCampos(numX, numY)) {
					outputTextArea.append("Campo sem valor ou ambos os valores iguais a zero.");
				}
				if(verificaCampos(numX, numY) == false) {
					BigInt bIntX = new BigInt(numX.getText());
				    BigInt bIntY = new BigInt(numY.getText());
				    BigInt bIntResultado = bIntX.add(bIntY);
				    outputTextArea.append("Resultado com implementação do BigInt: \n");
				    outputTextArea.append(bIntX.toString()+" + "+bIntY.toString()+" = "+bIntResultado.toString()+"\n");
					
				    outputTextArea.append("\n ------------------------------------------------------------------ \n\n");
				    
					BigInteger bigIntX = new BigInteger(numX.getText());
					BigInteger bigIntY = new BigInteger(numY.getText());
					BigInteger resultadoSoma = bigIntX.add(bigIntY);
					outputTextArea.append("Resultado com java.math.BigInteger: \n");
					outputTextArea.append(bigIntX+" + "+bigIntY+" = "+resultadoSoma+"\n");
				}
			}
		});
		
		multBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTextArea.setText(null);
				if(verificaCampos(numX, numY)) {
					outputTextArea.append("Campo sem valor ou ambos os valores iguais a zero.");
				}
				if(verificaCampos(numX, numY) == false) {
				    String bIntResultado = multiplica(numX.getText().toString(), numY.getText().toString());
				    outputTextArea.append("Resultado com String simulando BigInt: \n");
				    outputTextArea.append(numX.getText().toString()+" * "+numY.getText().toString()+" = "+bIntResultado+"\n");
					
				    outputTextArea.append("\n ------------------------------------------------------------------ \n\n");
				    
					BigInteger bigIntX = new BigInteger(numX.getText());
					BigInteger bigIntY = new BigInteger(numY.getText());
					BigInteger resultadoSoma = bigIntX.multiply(bigIntY);
					outputTextArea.append("Resultado com java.math.BigInteger: \n");
					outputTextArea.append(bigIntX+" * "+bigIntY+" = "+resultadoSoma+"\n");
				}
			}
		});

		expoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTextArea.setText(null);
				if(verificaCampos(numX, numY)) {
					outputTextArea.append("Campo sem valor ou ambos os valores iguais a zero.");
				}
				if(verificaCampos(numX, numY) == false) {
					BigInteger bigIntX = new BigInteger(numX.getText());
					BigInteger bigIntY = new BigInteger(numY.getText());
					BigInteger resultadoExpo = expo(bigIntX, bigIntY);
					outputTextArea.append(bigIntX+" ^ "+bigIntY+" = "+resultadoExpo+"\n");
				}
			}
		});

		InMultBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTextArea.setText(null);
				if(verificaCampos(numX, numY)) {
					outputTextArea.append("Campo sem valor ou ambos os valores iguais a zero.");
				}
					if(verificaCampos(numX, numY) == false) {
					BigInteger bigIntX = new BigInteger(numX.getText());
					BigInteger bigIntY = new BigInteger(numY.getText());
					BigInteger resultadoModInv = modInv(bigIntX, bigIntY);
					outputTextArea.append(bigIntX+" ^-1 mod "+bigIntY+" = "+resultadoModInv+"\n");
				}
			}
		});
	}
	
	public boolean verificaCampos(JTextField numX, JTextField numY) {
		if(numX.getText().isEmpty() || numY.getText().isEmpty()) {
			return true;
		} else if(numX.getText().equals('0') && numY.getText().equals('0')) {
			return false;
		} else {
			return false;
		}
		
	}
	
	public BigInteger expo(BigInteger base, BigInteger exponente) {
		BigInteger resultado = BigInteger.ONE;
		
		while (exponente.signum() > 0) {
			if (exponente.testBit(0)) resultado = resultado.multiply(base);
			base = base.multiply(base);
			exponente = exponente.shiftRight(1);
		}
		return resultado;
	}
	
	public BigInteger modInv(BigInteger x, BigInteger y) {
		BigInteger b0 = x;
		BigInteger n0 = y;
		BigInteger t0 = BigInteger.ZERO;
		BigInteger t = BigInteger.ONE;
		BigInteger q = y.divide(b0);
		BigInteger r = n0.subtract(q.multiply(b0));
		
		while(r.compareTo(BigInteger.ZERO) > 0) {
			BigInteger temp = t0.subtract(q.multiply(t));
			
			if(temp.compareTo(BigInteger.ZERO) > 0)
			  temp = temp.mod(y);
			if(temp.compareTo(BigInteger.ZERO) < 0)
			  temp = y.subtract(temp.negate().mod(y));
			
			t0 = t;
			t = temp;
			n0 = b0;
			b0 = r;
			q = n0.divide(b0);
			r = n0.subtract(q.multiply(b0));
		}
		
		if(!b0.equals(BigInteger.ONE)) {
			return null;
		} else {
		 	return t.mod(y);
		}
	}
	
	public String multiplica(String numX, String numY) {
        int product = 0;
        int carry = 0;
        int sum = 0;
        
        String result = new String("");
        String partial = new String("");
        
        ArrayList<String> partialList = new ArrayList<String>();

        for(int j=numY.length()-1 ; j>=0 ; j--) {
            for(int i=numX.length()-1 ; i>=0 ; i--) {       
                product = Integer.parseInt((new Character(numX.charAt(i))).toString()) * Integer.parseInt((new Character(numY.charAt(j))).toString()) + carry;               
                carry = product/10;
                partial = Integer.toString(product%10) + partial;               
            }       
            if(carry != 0) {
                partial = Integer.toString(carry) + partial;
            }
            partialList.add(partial);
            partial = "";
            carry = 0;
        }                           

        for(int i=0 ; i<partialList.size() ; i++) {
            partialList.set(i, partialList.get(i) + (Long.toString((long)java.lang.Math.pow(10.0,(double)i))).substring(1));        
        }
        
        int largestPartial = partialList.get(partialList.size()-1).length();

        int zeroes;
        for(int i=0 ; i<partialList.size() ; i++) {
            zeroes =  largestPartial - partialList.get(i).length();
            if(zeroes >= 1)
            partialList.set(i, (Long.toString((long)java.lang.Math.pow(10.0,(double)zeroes))).substring(1) + partialList.get(i));
        }

        carry = 0;
        for(int i=largestPartial-1 ; i>=0 ; i--) {
            sum = 0;
            for(int j=0 ; j<partialList.size() ; j++) {
                sum = sum + Integer.parseInt(new Character(partialList.get(j).charAt(i)).toString());
            }
            sum = sum + carry;
            carry = sum/10;         
            result = Integer.toString(sum%10) + result;     
        }

        if(carry != 0) {
            result = Integer.toString(carry) + result;
        }
        return result;
    }
}
