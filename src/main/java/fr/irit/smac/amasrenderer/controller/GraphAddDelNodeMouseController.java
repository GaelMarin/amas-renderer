package fr.irit.smac.amasrenderer.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.swingViewer.ViewPanel;

import fr.irit.smac.amasrenderer.model.AgentGraph;
import fr.irit.smac.amasrenderer.model.Stock;
import fr.irit.smac.amasrenderer.service.GraphService;

/**
 * The Class GraphAddDelNodeMouseController.
 * This controller controls addition and deletion of nodes on the graph "model"
 * Creation and deletion are used by alt+click on an empty space to create one
 * and on a node to delete it and all connected edges
 */
public class GraphAddDelNodeMouseController extends MouseAdapter{

    private GraphService graphNodeService;

    private ViewPanel  graphView;
    
    private int        currentNodeId;
    
    private boolean buttonAddAgent;
    private boolean buttonDelAgent;

    /**
     * Initialize the controller, and adds it to the graph.
     *
     * @param graphView the graph view
     * @param model the model
     */
    public void init(ViewPanel graphView, GraphService graphNodeService) {
        this.graphNodeService = graphNodeService;
        this.graphView = graphView;
        this.currentNodeId = graphNodeService.getModel().getNodeCount() + 1;
        graphView.addMouseListener(this);
        buttonAddAgent= buttonDelAgent = false;
    }
    
    /**
     * On mouse alt+click, if there is no node at mouse location creates a node at mouse location
     * else deletes the node at mouse location
     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e){
        Node n = (Node) graphView.findNodeOrSpriteAt(e.getX(), e.getY());
        //if clicked on empty space, create a node
        if(n == null && SwingUtilities.isLeftMouseButton(e) && (e.isControlDown() || buttonAddAgent)){
           createNode(e);
        }
        //else on right click deletes the node and all connected edges
        //explanation of the if:
        //if there is a node on clic location
        //and user does ctrl+right-click XOR he does left click and the del agen button is pressed
        else if((n != null)&&((SwingUtilities.isRightMouseButton(e) && e.isControlDown())^(SwingUtilities.isLeftMouseButton(e) && buttonDelAgent))){
            this.graphNodeService.removeNode(n);
        }
    }
    
    private void createNode(MouseEvent e){
        String curId = Integer.toString(currentNodeId++);
        Point3 clicLoc = graphView.getCamera().transformPxToGu(e.getX(), e.getY());
        
        this.graphNodeService.addNode(curId, clicLoc.x, clicLoc.y);
    }
}