import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frmShowMeThe;
	
	private File file;
	
	JLabel nomeArquivo = new JLabel();
	
	private int mode;
	
	private boolean fileSelected = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmShowMeThe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShowMeThe = new JFrame();
		frmShowMeThe.setResizable(false);
		frmShowMeThe.setTitle("CriptMaster5000");
		frmShowMeThe.setBounds(100, 100, 450, 150);
		frmShowMeThe.setLocationRelativeTo(null);
		frmShowMeThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShowMeThe.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		frmShowMeThe.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSouth.getLayout();
		frmShowMeThe.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);
		
		JButton selectOriginalFileBtn = new JButton("Select File");
		selectOriginalFileBtn.setToolTipText("Select the original file");
		
		panelNorth.add(selectOriginalFileBtn);
		panelNorth.add(nomeArquivo);
		
		JButton cesarBtn = new JButton("C\u00E9sar");
		cesarBtn.setEnabled(false);
		panelSouth.add(cesarBtn);
		
		JButton subsBtn = new JButton("Substitui\u00E7\u00E3o");
		subsBtn.setEnabled(false);
		panelSouth.add(subsBtn);
		
		JButton transpBtn = new JButton("Transposi\u00E7\u00E3o");
		transpBtn.setEnabled(false);
		panelSouth.add(transpBtn);
		
		JButton vigBtn = new JButton("Vigen\u00E8re");
		vigBtn.setEnabled(false);
		panelSouth.add(vigBtn);
		
		selectOriginalFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFileOriginal();
				if(fileSelected) {
					cesarBtn.setEnabled(true);
					subsBtn.setEnabled(true);
					transpBtn.setEnabled(true);
					vigBtn.setEnabled(true);
				}
			}
		});
		
		cesarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new cesar(file, mode);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		subsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new substituicao(file, mode);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		transpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new transposicao(file, mode);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		vigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new vigenere(file, mode);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void openFileOriginal() 
	{
		try
		{
			JFileChooser fc = new JFileChooser(".");
		
			if (fc.showOpenDialog(frmShowMeThe) == 0)
			{
				file = fc.getSelectedFile();
				nomeArquivo.setText(file.getName());
				if(file.getAbsolutePath().endsWith(".cesar") || file.getAbsolutePath().endsWith(".subs") || 
						file.getAbsolutePath().endsWith(".transp") || file.getAbsolutePath().endsWith(".vig")) {
					mode = 2;
				} else {
					mode = 1;
				}
				fileSelected = true;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frmShowMeThe, "Deu pau: "+ex.getMessage());
		}
		JOptionPane.showMessageDialog(frmShowMeThe, "Arquivo: "+file.getName());
	}
}
