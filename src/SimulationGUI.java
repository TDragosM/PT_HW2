import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimulationGUI extends JFrame implements ActionListener {

        JTextField txtf1,txtf2,txtf3,txtf4,txtf5,txtf6,txtf7;
        JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
        JButton bt;
        JTextArea textArea;
        ArrayList<Integer> list;
        Boolean ready=false;
        JFrame frame;
        public SimulationGUI (){
            list=new ArrayList<>();
            frame=new JFrame("Multithreading");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(575,400);
            txtf1=new JTextField();//clients
            txtf1.setBounds(180,90,30,20);
            txtf2=new JTextField();//min arrival
            txtf2.setBounds(180,180,30,20);
            txtf3=new JTextField();//threads
            txtf3.setBounds(450,90,30,20);
            txtf4=new JTextField();//max arrival
            txtf4.setBounds(450,180,30,20);
            txtf5=new JTextField();//time limit
            txtf5.setBounds(300,140,30,20);
            txtf6=new JTextField();//min service
            txtf6.setBounds(180,220,30,20);
            txtf7=new JTextField();//max service
            txtf7.setBounds(450,220,30,20);
            jl1=new JLabel("Number Of Clients");
            jl1.setBounds(60,90,135,17);
            jl2=new JLabel("Number Of Threads");
            jl2.setBounds(320,90,135,17);
            jl3=new JLabel("Simulation Interval");
            jl3.setBounds(180,140,135,17);
            jl4=new JLabel("Min Arrival Time");
            jl4.setBounds(50,180,105,17);
            jl5=new JLabel("Max Arrival Time");
            jl5.setBounds(320,180,105,17);
            jl6=new JLabel("Min Service Time");
            jl6.setBounds(50,220,105,17);
            jl7=new JLabel("Max Service Time");
            jl7.setBounds(320,220,105,17);
            bt=new JButton("Start");
            bt.setBounds(200,280,135,25);
            frame.add(txtf1);
            frame.add(txtf2);
            frame.add(txtf3);
            frame.add(txtf4);
            frame.add(txtf5);
            frame.add(txtf6);
            frame.add(txtf7);
            frame.add(jl1);
            frame.add(jl2);
            frame.add(jl3);
            frame.add(jl4);
            frame.add(jl5);
            frame.add(jl6);
            frame.add(jl7);
            frame.add(bt);
            bt.addActionListener(this);
            frame.setLayout(null);
            frame.setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent event){
            Integer noOfClients=Integer.parseInt(txtf1.getText());
            Integer minArrivalTime=Integer.parseInt(txtf2.getText());
            Integer noOfThreads=Integer.parseInt(txtf3.getText());
            Integer maxArrivalTime=Integer.parseInt(txtf4.getText());
            Integer timeLimit=Integer.parseInt(txtf5.getText());
            Integer minServiceTime=Integer.parseInt(txtf6.getText());
            Integer maxServiceTime=Integer.parseInt(txtf7.getText());
            list.add(noOfClients);
            list.add(noOfThreads);
            list.add(timeLimit);
            list.add(minArrivalTime);
            list.add(maxArrivalTime);
            list.add(minServiceTime);
            list.add(maxServiceTime);
            ready=true;
        }
        private void newFrame(){

            frame=new JFrame("Multithreading");
            frame.setSize(900,900);
            textArea=new JTextArea();
            textArea.setBounds(20,20,850,850);
            frame.add(textArea);
            frame.setLayout(null);
            frame.setVisible(true);
        }
        public void refreshFrame(String string){
            textArea.setText(string);
        }
        public List<Integer> getInitValues (){

            while (!ready)
            {
                bt.setName("Start Simulation");
            }
            newFrame();
            return list;
        }
    }

