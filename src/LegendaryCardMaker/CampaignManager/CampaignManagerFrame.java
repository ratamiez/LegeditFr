package LegendaryCardMaker.CampaignManager;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import LegendaryCardMaker.Messages;

public class CampaignManagerFrame extends JDialog {

	
	
	public CampaignManagerFrame()
	{
		setTitle(Messages.getString("IHM.CampaignManager"));
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setModal(true);
		
		this.setSize(800, 600);
		
		this.add(setupEventDeckPanel(), BorderLayout.LINE_START);
		
		this.setVisible(true);
	}
	
	public JScrollPane setupEventDeckPanel()
	{
		JScrollPane scroll = new JScrollPane();
		JPanel panel = new JPanel();
		
		scroll.setViewportView(panel);
		return scroll;
	}
}
