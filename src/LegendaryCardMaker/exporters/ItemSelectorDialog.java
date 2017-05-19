package LegendaryCardMaker.exporters;

import java.util.List;

import javax.swing.JDialog;

import LegendaryCardMaker.LegendaryCardMakerFrame;
import LegendaryCardMaker.LegendaryItem;
import LegendaryCardMaker.Messages;
import LegendaryCardMaker.LegendaryHeroMaker.Hero;
import LegendaryCardMaker.LegendarySchemeMaker.SchemeCard;
import LegendaryCardMaker.LegendaryVillainMaker.Villain;
import LegendaryCardMaker.LegendaryVillainMaker.VillainCard;

public class ItemSelectorDialog extends JDialog {

	private JCheckList<LegendaryItem> checklist;
	
	public ItemSelectorDialog(LegendaryCardMakerFrame parent)
	{
		super(parent);
		
		setSize(400, 500);
		
		setTitle(Messages.getString("IHM.SelectItems"));
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		checklist = new JCheckList<LegendaryItem>();
		
		for (Hero h : LegendaryCardMakerFrame.lcmf.lcm.heroes)
		{
			checklist.addCheckListItem(Messages.getString("Card.Hero")+" - " + h.name, h);
		}
		
		for (Villain h : LegendaryCardMakerFrame.lcmf.lcm.villains)
		{
			if (h.name.equals("system_bystander_villain"))
			{
				for (VillainCard vc : h.cards)
				{
					checklist.addCheckListItem(Messages.getString("Card.Bystander")+" - " + h.name, h);
				}
			}
			else if (h.name.equals("system_wound_villain"))
			{
				for (VillainCard vc : h.cards)
				{
					checklist.addCheckListItem(Messages.getString("Card.Wound")+" - " + h.name, h);
				}
			}
			else if (h.name.equals("system_bindings_villain"))
			{
				for (VillainCard vc : h.cards)
				{
					checklist.addCheckListItem(Messages.getString("Card.Bindings")+" - " + h.name, h);
				}
			}
			else
			{
				checklist.addCheckListItem(Messages.getString("Card.Villain")+" - " + h.name, h);
			}
		}
		
		for (SchemeCard h : LegendaryCardMakerFrame.lcmf.lcm.schemes)
		{
			checklist.addCheckListItem(Messages.getString("Card.Scheme")+" - " + h.name + " (" + h.cardType.getDisplayString() + ")", h);
		}
		
		this.add(checklist);
		
		this.setModal(true);
		
		this.setVisible(true);
	}
	
	public List<LegendaryItem> getLegendaryItems()
	{
		return checklist.getSelectedItems();
	}

}
