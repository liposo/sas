
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1328756640538025065L;

	public MenuFrame()
	{
		super();
		
		this.setSize(300, 300);
		this.setTitle("Criptografador");
		
		JButton buttonCesar = new JButton("Cesar");
		JButton buttonSubstituicao = new JButton("Substituição");
		JButton buttonVigenere = new JButton("Vigenère");
		JButton buttonTransposicao = new JButton("Transposição");
		
		BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		
		this.setLayout(box);
		
		buttonCesar.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonSubstituicao.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonTransposicao.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonVigenere.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(15, 15)));
		this.add(buttonCesar);
		this.add(Box.createRigidArea(new Dimension(15, 15)));		
		this.add(buttonSubstituicao);
		this.add(Box.createRigidArea(new Dimension(15, 15)));		
		this.add(buttonTransposicao);
		this.add(Box.createRigidArea(new Dimension(15, 15)));		
		this.add(buttonVigenere);
		this.add(Box.createRigidArea(new Dimension(15, 15)));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		
		buttonCesar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CesarFrame();
			}
		});
		
		
		buttonSubstituicao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SubstituicaoFrame();
			}
		});		
		
		
	}

}
