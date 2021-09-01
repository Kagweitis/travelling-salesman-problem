/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Kagwe.routes;




import static com.mycompany.Kagwe.routes.Graph.log3;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;



class Routes 
    extends JFrame
       implements ActionListener 
{ 
    //variables and jcomponents declaration
    private final Container container; 
    private final JLabel title; 
    private final JLabel sourceTown;
    private final JLabel destinationTown;
    private final JLabel method; 
    private final JLabel map_image;
    private final JLabel cities;
    private final JLabel output; 
    private final JComboBox itsSourceTown;
    private final JComboBox itsDestinationTown;
     private final JRadioButton itsA_search;
    private final JRadioButton itsBreadthFirst;
    private final JRadioButton itsDepthFirst;
    private final ButtonGroup chooser; 
    private final JButton search;
    private final JButton reset; 
    private final JTextArea itsOutput; 
    private final JPanel itsMap2;
    private final ImageIcon background;
    private static final String[] towns = {"Nairobi", "Kisumu", "Malindi", "Meru",
   "Garissa", "Lamu"};
    private static String source, destination, logs = "", optimization_method = "";
    private int sourcetwn, dest;
   
  
    //constructor
    public Routes() 
    { 
        setTitle("Find a name"); 
        setBounds(200, 90, 1040, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 
  
        container = getContentPane(); 
        container.setLayout(null); 
        
        title = new JLabel("KBS Assignment");
        title.setFont(new Font("Times New Romans", Font.PLAIN, 30));
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        container.add(title); 
  
        sourceTown = new JLabel("Source Town: "); 
        sourceTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sourceTown.setSize(200, 20); 
        sourceTown.setLocation(50, 360); 
        container.add(sourceTown); 
        
        itsSourceTown = new JComboBox(towns); 
        itsSourceTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsSourceTown.setSize(200, 20); 
        itsSourceTown.setLocation(200, 360); 
        container.add(itsSourceTown); 
  
        destinationTown = new JLabel("Destination Town: "); 
        destinationTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        destinationTown.setSize(200, 20); 
        destinationTown.setLocation(50, 390); 
        container.add(destinationTown); 
        
        itsDestinationTown = new JComboBox(towns); 
        itsDestinationTown.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsDestinationTown.setSize(200, 20); 
        itsDestinationTown.setLocation(200, 390); 
        container.add(itsDestinationTown); 
        
  
        method = new JLabel("Search Method: "); 
        method.setFont(new Font("Arial", Font.PLAIN, 15)); 
        method.setSize(200, 20); 
        method.setLocation(50, 420); 
        container.add(method);
        
        itsA_search = new JRadioButton ("A* Search", true); 
        itsA_search.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsA_search.setSize(250, 20); 
        itsA_search.setLocation(50, 450); 
        container.add(itsA_search);
        
        
        itsBreadthFirst = new JRadioButton ("BreadthFirst", true); 
        itsBreadthFirst.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsBreadthFirst.setSize(200, 20); 
        itsBreadthFirst.setLocation(50, 480); 
        container.add(itsBreadthFirst);
        
        itsDepthFirst = new JRadioButton ("DepthFirst", true); 
        itsDepthFirst.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsDepthFirst.setSize(110, 20); 
        itsDepthFirst.setLocation(50, 510); 
        container.add(itsDepthFirst); 
  
        
        //button group for radio buttons
        chooser = new ButtonGroup(); 
        chooser.add(itsA_search); 
        chooser.add(itsBreadthFirst);
        chooser.add(itsDepthFirst);
  
        search = new JButton("Search"); 
        search.setFont(new Font("Arial", Font.PLAIN, 15)); 
        search.setSize(100, 25); 
        search.setLocation(50, 550); 
        search.addActionListener(this); 
        container.add(search); 
  
        reset = new JButton("Reset"); 
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setSize(100, 25); 
        reset.setLocation(170, 550); 
        reset.addActionListener(this); 
        container.add(reset); 
        
        map_image = new JLabel("Kenyan Cities: "); 
        map_image.setFont(new Font("Arial", Font.PLAIN, 15)); 
        map_image.setSize(200, 20); 
        map_image.setLocation(100, 240); 
        //container.add(map_image);
        
        background=new ImageIcon("cities.jpg");
        cities = new JLabel(background);
        cities.setSize(background.getIconWidth(),background.getIconHeight());
        cities.setLocation(50, 70);
        itsMap2 = (JPanel)this.getContentPane();
        itsMap2.setOpaque(false);
        this.getLayeredPane().add(cities,new Integer(Integer.MIN_VALUE));
        
        output = new JLabel("Output Panel: "); 
        output.setFont(new Font("Arial", Font.PLAIN, 15)); 
        output.setSize(200, 20); 
        output.setLocation(420, 350); 
        container.add(output);
        
        itsOutput = new JTextArea(); 
        itsOutput.setFont(new Font("Arial", Font.PLAIN, 15)); 
        itsOutput.setSize(330, 200); 
        itsOutput.setLocation(420, 380); 
        itsOutput.setLineWrap(true); 
        itsOutput.setEditable(false); 
        container.add(itsOutput); 
        
        
        setVisible(true); 
    }  
    
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
        //submit button clicked
        if (e.getSource() == search) 
        {
            source = (String)itsSourceTown.getSelectedItem();
            destination = (String)itsDestinationTown.getSelectedItem();
            if (source.equals(destination))
            {
                logs = " ERROR: Source and Destination towns can't be    the same";
                itsOutput.setText(logs);
                itsOutput.setEditable(false);  
            }
            else
            {
                int V = 6;  
                int sourcetwn = 0;
                int dest = 0;
                if(("Nairobi".equals(source))){sourcetwn = 0;}
                else if(("Kisumu".equals(source))){sourcetwn = 1;}
                else if(("Meru".equals(source))){sourcetwn = 2;}
                else if(("Garissa".equals(source))){sourcetwn = 3;}
                else if(("Malindi".equals(source))){sourcetwn = 4;}
                else if(("Lamu".equals(source))){sourcetwn = 5;}
                
                if(("Nairobi".equals(destination))){dest = 0;}
                else if(("Kisumu".equals(destination))){dest = 1;}
                else if(("Meru".equals(destination))){dest = 2;}
                else if(("Garissa".equals(destination))){dest = 3;}
                else if(("Malindi".equals(destination))){dest = 4;}
                else if(("Lamu".equals(destination))){dest = 5;}
                
                Graph kenyaCities = new Graph(V); 
                kenyaCities.addEdge(0, 1, 357);
                kenyaCities.addEdge(0, 2, 225);
                kenyaCities.addEdge(0, 3, 373);
                kenyaCities.addEdge(0, 4, 691);
                kenyaCities.addEdge(1, 0, 357);
                kenyaCities.addEdge(2, 0, 225);
                kenyaCities.addEdge(3, 0, 373);
                kenyaCities.addEdge(4, 0, 691);
                kenyaCities.addEdge(1, 2, 443);
                kenyaCities.addEdge(2, 1, 443);
                kenyaCities.addEdge(2, 3, 408);
                kenyaCities.addEdge(3, 2, 408);
                kenyaCities.addEdge(3, 5, 245);
                kenyaCities.addEdge(5, 3, 245);
                kenyaCities.addEdge(5, 4, 137);
                kenyaCities.addEdge(4, 5, 137);
                
                if(itsA_search.isSelected())
                {
                    optimization_method = "A* Search";

                    List<List<Node> > adj = new ArrayList<List<Node> >();

                    for (int i = 0; i < V; i++) {
                        List<Node> item = new ArrayList<Node>();
                        adj.add(item);
                    }
                    adj.get(0).add(new Node(1, 357));
                    adj.get(0).add(new Node(2, 225));
                    adj.get(0).add(new Node(3, 373));
                    adj.get(0).add(new Node(4, 691));
                    adj.get(1).add(new Node(0, 357));
                    adj.get(1).add(new Node(2, 443));
                    adj.get(2).add(new Node(0, 225));
                    adj.get(2).add(new Node(1, 443));
                    adj.get(2).add(new Node(3, 408));
                    adj.get(3).add(new Node(0, 373));
                    adj.get(3).add(new Node(2, 408));
                    adj.get(3).add(new Node(5, 245));
                    adj.get(4).add(new Node(0, 691));
                    adj.get(4).add(new Node(5, 137));
                    adj.get(5).add(new Node(3, 245));
                    adj.get(5).add(new Node(4, 137));

                    // Calculate the single source shortest path
                    DPQ dpq = new DPQ(V);
                    dpq.a_search(adj, sourcetwn);
                    
                    String Route = "";
                    int length = dpq.dist[dest];
                    if((("Kisumu".equals(source))&&("Meru".equals(destination)))||(("Meru".equals(source))&&("Kisumu".equals(destination)))){Route = "H";}
                    else if((("Meru".equals(source))&&("Garissa".equals(destination)))||(("Garissa".equals(source))&&("Meru".equals(destination)))){Route = "G";}
                    else if((("Garissa".equals(source))&&("Lamu".equals(destination)))||(("Lamu".equals(source))&&("Garissa".equals(destination)))){Route = "D";}
                    else if((("Lamu".equals(source))&&("Malindi".equals(destination)))||(("Malindi".equals(source))&&("Lamu".equals(destination)))){Route = "C";}
                    else if((("Nairobi".equals(source))&&("Malindi".equals(destination)))||(("Malindi".equals(source))&&("Nairobi".equals(destination)))){Route = "B";}
                    else if((("Nairobi".equals(source))&&("Garissa".equals(destination)))||(("Garissa".equals(source))&&("Nairobi".equals(destination)))){Route = "E";}
                    else if((("Nairobi".equals(source))&&("Meru".equals(destination)))||(("Meru".equals(source))&&("Nairobi".equals(destination)))){Route = "F";}
                    else if((("Nairobi".equals(source))&&("Kisumu".equals(destination)))||(("Kisumu".equals(source))&&("Nairobi".equals(destination)))){Route = "A";}
                    else if(("Kisumu".equals(source))&&("Garissa".equals(destination))){Route = "A -> E";}
                    else if(("Garissa".equals(source))&&("Kisumu".equals(destination))){Route = "E -> A";}
                    else if(("Kisumu".equals(source))&&("Lamu".equals(destination))){Route = "A -> E -> D";}
                    else if(("Lamu".equals(source))&&("Kisumu".equals(destination))){Route = "D -> E -> A";}
                    else if(("Kisumu".equals(source))&&("Malindi".equals(destination))){Route = "A -> B";}
                    else if(("Malindi".equals(source))&&("Kisumu".equals(destination))){Route = "B -> A";}
                    else if(("Meru".equals(source))&&("Lamu".equals(destination))){Route = "G -> D";}
                    else if(("Lamu".equals(source))&&("Meru".equals(destination))){Route = "D -> G";}
                    else if(("Meru".equals(source))&&("Malindi".equals(destination))){Route = "G -> D -> C";}
                    else if(("Malindi".equals(source))&&("Meru".equals(destination))){Route = "C -> D -> G";}
                    else if(("Garissa".equals(source))&&("Malindi".equals(destination))){Route = "D -> C";}
                    else if(("Malindi".equals(source))&&("Garissa".equals(destination))){Route = "C -> D";}
                    else if(("Lamu".equals(source))&&("Nairobi".equals(destination))){Route = "D -> E";}
                    else if(("Nairobi".equals(source))&&("Lamu".equals(destination))){Route = "E -> D";}
                   
                    logs = " SOURCE TOWN: "+source+"\n DESTINATION TOWN: "+destination+
                            "\n OPTIMAL ROUTE: "+Route+" \n OPTIMAL DISTANCE: "+length+" KM \n OPTIMIZATION METHOD: "+optimization_method;
                    itsOutput.setText(logs);
                    itsOutput.setEditable(false);
                }
                if(itsBreadthFirst.isSelected())
                {
                    optimization_method = "BreadthFirst Search";
                    kenyaCities.BFS(sourcetwn,dest); 
                    logs = " SOURCE TOWN: "+source+"\n DESTINATION TOWN: "+destination+ "\n"+log3+" SEARCH METHOD: "+optimization_method;
                    itsOutput.setText(logs);
                    itsOutput.setEditable(false);
                }
                if(itsDepthFirst.isSelected())
                {
                    optimization_method = "DepthFirst Search";
                    kenyaCities.DFS(sourcetwn,dest);
                    logs = " SOURCE TOWN: "+source+"\n DESTINATION TOWN: "+destination+ "\n"+log3+" SEARCH METHOD: "+optimization_method;
                    itsOutput.setText(logs);
                    itsOutput.setEditable(false);
                }
            }
            
        } 
        //reseting buttton
        else if (e.getSource() == reset) 
        { 
            source = "";
            destination = "";
            optimization_method = "";
            logs = " Reset Complete!";
            itsOutput.setText(logs);
            itsOutput.setEditable(false);
        } 
    } 
    public static void main(String[] args) throws Exception 
    { 
        Routes dist = new Routes();
        dist.setSize(800,650);
    } 
} 