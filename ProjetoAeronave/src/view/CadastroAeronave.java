package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Aviao;

public class CadastroAeronave extends JInternalFrame {
	
	private static final long serialVersionUID = 5740693564680205033L;

	private JTextField modelo;
	private JTextField fileiras;
	private JTextField assentos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAeronave frame = new CadastroAeronave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroAeronave() {
		setMaximizable(true);
		setClosable(true);
		setTitle("Cadastro de Aeronave");
		setBounds(100, 100, 541, 349);
		getContentPane().setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(15, 16, 57, 20);
		getContentPane().add(lblModelo);
		
		modelo = new JTextField();
		modelo.setBounds(74, 13, 423, 26);
		getContentPane().add(modelo);
		modelo.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (modelo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Modelo do Avião não preenchido!");
					return;
				}
				if (fileiras.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fileiras do Avião não preenchido!");
					return;
				}
				if (assentos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Assentos do Avião não preenchido!");
					return;
				}
				
				try{
					int linhas = Integer.valueOf(fileiras.getText()).intValue();
					int colunas = Integer.valueOf(assentos.getText()).intValue();

					//TODO COMO LEVAR O OBJETO AVIAO CRIADO PARA TELA PRINCIPAL
					Aviao aviao = new Aviao(modelo.getText(), linhas, colunas);
					
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
					
				} catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro na crição da Aeronave - Valores não permitidos");
				} finally {
					modelo.setText("");
					fileiras.setText("");
					assentos.setText("");
				}
				
				
			}
		});
		
		btnCadastrar.setBounds(383, 109, 115, 29);
		getContentPane().add(btnCadastrar);
		
		JLabel label = new JLabel("Fileiras:");
		label.setBounds(15, 68, 57, 20);
		getContentPane().add(label);
		
		fileiras = new JTextField();
		fileiras.setBounds(74, 65, 146, 26);
		getContentPane().add(fileiras);
		fileiras.setColumns(10);
		
		JLabel lblAssentos = new JLabel("Assentos:");
		lblAssentos.setBounds(271, 68, 69, 20);
		getContentPane().add(lblAssentos);
		
		assentos = new JTextField();
		assentos.setBounds(351, 67, 146, 26);
		getContentPane().add(assentos);
		assentos.setColumns(10);

	}
}
