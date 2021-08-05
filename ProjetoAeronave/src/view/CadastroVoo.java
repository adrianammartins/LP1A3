package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Aviao;
import model.Voo;

public class CadastroVoo extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5066625916468167705L;
	private JTextField nro;
	private JTextField data;
	private JTextField hora;
	private JComboBox comboBoxAviao;
	private static List<Aviao> listAviao;
	private static List<Voo> listVoo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVoo frame = new CadastroVoo(listVoo, listAviao);
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
	public CadastroVoo(List<Voo> listaVoo, List<Aviao> listaAviao) {
		listVoo = listaVoo;
		listAviao = listaAviao;

		setTitle("Cadastro de V\u00F4o");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 621, 421);
		getContentPane().setLayout(null);
		
		comboBoxAviao = new JComboBox();
		comboBoxAviao.setBounds(104, 50, 443, 26);
		getContentPane().add(comboBoxAviao);
		
		for (Aviao aviao : listaAviao) {
			comboBoxAviao.addItem(aviao);
		}
		
		JLabel lblAeronave = new JLabel("Aeronave: ");
		lblAeronave.setBounds(15, 52, 87, 23);
		getContentPane().add(lblAeronave);
		
		JLabel lblNmero = new JLabel("N\u00FAmero: ");
		lblNmero.setBounds(15, 109, 69, 20);
		getContentPane().add(lblNmero);
		
		nro = new JTextField();
		nro.setBounds(103, 106, 146, 26);
		getContentPane().add(nro);
		nro.setColumns(10);
		
		JLabel lblData = new JLabel("Data: ");
		lblData.setBounds(15, 170, 69, 20);
		getContentPane().add(lblData);
		
//		data = new JTextField();
		try {
			data = new JFormattedTextField(new MaskFormatter("##/##/####"));
			hora = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Deu erro ao colocar as mascaras nos campo data e hora");
		}

		data.setBounds(104, 167, 146, 26);
		getContentPane().add(data);
		data.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora: ");
		lblHora.setBounds(15, 238, 69, 20);
		getContentPane().add(lblHora);
		
		hora.setBounds(103, 235, 146, 26);
		getContentPane().add(hora);
		hora.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (comboBoxAviao.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Aeronave não preenchida!");
					return;
				}

				if (nro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Número não preenchido!");
					return;
				}else {
					try{
						Integer.valueOf(nro.getText().trim()).intValue();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Número inválido!");
						e.printStackTrace();
						return;
					}
				}
				if (validaData()) {
					JOptionPane.showMessageDialog(null, "Data não preenchida ou inválida");
					return;
				}
				if (validaHora()){
					JOptionPane.showMessageDialog(null, "Hora não preenchida ou inválida!");
					return;
				}
				
				//GUARDANDO NA LISTA DE VOO's
				try{
					Voo voo = new Voo((Aviao)comboBoxAviao.getSelectedItem(), Integer.valueOf(nro.getText().trim()), data.getText(), hora.getText());
					listVoo.add(voo);
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
					
				} catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro na criação do Vôo - Valores não permitidos");
				} finally {
					nro.setText("");
					data.setText("");
					hora.setText("");
				}
			}
			
		});
		btnNewButton.setBounds(432, 320, 115, 29);
		getContentPane().add(btnNewButton);
		
		JLabel lblDdmmyyyy = new JLabel("dd/MM/yyyy");
		lblDdmmyyyy.setBounds(270, 170, 115, 20);
		getContentPane().add(lblDdmmyyyy);
		
		JLabel lblHhmm = new JLabel("HH:mm");
		lblHhmm.setBounds(270, 241, 69, 20);
		getContentPane().add(lblHhmm);

	}
	
	private boolean validaHora() {
		boolean erro = false;
		if (hora.getText().isEmpty()) {
			return true;
		}
		
		try
		{
			SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm");
			sdf.setLenient(false);
			sdf.parse(hora.getText());
		}
		catch(ParseException e)
		{
			e.printStackTrace();
			erro = true;
		}
		return erro;
	}
	
	private boolean validaData() {
		boolean erro = false;
		if (data.getText().isEmpty()) {
			return true;
		}
		
		try
		{
			SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			sdf.parse(data.getText());
		}
		catch(ParseException e)
		{
			e.printStackTrace();
			erro = true;
		}
		
		return erro;
	}
}
