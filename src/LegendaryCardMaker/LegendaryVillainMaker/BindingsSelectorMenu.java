package LegendaryCardMaker.LegendaryVillainMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import LegendaryCardMaker.CardMakerToolbar;
import LegendaryCardMaker.LegendaryCardMakerFrame;
import LegendaryCardMaker.Messages;

public class BindingsSelectorMenu extends JMenu implements ActionListener{
	
	JMenuItem newBindings = new JMenuItem(Messages.getString("Toolbar.NewBindings"));
	JMenuItem edit = new JMenuItem(Messages.getString("Toolbar.EditBindings"));
	JMenuItem delete = new JMenuItem(Messages.getString("Toolbar.DeleteBindings"));
	
	public LegendaryCardMakerFrame lcmf;
	
	static CardMakerToolbar tb = null;
	
	public BindingsSelectorMenu(LegendaryCardMakerFrame lcmf, CardMakerToolbar tb)
	{
		this.tb = tb;
		this.lcmf = lcmf;
		
		this.setText(Messages.getString("Toolbar.Edit"));
		
		newBindings.addActionListener(this);
		add(newBindings);
		
		addSeparator();
		
		edit.addActionListener(this);
		add(edit);
		
		delete.addActionListener(this);
		add(delete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(newBindings))
		{
			String s = JOptionPane.showInputDialog(lcmf, Messages.getString("IHM.EnterBindingsName"), "");
			if (s == null) { return; }
			if (s != null && s.isEmpty()) { return; }
			
			Villain h = lcmf.lcm.bindingsVillain;
			h.changed = true;
			
			VillainCard hc = VillainMaker.getBlankVillainCard();
			hc.name = s;
			hc.villain = h;
			hc.villainGroup = h.name;
			hc.cardType = VillainCardType.BINDINGS;
			hc.changed = true;
			hc.abilityText = "<k>"+Messages.getString("Card.Betrayal")+": <r>"+Messages.getString("Card.BetrayalEffect");

			lcmf.bindingsListModel.addElement(hc);
			lcmf.lcm.bindingsVillain.cards.add(hc);
		}
		
		if (e.getSource().equals(edit))
		{
			if (getCurrentBindings() == null)
			{
				return;
			}
			VillainMakerFrame vmf = new VillainMakerFrame(getCurrentBindings());
		}
		
		if (e.getSource().equals(delete))
		{
			if (getCurrentBindings() == null)
			{
				return;
			}
			
			int outcome = JOptionPane.showOptionDialog(lcmf, "Delete Bindings?", "Delete Bindings?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (outcome == JOptionPane.YES_OPTION)
			{
				lcmf.lcm.bindingsVillain.cards.remove(getCurrentBindings());
				getBindingsListModel().removeElement(getCurrentBindings());
			}
		}
	}
	
	public JList getBindingsList()
	{
		return lcmf.bindingsList;
	}
	
	public DefaultListModel getBindingsListModel()
	{
		return lcmf.bindingsListModel;
	}
	
	public VillainCard getCurrentBindings()
	{
		VillainCard h = null;
		
		if (lcmf.bindingsListModel.size() > 0 && lcmf.bindingsList.getSelectedIndex() >= 0)
		{
			h = (VillainCard)lcmf.bindingsListModel.get(lcmf.bindingsList.getSelectedIndex());
		}
		
		return h;
	}
}
