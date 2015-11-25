import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
	
	private JFrame mainFrame;
	private JTextArea outputTextArea = new JTextArea();
	
	final static BigInteger one = new BigInteger("1");
	
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
		mainFrame.setTitle("Diffie-Hellman simulador de troca de chaves");
		mainFrame.setBounds(100, 100, 1000, 280);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		mainFrame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new BorderLayout());
		mainFrame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		
		JLabel a = new JLabel("Primo:");
		JLabel n = new JLabel("N:");
		JLabel pessoaA = new JLabel("Secreto A:");
		JLabel pessoaB = new JLabel("Secreto B:");
		
		JTextField numA = new JTextField();
		JTextField numN = new JTextField();
		JTextField secretoA = new JTextField();
		JTextField secretoB = new JTextField();
		
		numA.setColumns(10);
		numN.setColumns(10);
		secretoA.setColumns(10);
		secretoB.setColumns(10);
		
		JButton geraChavesBtn = new JButton("Simular");
		
		panelNorth.add(a);
		panelNorth.add(numA);
		panelNorth.add(n);
		panelNorth.add(numN);
		panelNorth.add(pessoaA);
		panelNorth.add(secretoA);
		panelNorth.add(pessoaB);
		panelNorth.add(secretoB);
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
				BigInteger bigIntA = new BigInteger(numA.getText());
				BigInteger bigIntN = new BigInteger(numN.getText());
				BigInteger bigIntSecretoA = new BigInteger(secretoA.getText());
				BigInteger bigIntSecretoB = new BigInteger(secretoB.getText());
				DiffieHellman(bigIntA, bigIntN, bigIntSecretoA, bigIntSecretoB);
			}
		});
	}
	
	public void DiffieHellman(BigInteger a, BigInteger n, BigInteger secretA, BigInteger secretB) {
		
		String ans = a.toString();
		BigInteger p = getNextPrime(ans);
		
		outputTextArea.append("O primo mais proximo do valor inserido(caso não seja primo): "+p+"\n");
		outputTextArea.append("Valor N selecionado: "+n+"\n");
		outputTextArea.append("Valor Secreto da pessoa A: "+secretA+"\n");
		outputTextArea.append("Valor Secreto da pessoa B: "+secretB+"\n");
		
		BigInteger resultA = n.modPow(secretA,p);
		outputTextArea.append("A envia para B: "+resultA+"\n");
		
		BigInteger resultB = n.modPow(secretB, p);
		outputTextArea.append("B envia para A: "+resultB+"\n");
		
		BigInteger KeyA = resultB.modPow(secretA,p);
		BigInteger KeyB = resultA.modPow(secretB,p);

		outputTextArea.append("A chave 'A': "+KeyA+"\n");
		outputTextArea.append("A chave 'B': "+KeyB+"\n");
	}
	
	public static BigInteger getNextPrime(String ans) {
		
		BigInteger teste = new BigInteger(ans);
		while (!teste.isProbablePrime(99))
			teste = teste.add(one);
		return teste;		
	}
}
