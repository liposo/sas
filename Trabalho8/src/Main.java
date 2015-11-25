import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

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
		mainFrame.setTitle("Gerador de chave RSA");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		mainFrame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());
		mainFrame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		
		JLabel labelChaveSize = new JLabel("Tamanho da Chave: ");
		
		JSpinner tamanhoChave = new JSpinner(new SpinnerNumberModel(3, 3, Integer.MAX_VALUE, 1));
		
		JButton geraChavesBtn = new JButton("Gerar");
		
		panelNorth.add(labelChaveSize);
		panelNorth.add(tamanhoChave);
		panelNorth.add(geraChavesBtn);
		
		outputTextArea.setEditable(false);
		outputTextArea.setVisible(true);
		outputTextArea.setRows(10);
		outputTextArea.setBackground(Color.lightGray);
		
		panelSouth.add(outputTextArea);
		mainFrame.pack();
		
		geraChavesBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextArea.setText(null);
				int tamanho = (int) tamanhoChave.getValue();
				geradorChave(tamanho);
			}
		});
	}
	
	public static BigInteger getCoprimo(BigInteger m) {
		Random rand = new Random();
		
		int tamanho = m.bitLength()-1;
		
		BigInteger e = BigInteger.probablePrime(tamanho, rand);
		
		while (!(m.gcd(e)).equals(BigInteger.ONE)) {
			e = BigInteger.probablePrime(tamanho, rand);
		}
		return e;
	}
	
	public void geradorChave(int tamanhoChave) {
		
		Random rnd = new Random();
		
		BigInteger e = BigInteger.ZERO;
		BigInteger d = BigInteger.ZERO;
		
		
		//1
		BigInteger p = BigInteger.probablePrime(tamanhoChave, rnd);
		BigInteger q = BigInteger.probablePrime(tamanhoChave, rnd);
		
		//2
		BigInteger n = p.multiply(q);
		
		//3
		BigInteger euler = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		//4
		BigInteger x = getCoprimo(euler);
		
		do {
			if(euler.gcd(x).equals(BigInteger.ONE)) {
				e = x;
				//5
				d = gcdExt(e, euler);
				
				if(d.signum() == 1) {
					break;
				}
			}
			x = x.add(BigInteger.ONE);
		} while(true);
		
		outputTextArea.append("Chave Pública mod(N): "+n+"\n");
		outputTextArea.append("Chave Pública (e): "+e+"\n\n");
		outputTextArea.append("Chave privada (d): "+d+"\n");
		
//	    BigInteger p = BigInteger.probablePrime(tamanhoChave/2, rnd);
//	    BigInteger q = p.nextProbablePrime();
//	    BigInteger n = p.multiply(q);
//	    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
//	    BigInteger e = getCoprimo(m);
//	    BigInteger d = e.modInverse(m);
//	    
//	    outputTextArea.append("Chave Pública: "+e+"\n");
//	    outputTextArea.append("Mod(N): "+n+"\n");
//	    outputTextArea.append("Chave Privada: "+d+"\n");

	}
	
	public static BigInteger gcdExt(BigInteger a, BigInteger b) {
		BigInteger aAtual = BigInteger.ONE;
		BigInteger bAtual = BigInteger.ZERO;
		BigInteger aProximo = BigInteger.ZERO;
		BigInteger bProximo = BigInteger.ONE;
		BigInteger q, r;
		
		do {
			q = a.divide(b);
			r = a.subtract(q.multiply(b));
			
			BigInteger AUX_A = aProximo;
			BigInteger AUX_B = bProximo;
			
			aProximo = aAtual.subtract(q.multiply(aProximo));
			bProximo = bAtual.subtract(q.multiply(bProximo));
			
			aAtual = AUX_A;
			bAtual = AUX_B;
			
			a = b;
			b = r;
		} while(r.intValue()>0);
		
		return aAtual;
	}
}
