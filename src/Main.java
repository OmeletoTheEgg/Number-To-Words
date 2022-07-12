import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Main {

	private JFrame frmNumbersToWords;
	private JTextField txtNumber;
	private JTextField txtIsItA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmNumbersToWords.setVisible(true);
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
		frmNumbersToWords = new JFrame();
		frmNumbersToWords.setLocation(new Point(960, 540));
		frmNumbersToWords.setTitle("Numbers to Words");
		frmNumbersToWords.setResizable(false);
		frmNumbersToWords.setBounds(100, 100, 441, 288);
		frmNumbersToWords.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNumbersToWords.getContentPane().setLayout(new BorderLayout(0, 0));
		frmNumbersToWords.setLocationRelativeTo(null);
		
		final Background panel = new Background();
		frmNumbersToWords.getContentPane().add(panel);
		panel.setLayout(null);
		
		Font font = new Font("Arial", Font.PLAIN, 17);
		/*try { 
		    font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/RifficFree-Bold.ttf"));
		    //font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("resources/Times New Roman.ttf"));
		    
		} catch (Exception e) { 
		    e.printStackTrace();
		}*/
		txtNumber = new JTextField();
		txtNumber.setForeground(Color.WHITE);
		txtNumber.setOpaque(false);
		txtNumber.setFont(font.deriveFont(17f));
		txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumber.setText("Enter number (0-999)");
		txtNumber.setBounds(114, 110, 200, 41);
		panel.add(txtNumber);
		txtNumber.setColumns(10);
		txtNumber.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				JTextField source = (JTextField)e.getComponent();
		        source.setText("");
		        source.removeFocusListener(this);
		    }
		});
		txtNumber.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
			        e.consume();
			    } 
				if (txtNumber.getText().length() > 2) {
					txtNumber.setText("999");
			    	e.consume();
			    	
			    }
			}
		});
		
		txtNumber.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateTxtIsItA();
			}
			public void removeUpdate(DocumentEvent e) {
				updateTxtIsItA();
			}
			public void insertUpdate(DocumentEvent e) {
				updateTxtIsItA();
			}

			public void updateTxtIsItA() {
				if (!txtNumber.getText().isEmpty() && (txtNumber.getText().length() <= 3)) {
					txtIsItA.setText(Operations.numberToWords(txtNumber.getText().toCharArray()));
				} else if (txtNumber.getText().length() > 3) {
					txtIsItA.setText("Overflow");
				} else {
					txtIsItA.setText("");
				}
				
			}}
		);
		
		JLabel title_label = new JLabel("Numbers To Words");
		
		title_label.setFont(font.deriveFont(30f));
		title_label.setForeground(Color.WHITE);
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 0, 435, 132);
		panel.add(title_label);
		
		txtIsItA = new JTextField();
		txtIsItA.setEditable(false);
		txtIsItA.setOpaque(false);
		txtIsItA.setHorizontalAlignment(SwingConstants.CENTER);
		txtIsItA.setForeground(Color.WHITE);
		txtIsItA.setFont(font.deriveFont(17f));
		txtIsItA.setColumns(10);
		txtIsItA.setBounds(26, 162, 372, 41);
		panel.add(txtIsItA);
		
		JLabel creatorName = new JLabel("Created by: Moscosa, Arjhi P.");
		creatorName.setForeground(Color.WHITE);
		creatorName.setHorizontalAlignment(SwingConstants.RIGHT);
		creatorName.setBounds(235, 234, 190, 14);
		panel.add(creatorName);
		frmNumbersToWords.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, frmNumbersToWords.getContentPane(), title_label, txtNumber, txtIsItA}));
		panel.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				panel.mouseX = e.getX();
				panel.mouseY = e.getY();
			}
		});
	}
}
