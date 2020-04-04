package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CalculatorClass extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu jMenuFile, jMenuHelp;
	private JPanel jMaster, jplBackSpace, jplControl;
	private JLabel jLabelOuput;
	private JLabel jLabelBase;
	private JButton JbnButtons[];
	private boolean firstInput = true;
	private String numStr1 = "0";
    private String numStr2 = "";
    Font f12 = new Font("Times New Roman", 0, 12);
	Font f121 = new Font("Times New Roman", 1, 12);
	private char op;
	private char op1;
	private int currentBase=10;


    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorClass frame = new CalculatorClass();
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
	public CalculatorClass() {
		currentBase = 10;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// MEnu File
        jMenuFile = new JMenu("File");

        // MEnu Help
        jMenuHelp = new JMenu("Help");

        // Menu Bar
        JMenuBar mb = new JMenuBar();
        mb.add(jMenuFile);
        mb.add(jMenuHelp);
        setJMenuBar(mb);

        // frame componant
        jMaster = new JPanel();
        jLabelOuput = new JLabel("0");
        jLabelBase = new JLabel("dec");
        // add our componante to fram
        getContentPane().add(jLabelOuput, BorderLayout.NORTH);
        getContentPane().add(jLabelBase, BorderLayout.EAST);
        jLabelBase.setForeground(Color.GREEN);
        JbnButtons = new JButton[23];
        
        for(int i = 0; i<= 9; i++) {
        	JbnButtons[i] = new JButton(String.valueOf(i));
        }
        JButton bin = new JButton("bin");
        JButton hex = new JButton("hex");
        JButton octal = new JButton("octal");
        JButton dec = new JButton("dec");
        bin.addActionListener(this);
        hex.addActionListener(this);
        octal.addActionListener(this);
        dec.addActionListener(this);
        
     // Create operator Jbuttons
     		JbnButtons[10] = new JButton("+/-");
     		JbnButtons[11] = new JButton(".");
     		JbnButtons[12] = new JButton("=");
     		JbnButtons[13] = new JButton("/");
     		JbnButtons[14] = new JButton("*");
     		JbnButtons[15] = new JButton("-");
     		JbnButtons[16] = new JButton("+");
     		JbnButtons[17] = new JButton("sqrt");
     		JbnButtons[18] = new JButton("(1/x)");
     		JbnButtons[19] = new JButton("%");
     		jplBackSpace = new JPanel();
     		jplBackSpace.setLayout(new GridLayout(1, 1, 2, 2));
     		JbnButtons[20] = new JButton("Backspace");
     		jplBackSpace.add(JbnButtons[20]);
     		jplControl = new JPanel();
     		jplControl.setLayout(new GridLayout(1, 2, 2, 2));
     		JbnButtons[21] = new JButton(" CE ");
     		JbnButtons[22] = new JButton("C");
     		jplControl.add(bin);
     		jplControl.add(hex);
     		jplControl.add(octal);
     		jplControl.add(dec);
     		jplControl.add(JbnButtons[21]);
     		jplControl.add(JbnButtons[22]);
     		//		Setting all Numbered JButton's to Blue. The rest to Red
     		for (int i = 0; i < JbnButtons.length; i++) {
     			JbnButtons[i].setFont(f12);
     			if (i < 10)
     				JbnButtons[i].setForeground(Color.blue);
     			else
     				JbnButtons[i].setForeground(Color.red);
     		}
        
        JPanel jPLButtons = new JPanel();
        jPLButtons.setLayout(new GridLayout(4, 5, 2, 2));
        
        // add button to the jPLButtons
        for(int i = 7; i<=9; i++) {
        	jPLButtons.add(JbnButtons[i]);
        }
        
        
     // add button / and sqrt
     		jPLButtons.add(JbnButtons[13]);
     		jPLButtons.add(JbnButtons[17]);
     		// Second row
     		for (int i = 4; i <= 6; i++) {
     			jPLButtons.add(JbnButtons[i]);
     		}
     		// add button * and x^2
     		jPLButtons.add(JbnButtons[14]);
     		jPLButtons.add(JbnButtons[18]);
     		// Third row
     		for (int i = 1; i <= 3; i++) {
     			jPLButtons.add(JbnButtons[i]);
     		}
     		//adds button - and %
     		jPLButtons.add(JbnButtons[15]);
     		jPLButtons.add(JbnButtons[19]);
     		//Fourth Row
     		// add 0, +/-, ., +, and =
     		jPLButtons.add(JbnButtons[0]);
     		jPLButtons.add(JbnButtons[10]);
     		jPLButtons.add(JbnButtons[11]);
     		jPLButtons.add(JbnButtons[16]);
     		jPLButtons.add(JbnButtons[12]);
        // JPANEL MASET
        jMaster.setLayout(new BorderLayout());
        jMaster.add(jPLButtons, BorderLayout.SOUTH);
        jMaster.add(jplBackSpace, BorderLayout.WEST);
        jMaster.add(jplControl, BorderLayout.EAST);
        
        
        
        // add componant to frame
        getContentPane().add(jMaster, BorderLayout.SOUTH);
        requestFocus();
        
        //add listenner to button
        for(int i = 0; i< JbnButtons.length; i++) {
        	JbnButtons[i].addActionListener(this);
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String resultStr;                                   
        String str = String.valueOf(e.getActionCommand());  

        char ch = str.charAt(0);                            
        switch(ch)                                          
        {
        case '0': case '1': case '2':                       
        case '3': case '4': case '5':
        case '6': case '7': case '8':
        case '9':case'.': if(firstInput)
                  {
                     numStr1 = numStr1 + ch;
                     jLabelOuput.setText(baseConversion(numStr1,10,currentBase));
                  }
                  else
                  {
                      numStr2 = numStr2 + ch;
                      jLabelOuput.setText(baseConversion(numStr2,10,currentBase));
                  }
                  break;
        case '+': case '-': case '*':                       //Step 4b
        case '/':case '%': op = ch;
        		jLabelOuput.setText("0");
                  firstInput = false;
                  break;
        case '=':
        		if(numStr1 !=""&&numStr2!="") {
        		  resultStr = evaluate(); 
        //Step 4c
        		  if(currentBase != 10)
        			  jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(resultStr)),10,currentBase));
        		  else {
        			  System.out.println(currentBase);
        			  jLabelOuput.setText(resultStr);
        		  }
                  numStr1 = resultStr;
                  numStr2 = "";
                  firstInput = false;
        		}
                  break;
        case 's':
        	op1=ch;
        	if(numStr2=="")
            {
               numStr1=ft_sqrt(numStr1);
               jLabelOuput.setText(numStr1);
            }
            else
            {
                numStr2=ft_sqrt(numStr2);
                jLabelOuput.setText(numStr2);
            }
        	jLabelBase.setText("dec");
            break;
        case 'C': jLabelOuput.setText("0");                  //Step 4c
                  numStr1 = "";
                  numStr2 = "";
                  firstInput = true;
                  break;
        case '(':
        	op1=ch;
        	if(numStr2=="")
            {
               numStr1=ft_swip(numStr1);
               jLabelOuput.setText(numStr1);
            }
            else
            {
                numStr2=ft_swip(numStr2);
                jLabelOuput.setText(numStr2);
            }
            break;
        case 'B':
        	if(numStr2=="")
            {
        		if(numStr1.length()!=0) {
                    numStr1=numStr1.substring(0, numStr1.length()-1);
                    jLabelOuput.setText(numStr1);
            		if(numStr1.length()==0) {
            			jLabelOuput.setText("0");
            		}
        		}
            }
            else
            {
            	if(numStr2.length()==0) {
                    numStr2=numStr2.substring(0, numStr2.length()-1);
                    jLabelOuput.setText(numStr2);
                    if(numStr2.length()!=0) {
                		jLabelOuput.setText("0");
                    }
            	}
            }
        case 'd':
        	if(numStr2=="")
            {
               jLabelOuput.setText(numStr1);
            }
            else
            {
            	jLabelOuput.setText(numStr2);
            }
        	currentBase = 10;
        	System.out.println();
        	jLabelBase.setText("dec");

        	break;
        case 'h':

        	if(numStr2=="")
            {
               jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(numStr1)),10,16));
            }
            else
            {
            	jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(numStr2)),10,16));
            }
        	currentBase = 16;
        	jLabelBase.setText("hex");
        	break;
        case 'o':
        	if(numStr2=="")
            {
               jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(numStr1)),10,8));
            }
            else
            {
            	jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(numStr2)),10,8));
            }
        	currentBase = 8;
        	jLabelBase.setText("octal");
        	break;
        case 'b':
        	if(numStr2=="")
            {
               jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(numStr1)),10,2));
            }
            else
            {
            	jLabelOuput.setText(baseConversion(Integer.toString((int)Double.parseDouble(numStr2)),10,2));
            }
        	currentBase = 2;
        	jLabelBase.setText("binary");
        	break;
        }

        
	}
	
	private String evaluate() {
		double resultat = 0;
		double x = Double.parseDouble(numStr1);
		double y = Double.parseDouble(numStr2);;
		
		
		switch(op)                                          
        {
        case '+':  resultat = x + y; break;
        case '-':  resultat = x - y;  break;
        case '*':   resultat = x * y; break;
        case '/':   resultat = x / y; break;
        case '%':   resultat = x % y; break;
        }
			return String.valueOf(resultat);
	}
	
	private String ft_sqrt(String numStr) {
		double x = Double.parseDouble(numStr);
		return String.valueOf(Math.sqrt(x));
	}
	
	private String ft_swip(String numStr) {
		double x = Double.parseDouble(numStr);
		return String.valueOf(1/x);
	}
	
    public static String baseConversion(String number, int sBase, int dBase) 
	{ 
		return Integer.toString( Integer.parseInt(number, sBase), dBase);
	} 

}
