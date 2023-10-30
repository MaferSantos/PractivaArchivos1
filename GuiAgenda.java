import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GuiAgenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textTelefono;
	private JTextField textCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAgenda frame = new GuiAgenda();
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
	public GuiAgenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(227, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre ");
		lblNewLabel_1.setBounds(24, 72, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(24, 112, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setBounds(24, 158, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textNom = new JTextField();
		textNom.setBounds(89, 69, 86, 20);
		contentPane.add(textNom);
		textNom.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(80, 109, 86, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(89, 155, 86, 20);
		contentPane.add(textCorreo);
		textCorreo.setColumns(10);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// out;
				try {
					BufferedWriter	out = new BufferedWriter(new FileWriter("agenda.txt", true));
					String cadena = textNom.getText() + "," + textTelefono.getText() + "," + textCorreo.getText()+ "\n";
					out.write(cadena);
					textNom.setText("");
					textTelefono.setText("");
					textCorreo.setText("");
					out.close();
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setBounds(24, 310, 89, 23);
		contentPane.add(btnNewButton);
		
	
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(123, 298, 75, 66);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButtonBuscar = new JButton("Buscar");
		btnNewButtonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreBuscar = JOptionPane.showInputDialog("Dime el nombre a buscar");
				try {
					BufferedReader in = new BufferedReader(new FileReader("agenda.txt"));
					String linea = in.readLine();
					int indexComaUno = linea.indexOf(",",1);//trae donde termina la coma 1
					int indexComaDos = linea.indexOf(",",indexComaUno+1); //trae donde termina la coma 2. Como ya no hay otra coma solo son 2.
					String tel;
					String correo;
					String nombre = linea.substring(0,indexComaUno);
					while(!nombre.equals(nombreBuscar)&&linea!=null) {
						linea = in.readLine();
						 indexComaUno = linea.indexOf(",",1);
						 indexComaDos = linea.indexOf(",",indexComaUno+1);
						nombre = linea.substring(0,indexComaUno);
					}
					if (nombre.equals(nombreBuscar)) {
						tel = linea.substring(indexComaUno+1,indexComaDos);
						correo = linea.substring(indexComaDos+1, linea.length());
						textNom.setText(nombre);
						textTelefono.setText(tel);
						textCorreo.setText(correo);
					}
					else {
						JOptionPane.showMessageDialog(btnNewButtonBuscar, "No se encontró el contacto");
					}
					
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(btnNewButtonBuscar, "No encontré");
			}
				
			}
		});
		btnNewButtonBuscar.setBounds(208, 310, 89, 23);
		contentPane.add(btnNewButtonBuscar);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(325, 310, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
