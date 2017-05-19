package LegendaryCardMaker.LegendaryVillainMaker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import LegendaryCardMaker.CardTextDialog;
import LegendaryCardMaker.Icon;
import LegendaryCardMaker.Messages;

public class VillainMakerToolbar extends JMenuBar implements ActionListener{

	VillainMaker hm;
	VillainMakerFrame hmf;
	
	JMenu file = new JMenu(Messages.getString("Toolbar.File"));
	
	JMenuItem exportJPG = new JMenuItem(Messages.getString("Toolbar.ExportJPEG"));
	JMenuItem exportPNG = new JMenuItem(Messages.getString("Toolbar.ExportPNG"));
	JMenuItem exportPrinterStudioPNG = new JMenuItem(Messages.getString("Toolbar.ExportPrinterStudioPNG"));
	JMenuItem close = new JMenuItem(Messages.getString("Toolbar.Close"));
	
	JMenu edit = new JMenu(Messages.getString("Toolbar.Edit"));
	
	JMenu rarity = new JMenu(Messages.getString("Toolbar.CardType"));
	JCheckBoxMenuItem cardTypeHenchmen = new JCheckBoxMenuItem(Messages.getString("Card.Henchmen"));
	JCheckBoxMenuItem cardTypeVillain = new JCheckBoxMenuItem(Messages.getString("Card.Villain"));
	JCheckBoxMenuItem cardTypeMastermind = new JCheckBoxMenuItem(Messages.getString("Card.Mastermind"));
	JCheckBoxMenuItem cardTypeMastermindTactic = new JCheckBoxMenuItem(Messages.getString("Card.MastermindTactic"));
	JCheckBoxMenuItem cardTypeBystander = new JCheckBoxMenuItem(Messages.getString("Card.Bystander"));
	
	JMenu team = new JMenu(Messages.getString("Card.Team"));
	List<JCheckBoxMenuItem> teamItems = new ArrayList<JCheckBoxMenuItem>();
	
	//JMenu power = new JMenu("Power");
	//List<JCheckBoxMenuItem> powerItems = new ArrayList<JCheckBoxMenuItem>();
	
	JMenuItem setCost = new JMenuItem(Messages.getString("Toolbar.SetCost"));
	JMenuItem setVictory = new JMenuItem(Messages.getString("Toolbar.SetVictoryPoints"));
	JMenuItem setAttack = new JMenuItem(Messages.getString("Toolbar.SetAttack"));
	JMenuItem setCardName = new JMenuItem(Messages.getString("Toolbar.SetCardName"));
	JMenuItem setCardNameSize = new JMenuItem(Messages.getString("Toolbar.SetCardNameSize"));
	JMenuItem setAbilityText = new JMenuItem(Messages.getString("Toolbar.SetAbilityText"));
	JMenuItem setAbilityTextSize = new JMenuItem(Messages.getString("Toolbar.SetAbilityTextSize"));
	JMenuItem setBackgroundImage = new JMenuItem(Messages.getString("Toolbar.SetBackgroundImage"));
	JMenuItem setBackgroundZoom = new JMenuItem(Messages.getString("Toolbar.SetBackgroundZoom"));
	JMenuItem setNumberInDeck = new JMenuItem(Messages.getString("Toolbar.SetNumberInDeck"));
	
	static VillainMakerToolbar tb = null;
	
