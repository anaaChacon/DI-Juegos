import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class Reloj extends JPanel {

    private static final long serialVersionUID = 3545053785228009472L;

    // GUI Components
    private JPanel panel;
    private JLabel timeLabel;

 

    // Properties of Program.
    private byte centiseconds = 0;
    private byte seconds = 30;
    private short minutes = 0;

    private DecimalFormat timeFormatter;

    private Timer timer;
    private boolean parado = false;

    public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}
	
	  public Timer isTimer() {
			return timer;
		}

		public void setTimer1(Timer timer) {
			this.timer = timer;
		}

	public Reloj() {
        //panel = new JPanel();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#404040"));

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("MS UI Gothic", 1, 48));// Monospaced
        timeLabel.setForeground(Color.green);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timeLabel, BorderLayout.WEST);

     
        timeFormatter = new DecimalFormat("00");
        
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centiseconds > 0) {
                    centiseconds--;
                } else {
                    if (seconds == 0 && minutes == 0) {
                    	
                    	timer.stop();
                        parado = true;
                        
                    	if(centiseconds == 0){
                    		JOptionPane.showMessageDialog(null, "Su puntuación ha sido: ", "Tiempo", JOptionPane.INFORMATION_MESSAGE);	
                    	}
                        
                    } else if (seconds > 0) {
                    	
                        seconds--;
                        centiseconds = 99;
                        if(seconds == 5){
                        	timeLabel.setForeground(Color.red);
                        	
                        }
                    } else if (minutes > 0) {
                    	
                        minutes--;
                        seconds = 59;
                        centiseconds = 99;
                    }
                }
                
                timeLabel.setText(timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds) + ":"
                        + timeFormatter.format(centiseconds));
                
               
            }
            
        });
        
		
        timer.start();
        setVisible(true);
   }

    public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	
	/*public static void main(String[] args) {
       EventQueue.invokeLater(new Runnable() {
           public void run() {
               try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                //new Reloj();
           }
       });
    }*/
}