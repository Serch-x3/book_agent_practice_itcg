package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import agents.BookBuyerAgent;

public class BookBuyerGui extends JFrame{
    private BookBuyerAgent agent;
	
    private JTextField title_field, price_field;
	
    public BookBuyerGui(BookBuyerAgent agente) {
            super(agente.getLocalName());

            agent = agente;
            JPanel main_panel = new JPanel();
            main_panel.setLayout(new GridLayout(2, 2));
            main_panel.add(new JLabel("Book title: "));
            title_field = new JTextField(15);
            main_panel.add(title_field);
            getContentPane().add(main_panel, BorderLayout.CENTER);

            JButton buy_item = new JButton("Buy book");
            buy_item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                            try {
                                    String title = title_field.getText().trim();
                                    //defining title
                                    agent.setBookTitle(title); 
                                    title_field.setText("");
                                    agent.startAgent(); 
                            }catch(Exception e) {
                                    JOptionPane.showMessageDialog(BookBuyerGui.this, "IPlease insert a valid book name","Error",JOptionPane.ERROR_MESSAGE);
                            }
                    }
            });
            JButton close_button = new JButton("Close");
            close_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                            try {
                                    dispose(); 
                                    agent.doDelete();
                            }catch(Exception e) {
                                    System.out.println("Error closing the window");
                            }
                    }
            });

            main_panel = new JPanel();
            main_panel.add(buy_item);
            main_panel.add(close_button);
            getContentPane().add(main_panel, BorderLayout.NORTH);

            addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent e) {
                agent.doDelete();
              }
            });
            setResizable(true);
    }

    public void showGui() {
      pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
      setSize(200,200);
      setLocation(((int)screenSize.getWidth() / 2) - getWidth() / 2, ((int)screenSize.getHeight()) / 2 - getHeight() / 2);
      super.setVisible(true);
    }
}