	public VillainMakerToolbar(VillainMaker hm, VillainMakerFrame hmf)
	{
		tb = this;
		
		this.hm = hm;
		this.hmf = hmf;
		
		exportJPG.addActionListener(this);
		//file.add(exportJPG);
		exportPNG.addActionListener(this);
		file.add(exportPNG);
		
		exportPrinterStudioPNG.addActionListener(this);
		file.add(exportPrinterStudioPNG);
		
		file.addSeparator();
		
		close.addActionListener(this);
		file.add(close);
		
		add(file);
		
		if (hm.card.cardType.equals(VillainCardType.HENCHMEN)) { cardTypeHenchmen.setSelected(true); }
		cardTypeHenchmen.addActionListener(this);
		rarity.add(cardTypeHenchmen);
		if (hm.card.cardType.equals(VillainCardType.VILLAIN)) { cardTypeVillain.setSelected(true); }
		cardTypeVillain.addActionListener(this);
		rarity.add(cardTypeVillain);
		if (hm.card.cardType.equals(VillainCardType.MASTERMIND)) { cardTypeMastermind.setSelected(true); }
		cardTypeMastermind.addActionListener(this);
		rarity.add(cardTypeMastermind);
		if (hm.card.cardType.equals(VillainCardType.MASTERMIND_TACTIC)) { cardTypeMastermindTactic.setSelected(true); }
		cardTypeMastermindTactic.addActionListener(this);
		rarity.add(cardTypeMastermindTactic);
		if (hm.card.cardType.equals(VillainCardType.BYSTANDER)) { cardTypeBystander.setSelected(true); }
		cardTypeBystander.addActionListener(this);
		//rarity.add(cardTypeBystander);
		if (!hm.card.cardType.equals(VillainCardType.BYSTANDER))
		{
			edit.add(rarity);
		}
		
		for (Icon icon : Icon.values())
		{
			if (icon.getIconType().equals(Icon.ICON_TYPE.TEAM) || icon.getIconType().equals(Icon.ICON_TYPE.NONE))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s = s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hm.card.cardTeam = icon;
						
						for (JCheckBoxMenuItem item : tb.teamItems)
						{
							if (item.getText().replace(" ", "_").toUpperCase().equals(enumValue))
							{
								item.setSelected(true);
							}
							else
							{
								item.setSelected(false);
							}
						}
						
						tb.hmf.reRenderCard();
						tb.hm.card.changed = true;
					}
				});
				
				teamItems.add(m);
				team.add(m);
			}
			
			/*
			if (icon.getIconType().equals(Icon.ICON_TYPE.POWER) || icon.getIconType().equals(Icon.ICON_TYPE.NONE))
			{
				String s = icon.toString().substring(0, 1).toUpperCase() + icon.toString().substring(1).toLowerCase();
				s.replace("_", " ");
				JCheckBoxMenuItem m = new JCheckBoxMenuItem(s);
				m.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String enumValue = ((JCheckBoxMenuItem)e.getSource()).getText().replace(" ", "_").toUpperCase();
						Icon icon = Icon.valueOf(enumValue);
						tb.hm.card.cardPower = icon;
						
						for (JCheckBoxMenuItem item : tb.powerItems)
						{
							if (item.getText().replace(" ", "_").toUpperCase().equals(enumValue))
							{
								item.setSelected(true);
							}
							else
							{
								item.setSelected(false);
							}
						}
						
						tb.hmf.reRenderCard();
						tb.hm.card.changed = true;
					}
				});
				
				powerItems.add(m);
				power.add(m);
			}
			*/
			
		}
		
		for (JCheckBoxMenuItem item : teamItems)
		{
			if (hm.card.cardTeam != null && item.getText().replace(" ", "_").toUpperCase().equals(hm.card.cardTeam.toString()))
			{
				item.setSelected(true);
			}
			else
			{
				item.setSelected(false);
			}
		}
		edit.add(team);
		
		/*
		for (JCheckBoxMenuItem item : powerItems)
		{
			if (item.getText().replace(" ", "_").toUpperCase().equals(hm.card.cardPower.toString()))
			{
				item.setSelected(true);
			}
			else
			{
				item.setSelected(false);
			}
		}
		edit.add(power);
		*/
		
		setCardName.addActionListener(this);
		edit.add(setCardName);
		
		setCardNameSize.addActionListener(this);
		edit.add(setCardNameSize);
		
		setVictory.addActionListener(this);
		edit.add(setVictory);
		
		setAttack.addActionListener(this);
		edit.add(setAttack);
		
		//setCost.addActionListener(this);
		//edit.add(setCost);
		
		setAbilityText.addActionListener(this);
		edit.add(setAbilityText);
		
		setAbilityTextSize.addActionListener(this);
		edit.add(setAbilityTextSize);
		
		setBackgroundImage.addActionListener(this);
		edit.add(setBackgroundImage);
		
		setBackgroundZoom.addActionListener(this);
		edit.add(setBackgroundZoom);
		
		setNumberInDeck.addActionListener(this);
		edit.add(setNumberInDeck);
		
		add(edit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cardTypeHenchmen))
		{
			cardTypeHenchmen.setSelected(true);
			cardTypeVillain.setSelected(false);
			cardTypeMastermind.setSelected(false);
			cardTypeMastermindTactic.setSelected(false);
			cardTypeBystander.setSelected(false);
			hm.card.numberInDeck = VillainCardType.HENCHMEN.getCount();
			hm.card.cardType = VillainCardType.HENCHMEN;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		if (e.getSource().equals(cardTypeVillain))
		{
			cardTypeHenchmen.setSelected(false);
			cardTypeVillain.setSelected(true);
			cardTypeMastermind.setSelected(false);
			cardTypeMastermindTactic.setSelected(false);
			cardTypeBystander.setSelected(false);
			hm.card.numberInDeck = VillainCardType.VILLAIN.getCount();
			hm.card.cardType = VillainCardType.VILLAIN;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		if (e.getSource().equals(cardTypeMastermind))
		{
			cardTypeHenchmen.setSelected(false);
			cardTypeVillain.setSelected(false);
			cardTypeMastermind.setSelected(true);
			cardTypeMastermindTactic.setSelected(false);
			cardTypeBystander.setSelected(false);
			hm.card.numberInDeck = VillainCardType.MASTERMIND.getCount();
			hm.card.cardType = VillainCardType.MASTERMIND;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		if (e.getSource().equals(cardTypeMastermindTactic))
		{
			cardTypeHenchmen.setSelected(false);
			cardTypeVillain.setSelected(false);
			cardTypeMastermind.setSelected(false);
			cardTypeMastermindTactic.setSelected(true);
			cardTypeBystander.setSelected(false);
			hm.card.numberInDeck = VillainCardType.MASTERMIND_TACTIC.getCount();
			hm.card.cardType = VillainCardType.MASTERMIND_TACTIC;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		if (e.getSource().equals(cardTypeBystander))
		{
			cardTypeHenchmen.setSelected(false);
			cardTypeVillain.setSelected(false);
			cardTypeMastermind.setSelected(false);
			cardTypeMastermindTactic.setSelected(false);
			cardTypeBystander.setSelected(true);
			hm.card.numberInDeck = VillainCardType.BYSTANDER.getCount();
			hm.card.cardType = VillainCardType.BYSTANDER;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(exportJPG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.JPEGFile"), "jpg", "jpeg");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage bi = hm.generateCard();
				try
				{
					if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".jpg") 
							&& !chooser.getSelectedFile().getName().toLowerCase().endsWith(".jpeg"))
					{
						chooser.setSelectedFile(new File(chooser.getSelectedFile().getAbsolutePath() + ".jpg"));
					}
					if (chooser.getSelectedFile().exists())
					{
						int confirm = JOptionPane.showConfirmDialog(hmf, Messages.getString("IHM.OverwriteFile"), Messages.getString("IHM.FileExists"), JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION)
						{
							hm.exportToJPEG(bi, chooser.getSelectedFile());
						}
					}
					else
					{
						hm.exportToJPEG(bi, chooser.getSelectedFile());
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		
		if (e.getSource().equals(exportPNG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.PNGFile"), "png");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage bi = hm.generateCard();
				try
				{
					if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".png"))
					{
						chooser.setSelectedFile(new File(chooser.getSelectedFile().getAbsolutePath() + ".png"));
					}
					if (chooser.getSelectedFile().exists())
					{
						int confirm = JOptionPane.showConfirmDialog(hmf, Messages.getString("IHM.OverwriteFile"), Messages.getString("IHM.FileExists"), JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION)
						{
							hm.exportToPNG(bi, chooser.getSelectedFile());
						}
					}
					else
					{
						hm.exportToPNG(bi, chooser.getSelectedFile());
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		
		if (e.getSource().equals(exportPrinterStudioPNG))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.PNGFile"), "png");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showSaveDialog(this);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage bi = hm.generateCard();
				bi = hm.resizeImagePS(bi);
				try
				{
					if (!chooser.getSelectedFile().getName().toLowerCase().endsWith(".png"))
					{
						chooser.setSelectedFile(new File(chooser.getSelectedFile().getAbsolutePath() + ".png"));
					}
					if (chooser.getSelectedFile().exists())
					{
						int confirm = JOptionPane.showConfirmDialog(hmf, Messages.getString("IHM.OverwriteFile"), Messages.getString("IHM.FileExists"), JOptionPane.YES_OPTION);
						if (confirm == JOptionPane.YES_OPTION)
						{
							hm.exportToPNG(bi, chooser.getSelectedFile());
						}
					}
					else
					{
						hm.exportToPNG(bi, chooser.getSelectedFile());
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		}
		
		/*
		if (e.getSource().equals(setCost))
		{
			String s = JOptionPane.showInputDialog(hmf, "Enter the Cost", hm.card.cost);
			if (s == null) { s = hm.card.cost; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.cost = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		*/
		
		if (e.getSource().equals(setVictory))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterRecruit"), hm.card.victory);
			if (s == null) { s = hm.card.victory; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.victory = s;
			hmf.reRenderCard();
			hm.card.changed = true;
			
			hmf.updateLinkedCards();
		}
		
		if (e.getSource().equals(setAttack))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterAttack"), hm.card.attack);
			if (s == null) { s = hm.card.attack; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.attack = s;
			hmf.reRenderCard();
			hm.card.changed = true;
			
			hmf.updateLinkedCards();
		}
		
		if (e.getSource().equals(setAbilityText))
		{
			//String s = JOptionPane.showInputDialog(hmf, "Enter the Ability Text", hm.card.abilityText);
			String s = new CardTextDialog(hm.card.abilityText).showInputDialog();
			if (s == null) { s = hm.card.abilityText; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.abilityText = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardName))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterCardName"), hm.card.name);
			if (s == null) { s = hm.card.name; }
			if (s != null && s.isEmpty()) { s = null; }
			hm.card.name = s;
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setAbilityTextSize))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterAbilityTextSize"), hm.textSize);
			if (s == null) { s = "" + hm.textSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.textSize; }
			try
			{
				hm.textSize = Integer.parseInt(s);
				hm.card.abilityTextSize = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(setCardNameSize))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterCardNameSize"), hm.cardNameSize);
			if (s == null) { s = "" + hm.cardNameSize; }
			if (s != null && s.isEmpty()) { s = "" + hm.cardNameSize; }
			try
			{
				hm.cardNameSize = Integer.parseInt(s);
				hm.card.nameSize = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
		}
		
		if (e.getSource().equals(close))
		{
			hmf.setVisible(false);
		}
		
		if (e.getSource().equals(setBackgroundImage))
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(Messages.getString("IHM.ImageFiles"), "png", "jpeg", "jpg", "bmp");
		    chooser.addChoosableFileFilter(filter1);
		    chooser.setFileFilter(filter1);
			int outcome = chooser.showOpenDialog(hmf);
			if (outcome == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					//ImageIcon ii = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
					//hmf.imageLabel.setIcon(ii);
					hm.card.imagePath = chooser.getSelectedFile().getAbsolutePath();
					hm.card.imageZoom = 1.0d;
					hm.card.imageOffsetX = 0;
					hm.card.imageOffsetY = 0;
					
					hmf.reRenderCard();
					hm.card.changed = true;
					
					hmf.updateLinkedCards();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
		if (e.getSource().equals(setBackgroundZoom))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterBackgroundZoom"), hm.card.imageZoom);
			if (s == null) { s = "" + hm.card.imageZoom; }
			if (s != null && s.isEmpty()) { s = "" + hm.card.imageZoom; }
			try
			{
				hm.card.imageZoom = Double.parseDouble(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hmf.reRenderCard();
			hm.card.changed = true;
			
			hmf.updateLinkedCards();
		}
		
		if (e.getSource().equals(setNumberInDeck))
		{
			String s = JOptionPane.showInputDialog(hmf, Messages.getString("IHM.EnterNumberDeck"), hm.card.numberInDeck);
			if (s == null) { s = "" + hm.card.numberInDeck; }
			if (s != null && s.isEmpty()) { s = "" + hm.card.numberInDeck; }
			try
			{
				hm.card.numberInDeck = Integer.parseInt(s);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			hm.card.changed = true;
		}
	}

	public VillainMakerToolbar getVillainMakerToolbar()
	{
		return tb;
	}
}
